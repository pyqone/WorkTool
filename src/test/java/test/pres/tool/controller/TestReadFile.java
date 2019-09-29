package test.pres.tool.controller;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import pres.tool.controller.ReadFile;

public class TestReadFile {
	File folder = new File("src/test/java/test/pres/tool/controller/");
	
	@Test
	public void testReadExcel() throws IOException {
		File xls = new File(folder, "测试文件.xls");
		File xlsx = new File(folder, "测试文件.xlsx");
		
		ReadFile.readExcel(xls, 0).forEach(System.out::println);
		System.out.println("-".repeat(20));
		ReadFile.readExcel(xlsx, 0).forEach(System.out::println);
	}
}
