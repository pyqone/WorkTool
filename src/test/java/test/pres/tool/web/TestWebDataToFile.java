package test.pres.tool.web;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import pres.tool.web.WebDataToFile;

public class TestWebDataToFile {
	/**
	 * 测试获取文本
	 * @throws IOException
	 */
	@Test
	public void test_01_webTextDataToFile() throws IOException {
		WebDataToFile.webTextDataToFile(new File("D:\\8.test\\WebDataToFile"), 
				"测试文件12", 
				"//div[2]/table/tbody/tr/td[3]/div", 
				"//*[contains(@src, '/project/salary/choiceUser')]");
		WebDataToFile.webTextDataToFile(new File("D:\\8.test\\WebDataToFile"), 
				"测试文件13", 
				"//div[2]/table/tbody/tr/td[4]/div", 
				"//*[contains(@src, '/project/salary/choiceUser')]");
	}
	
	/**
	 * 测试单列获取文本
	 * @throws IOException
	 */
	@Test
	public void test_02_webListDataToFile() throws IOException {
		WebDataToFile.webListDataToFile(new File("D:\\8.test\\WebDataToFile"), "测试文件1", "新闻标题", "/html/body/div[2]/div/div[2]/div[2]/div[2]/table/tbody/tr/td[3]/div/a", "//*[@id='ifmbimcenter']", "//*[@id='src']");
	}
	
	/**
	 * 测试多列获取
	 * @throws IOException 
	 */
	@Test
	public void test_03_webListDataToFile() throws IOException {
		WebDataToFile.webListDataToFile(new File("D:\\8.test\\WebDataToFile"), 
				"测试文件2", 
				"企业", 
				"//div[2]/table/tbody/tr/td[3]/div", 
				"//*[contains(@src, '/project/salary/choiceUser')]");
		WebDataToFile.webListDataToFile(new File("D:\\8.test\\WebDataToFile"), 
				"测试文件2", 
				"姓名", 
				"//div[2]/table/tbody/tr/td[4]/div", 
				"//*[contains(@src, '/project/salary/choiceUser')]");
	}
	
	/**
	 * 测试多列获取
	 * @throws IOException 
	 */
	@Test
	public void test_04_IdDataToFile() throws IOException {
		WebDataToFile.webListDataToFile(new File("D:\\8.test\\WebDataToFile"), "身份证", "身份证", "/html/body/div[2]/div[1]/div/div[2]/table[2]/tbody/tr/td");
	}
}
