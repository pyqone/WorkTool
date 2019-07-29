package pres.tool.web;

import java.io.File;

public class WebDataCompare {
	public static File statisData(File folder, String fileName, String pageNum, Condition...conditions) {
		//创建文件夹
		folder.mkdirs();
		//定义目标文件
		File targeFile = new File(folder, ("\\" + fileName + ".xlsx"));
		
		
		return targeFile;
	}
}
