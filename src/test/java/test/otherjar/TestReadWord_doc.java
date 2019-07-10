package test.otherjar;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class TestReadWord_doc {
	public static void main(String[] args) throws Exception {
		HWPFDocument word = new HWPFDocument(new FileInputStream(new File("src/test/java/test/otherjar/test.doc")));
		System.out.println(word.getText());
		
		XWPFDocument word2 = new XWPFDocument(new FileInputStream(new File("src/test/java/test/otherjar/test.docx")));
		for (XWPFParagraph pa : word2.getParagraphs()) {
			System.out.println(pa.getText());
		}
		
		word2.close();
		word.close();
	}
}
