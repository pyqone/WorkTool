package pres.tool.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import pres.auxiliary.selenium.browers.ChromeBrower;
import pres.auxiliary.selenium.event.Event;

/**
 * <p>
 * <b>文件名：</b>WebDataCompare.java
 * </p>
 * <p>
 * <b>用途：</b>用于对页面数据的操作工具
 * </p>
 * <p>
 * <b>编码时间：</b>2019年8月2日上午9:45:00
 * </p>
 * <p>
 * <b>修改时间：</b>2019年8月2日上午9:45:00
 * </p>
 * 
 * @author 彭宇琦
 * @version Ver1.0
 * @since JDK 1.8
 *
 */
public class WebDataCompare {
	/**
	 * 该方法用于统计页面数据，根据数据的约束条件进行数据的组合统计，并将统计存储指定的excel文件中
	 * 
	 * @param folder     文件保存的文件夹
	 * @param fileName   文件名
	 * @param conditions 条件类对象组
	 * @param iframes    需要定位到列表所在的窗体
	 * @return 统计结果文件
	 * @throws IOException
	 */
	public static File statisData(File folder, String fileName, Condition[] conditions, String... iframes)
			throws IOException {
		// 构造事件类对象
		Event event = new Event(new ChromeBrower("resource/BrowserDriver/chromedriver.exe", 9222).getDriver());
		// 循环，添加所有的iframe
		for (String iframe : iframes) {
			event.switchFrame(iframe);
		}

		// 定义map对象，用于记录列表数据
		HashMap<Condition, HashMap<String, Integer>> datas = new HashMap<Condition, HashMap<String, Integer>>(16);
		// 定义map，用于存储组合条件的数据
		HashMap<String, Integer> groupDatas = new HashMap<String, Integer>(16);
		// 获取条件中的title，用于作为统计的依据以及读写文件的Sheet
		ArrayList<String> titles = new ArrayList<>();

		// 创建文件夹
		folder.mkdirs();
		// 定义目标文件
		File targeFile = new File(folder, ("\\" + fileName + ".xlsx"));
		// 判断文件是否存在，若文件存在，则读取文件
		if (targeFile.exists()) {
			// 循环，抽取title，用于指定Sheet的名称，并存储该Sheet
			for (Condition condition : conditions) {
				// 存储title
				titles.add(condition.getTitle());
				// 构造统计map
				datas.put(condition, readFile(targeFile, condition.getTitle(), 1, 0, 1));
			}

			// 存储组合条件的map
			groupDatas = readFile(targeFile, "组合条件", 1, 0, 1);
		} else {
			// 循环，抽取title，构造map
			for (Condition condition : conditions) {
				// 存储title
				titles.add(condition.getTitle());
				// 构造统计map
				datas.put(condition, new HashMap<String, Integer>(16));
			}
		}

		// 循环，获取页面每一行的数据
		while (true) {
			// 指定当前获取列表的行数
			int index = 0;
			// 用于指定循环是否继续
			boolean hasData = true;
			// 用于判断数据是否符合约束条件
			boolean constraint = true;
			// 用于存储组合查询符合约束条件的元素值
			String constraintText = "";

			// 循环，datas中存储的所有的Condition对象，以用于获取一行数据
			for (Condition condition : datas.keySet()) {
				try {
					// 读取第index行数据
					String text = condition.getListEvent(event.getDriver()).get(index).getText();
					// 判断该数据是否存在于condition指向的Map，若存在，则其Integer加1，不存在，则put如Map
					if (datas.get(condition).containsKey(text)) {
						datas.get(condition).put(text, datas.get(condition).get(text) + 1);
					} else {
						datas.get(condition).put(text, 1);
					}

					// 判断constraint是否已经为false，若已经为false，则表示某一列的元素已经不符合约束，则无需再对比其他列的数据
					if (!constraint) {
						continue;
					} else {
						// 若constraint为true，则对比当前列的数据是否符合约束，并将返回值存储至constraint中
						constraint = condition.isConformToConstraint(text);
						constraintText += text;
					}
					// 判断该值是否符合约束条件
				} catch (IndexOutOfBoundsException e) {
					// 若列表元素已被全部读取，则此会抛出IndexOutOfBoundsException，则可直接结束循环
					hasData = false;
					break;
				}
			}
			// 判断hasData值，若hasData为false，则表示此时列表上某一列或所有列的数据读取完毕，则可以结束外层循环
			if (!hasData) {
				break;
			}
			// 判断是否所有列元素均符合相应的约束条件，若符合，则存储该约束条件，并存储出现的次数
			if (constraint) {
				if (groupDatas.containsKey(constraintText)) {
					groupDatas.put(constraintText, groupDatas.get(constraintText) + 1);
				} else {
					groupDatas.put(constraintText, 1);
				}
			}

			// 初始化数据
			index++;
			constraintText = "";
		}

		for (Condition condition : datas.keySet()) {
			saveFile(targeFile, datas.get(condition), condition.getTitle(), 0, 0, 1);
		}

		return targeFile;
	}

	private static void saveFile(File targeFile, HashMap<String, Integer> datas, String sheetName, int dataRow,
			int keyCell, int valueCell) throws IOException {
		// 定义xlsx操作类对象，若文件存在则续写，文件不存在则新写
		XSSFWorkbook xlsx = null;
		XSSFSheet sheet = null;
		//判断文件是否存在，若文件不存在，则创建xlsx文件，若文件存在，则按照指定的数据读取xlsx
		if (targeFile.exists()) {
			xlsx = new XSSFWorkbook(new FileInputStream(targeFile));
			//判断sheet是否存在，若sheet不存在，则创建sheet
			if ((sheet = xlsx.getSheet(sheetName)) == null) {
				sheet = xlsx.createSheet(sheetName);
			}
		} else {
			xlsx = new XSSFWorkbook();
			sheet = xlsx.createSheet(sheetName);
		}
		
		
		
		xlsx.close();
	}

	private static HashMap<String, Integer> readFile(File targeFile, String sheetName, int dataRow, int keyCell,
			int valueCell) {
		// TODO Auto-generated method stub
		return null;
	}
}
