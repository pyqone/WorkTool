package test.pres.tool.web;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import pres.auxiliary.selenium.browers.ChromeBrower;
import pres.auxiliary.selenium.event.Event;
import pres.tool.web.Condition;
import pres.tool.web.ConstraintType;
import pres.tool.web.WebDataCompare;

public class TestWebDataCompare {
	@Test
	public void test_01_noConstraint() throws IOException {
		Condition[] c = new Condition[2];
		c[0] = new Condition("编号", "//*[@id=\"app\"]/div/div/section/div/div[2]/div[3]/table/tbody/tr/td[1]/div/span");
		c[1] = new Condition("题目", "//*[@id=\"app\"]/div/div/section/div/div[2]/div[3]/table/tbody/tr/td[2]/div/span");
		
		WebDataCompare.statisData(
			new File("D:\\8.test\\WebDataCompare"), 
			"test1", 
			c
		);
	}
	
	@Test
	public void test_02_Constraint() throws IOException {
		Condition[] c = new Condition[2];
		c[0] = new Condition("编号", 
				"//*[@id=\"app\"]/div/div/section/div/div[2]/div[3]/table/tbody/tr/td[3]/div/span", 
				ConstraintType.STRING, 
				"判断题");
		
		c[1] = new Condition("题目", 
				"//*[@id=\"app\"]/div/div/section/div/div[2]/div[3]/table/tbody/tr/td[2]/div/span", 
				ConstraintType.STRING, 
				"aaa");
		
		WebDataCompare.statisData(
			new File("D:\\8.test\\WebDataCompare"), 
			"test2", 
			c
		);
	}
	
	@Test
	public void test_03_numConstraint() throws IOException {
		Condition[] c = new Condition[3];
		c[0] = new Condition(
				"题型", 
				"//*[@id=\"app\"]/div/div/section/div/div[2]/div[3]/table/tbody/tr/td[3]/div/span", 
				ConstraintType.STRING, 
				"单选题"
				);
		
		c[1] = new Condition(
				"题目", 
				"//*[@id=\"app\"]/div/div/section/div/div[2]/div[3]/table/tbody/tr/td[2]/div/span"
				);
		
		c[2] = new Condition(
				"答案数量", 
				"//*[@id=\"app\"]/div/div/section/div/div[2]/div[3]/table/tbody/tr/td[4]/div/span", 
				ConstraintType.NUMBER, 
				"[4, 4]", "(5, 6]"
				);
		
		WebDataCompare.statisData(
			new File("D:\\8.test\\WebDataCompare"), 
			"test4", 
			c
		);
	}
	
	@Test
	public void test_04_dataConstraint() throws IOException {
		Condition[] c = new Condition[4];
		c[0] = new Condition(
				"题型", 
				"//*[@id=\"app\"]/div/div/section/div/div[2]/div[3]/table/tbody/tr/td[3]/div/span", 
				ConstraintType.STRING, 
				"单选题"
				);
		
		c[1] = new Condition(
				"题目", 
				"//*[@id=\"app\"]/div/div/section/div/div[2]/div[3]/table/tbody/tr/td[2]/div/span"
				);
		
		c[2] = new Condition(
				"答案数量", 
				"//*[@id=\"app\"]/div/div/section/div/div[2]/div[3]/table/tbody/tr/td[4]/div/span"
				);
		
		c[3] = new Condition(
				"更新时间", 
				"//*[@id=\"app\"]/div/div/section/div/div[2]/div[3]/table/tbody/tr/td[12]/div/span", 
				ConstraintType.DATE, 
				"[2019-07-31 20:16:42, 2019-07-31 19:40:25]", "(2019-07-31 22:39:35, 2019-08-01 14:50:29]"
				);
		
		WebDataCompare.statisData(
			new File("D:\\8.test\\WebDataCompare"), 
			"test5", 
			c
		);
	}
	
	@Test
	public void test_05_autoGet() throws IOException, InterruptedException {
		Event event = new Event(new ChromeBrower("resource/BrowserDriver/chromedriver.exe", 9222).getDriver());
		event.switchFrame("//*[@id=\"ifmbimcenter\"]");
		event.switchFrame("//*[@id=\"src\"]");
		
		for (int i = 0; i < 600; i++) {
			Condition[] c = new Condition[2];
			c[0] = new Condition(
					"主管单位", 
					"//div[2]/table/tbody/tr/td[8]/div", 
					ConstraintType.STRING, 
					"南宁市住建局"
					);
			
			c[1] = new Condition(
					"状态", 
					"//div[2]/table/tbody/tr/td[9]/div", 
					ConstraintType.STRING, 
					"在建"
					);
			
			WebDataCompare.statisData(
				new File("D:\\8.test\\WebDataCompare"), 
				"test6", 
				c, 
				"//*[@id=\"ifmbimcenter\"]", 
				"//*[@id=\"src\"]"
			);
			event.getClickEvent().click("/html/body/div[2]/div/div[3]/table/tbody/tr/td[10]/a/span/span[2]");
			Thread.sleep(2000);
		}
		event.getDriver().quit();
	}
}
