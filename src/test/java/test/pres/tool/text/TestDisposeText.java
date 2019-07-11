package test.pres.tool.text;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import pres.tool.text.DisposeText;

public class TestDisposeText {
	/**
	 * 
	 * @throws IOException
	 */
	@Ignore
	@Test
	public void test_01_readFile() throws IOException {
		System.out.println("doc:");
//		System.out.println(DisposeText.readFile(new File("src/test/java/test/pres/tool/text/test.doc")));
		System.out.println("--------------------------------");
		System.out.println("docx:");
//		System.out.println(DisposeText.readFile(new File("src/test/java/test/pres/tool/text/test.docx")));
		System.out.println("--------------------------------");
		System.out.println("xls:");
//		System.out.println(DisposeText.readFile(new File("src/test/java/test/pres/tool/text/test.xls")));
		System.out.println("--------------------------------");
		System.out.println("xlsx:");
//		System.out.println(DisposeText.readFile(new File("src/test/java/test/pres/tool/text/test.xlsx")));
		System.out.println("--------------------------------");
		System.out.println("txt:");
//		System.out.println(DisposeText.readFile(new File("src/test/java/test/pres/tool/text/test.txt")));
		System.out.println("--------------------------------");
		System.out.println("csv:");
//		System.out.println(DisposeText.readFile(new File("src/test/java/test/pres/tool/text/test.csv")));
		System.out.println("--------------------------------");
	}
	
	/**
	 * 测试compareFileWord()方法
	 * @throws IOException
	 */
	@Test
	public void test_01_compareFileWord() throws IOException {
//		File testFile = new File("src/test/java/test/pres/tool/text/test.txt");
//		File targetFile = new File("src/test/java/test/pres/tool/text/test.doc");
//		DisposeText.compareFileWord(testFile, targetFile).forEach(e -> System.out.println(e));
//		DisposeText.compareFileWord(testFile, targetFile);
		File f1 = new File("D:\\1.txt");
		File f2 = new File("D:\\2.txt");
		DisposeText.compareFileWord(f2, f1).forEach(e -> System.out.println(e));
	}
	
	/**
	 * 测试textDelDuplication()方法
	 * @throws IOException
	 */
	@Test
	public void test_02_textDelDuplication() throws IOException {
		System.out.println(DisposeText.textDelDuplication(new File("src/test/java/test/pres/tool/text/春.txt")));
	}
	
	/**
	 * 测试compareFileText()方法
	 * @throws IOException
	 */
	@Test
	public void test_03_compareFileText() throws IOException {
		File testFile = new File("src/test/java/test/pres/tool/text/春2.txt");
		File targeFile = new File("src/test/java/test/pres/tool/text/春.txt");
		for (String[] texts : DisposeText.compareFileText(testFile, targeFile, "\n", "。", "，")) {
			System.out.println("待测文：" + texts[0]);
			System.out.println("目标文：" + texts[1]);
			System.out.println("---------------------");
		}
	}
	
	@Test
	public void test_04_wordDelDuplication() throws IOException {
		File testFile = new File("src/test/java/test/pres/tool/text/test.txt");
		
		for (String s : DisposeText.wordDelDuplication(testFile)) {
			System.out.println(s);
		}
	}
}
