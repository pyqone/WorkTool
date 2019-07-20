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
		WebDataToFile.webTextDataToFile(new File("D:\\8.test\\WebDataToFile"), "测试文件1", "//*[@id=\"datagrid-row-r1-2-0\"]/td[3]/div/a", "//*[@id='ifmbimcenter']", "//*[@id='src']");
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
		WebDataToFile.webListDataToFile(new File("D:\\8.test\\WebDataToFile"), "测试文件2", "新闻标题", "/html/body/div[2]/div/div[2]/div[2]/div[2]/table/tbody/tr/td[3]/div/a", "//*[@id='ifmbimcenter']", "//*[@id='src']");
		WebDataToFile.webListDataToFile(new File("D:\\8.test\\WebDataToFile"), "测试文件2", "时间", "/html/body/div[2]/div/div[2]/div[2]/div[2]/table/tbody/tr/td[4]/div", "//*[@id='ifmbimcenter']", "//*[@id='src']");
	}
}
