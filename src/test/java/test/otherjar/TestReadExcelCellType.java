package test.otherjar;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestReadExcelCellType {
	public static void main(String[] args) throws Exception {
		File f = new File("src/test/java/test/otherjar/test.xls");
		// 读取文件流
		FileInputStream fip = new FileInputStream(f);
		// 用于读取excel
		Workbook excel = null;
		// 根据文件名的后缀，对其判断文件的格式，并按照相应的格式构造对象
		if (f.getName().indexOf("xlsx") > -1) {
			// 通过XSSFWorkbook对表格文件进行操作
			excel = new XSSFWorkbook(fip);
		} else {
			// 通过XSSFWorkbook对表格文件进行操作
			excel = new HSSFWorkbook(fip);
		}
		
		// 读取方式为，将列与行的内容一同写在一列文本中，不考虑分列
		Sheet sheet = excel.getSheetAt(0);
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				Cell cell = row.getCell(j);
				System.out.println(cell.getCellTypeEnum());
				System.out.println(cell.toString());
				if (cell.getCellTypeEnum() == CellType.NUMERIC) {
					System.out.println(cell.getDateCellValue());
					System.out.println(Double.valueOf(cell.getNumericCellValue()));
				}
				System.out.println("-----------------------------");
			}
		}
		
		excel.close();
		fip.close();
	}
}
