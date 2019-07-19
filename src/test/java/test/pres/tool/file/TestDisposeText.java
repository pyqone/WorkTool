package test.pres.tool.file;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import pres.tool.file.DisposeText;

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
	 * ï¿½ï¿½ï¿½ï¿½compareFileWord()ï¿½ï¿½ï¿½ï¿½
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
	 * ï¿½ï¿½ï¿½ï¿½textDelDuplication()ï¿½ï¿½ï¿½ï¿½
	 * @throws IOException
	 */
	@Test
	public void test_02_textDelDuplication() throws IOException {
		System.out.println(DisposeText.textDelDuplication(new File("src/test/java/test/pres/tool/text/ï¿½ï¿½.txt")));
	}
	
	/**
	 * ï¿½ï¿½ï¿½ï¿½compareFileText()ï¿½ï¿½ï¿½ï¿½
	 * @throws IOException
	 */
	@Test
	public void test_03_compareFileText() throws IOException {
		File testFile = new File("src/test/java/test/pres/tool/text/ï¿½ï¿½2.txt");
		File targeFile = new File("src/test/java/test/pres/tool/text/ï¿½ï¿½.txt");
		for (String[] texts : DisposeText.compareFileText(testFile, targeFile, "\n", "ï¿½ï¿½", "ï¿½ï¿½")) {
			System.out.println("ï¿½ï¿½ï¿½ï¿½ï¿½Ä£ï¿½" + texts[0]);
			System.out.println("Ä¿ï¿½ï¿½ï¿½Ä£ï¿½" + texts[1]);
			System.out.println("---------------------");
		}
	}
	
	@Test
	public void test_04_wordDelDuplication() throws IOException {
//		File testFile = new File("src/test/java/test/pres/tool/text/test.txt");
		File testFile = new File("D:\\6.programe\\tool\\eclipse_java_workspace\\GuiJianTongProjected\\src\\main\\java\\com\\selenium\\project\\Éí·ÝÖ¤.xlsx");
		for (String s : DisposeText.wordDelDuplication(testFile)) {
			System.out.println(s);
		}
	}
}
