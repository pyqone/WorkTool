package pres.tool.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import pres.tool.controller.ReadFile;

/**
 * <p><b>文件名：</b>DisposeFile.java</p>
 * <p><b>用途：</b>用于满足日常中对文本文件的处理</p>
 * <p><b>编码时间：</b>2019年7月12日上午10:40:25</p>
 * <p><b>修改时间：</b>2019年7月12日上午10:40:25</p>
 * @author 彭宇琦
 * @version Ver1.0
 * @since JDK 12
 *
 */
public class DisposeFile {
	/**
	 * 用于对Excel文件的后缀判定
	 */
	private static String EXCEL = "xlsx";
	
	/**
	 * 
	 * @param tableFile 
	 */
	/**
	 * 该方法用于对表格文件进行转置处理的工具
	 * @param tableFile 表格文件
	 * @param sheetIndex 工作簿sheet的下标
	 */
	public static void tableT(File tableFile, int sheetIndex) {
		ArrayList<String> texts = null;
		try {
			texts = readFile(tableFile, sheetIndex);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 用于读取文件，并将文件的信息有序地返回
	 * @param tagerFile 目标文件
	 * @return 文件中的内容
	 * @throws IOException 
	 */
	public static ArrayList<String> readFile(File tagerFile, int sheetIndex) throws IOException {
		// 用于存储文件的后缀名，以判断文件的格式
		String[] fileName = tagerFile.getName().split("\\.");
		String suffix = fileName[fileName.length - 1];

		switch (suffix) {
		case "xls":
		case "xlsx":
			return ReadFile.readExcel(tagerFile, sheetIndex);
		default:
			throw new UnsupportedFileException("无法解析“" + suffix + "”文件格式");
		}
	}
}
