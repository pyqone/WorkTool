package pres.tool.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import pres.auxiliary.selenium.browers.ChromeBrower;
import pres.auxiliary.selenium.event.Event;

/**
 * <p><b>文件名：</b>WebDataToFile.java</p>
 * <p><b>用途：</b>用于从页面爬取数据以文本的形式存储至本地的工具</p>
 * <p><b>编码时间：</b>2019年7月19日下午5:04:20</p>
 * <p><b>修改时间：</b>2019年7月19日下午5:04:20</p>
 * @author 彭宇琦
 * @version Ver1.0
 * @since JDK 1.8
 *
 */
public class WebDataToFile {
	/**
	 * 该方法可用于获取页面的数据，并以docx的文件形式进行存储
	 * @param folder 生成的文件存放位置
	 * @param fileName 生成的文件名
	 * @param element 需要获取文本的元素
	 * @return 返回生成的文件对象
	 * @throws IOException 
	 */
	public static File webTextDataToFile(File folder, String fileName, String element) throws IOException {
		//定义浏览器对象，该对象暂时定义为已开启的浏览器对象
		Event event = new Event(new ChromeBrower("resource/BrowserDriver/chromedriver.exe", 9222).getDriver());
		//获取页面数据
		String text = event.getTextEvent().getText(element).trim();
		
		//返回生成的文件的文件对象
		return writeData(folder, fileName, text, false);
	}
	
	/**
	 * 该方法用于将数据写入相应的文件中
	 * @param targeFile 目标文件对象
	 * @param text 需要写入文件的文本
	 * @param list 定义写入数据的方式是否为列表 
	 * @return 生成的文件对象
	 * @throws IOException 
	 */
	private static File writeData(File folder, String fileName, String text, boolean list) throws IOException {
		//创建文件夹
		folder.mkdirs();
		File targeFile = null;
		
		//判断写入数据的方式是否为列表，为列表，则按照excel形式存储，为文本则按照docx形式存储
		if (list) {
			
		} else {
			//定义需要存储的目标文件
			targeFile = new File(folder + fileName + ".docx");
			XWPFDocument docx = null;
			if (targeFile.exists()) {
				docx = new XWPFDocument(new FileInputStream(targeFile));
			} else {
				docx = new XWPFDocument();
			}
			
			docx.createParagraph().createRun().setText(text);
			docx.write(new FileOutputStream(targeFile));
			docx.close();
		}
		
		return targeFile;
	}
}
