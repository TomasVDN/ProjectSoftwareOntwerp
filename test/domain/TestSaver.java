package domain;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TestSaver {
	
	Saver saver = new Saver();
	String filename = "test";
	String filePath = "savedPages/test.html";
	
	String normalWebPage = "<table>\n"
			+ "  <tr><td>HTML elements partially supported by Browsr:\n"
			+ "  <tr><td>\n"
			+ "    <table>\n"
			+ "      <tr><td><a href=\"a.html\">a</a><td>Hyperlink anchors\n"
			+ "      <tr><td><a href=\"table.html\">table</a><td>Tables\n"
			+ "      <tr><td><a href=\"tr.html\">tr</a><td>Table rows\n"
			+ "      <tr><td><a href=\"td.html\">td</a><td>Table cells containing table data\n"
			+ "    </table>\n"
			+ "</table>\n";
	File directory = new File("savedPages");
	
	
	@BeforeEach
	void setup() {
		saver.setHtmlCode(normalWebPage);
	}
	
	@AfterEach
	void cleanUp() {
		deleteFolderContent(directory);
	}
	
	
	@Test
	void testValidFileName() {
		saver.saveToFile(filename);
		
		File file = new File(filePath);
		assertTrue(file.exists());
		assertEquals(normalWebPage, readFileToString(filePath));
	}
	
	@Test
	void testEmptyFileName() {
		saver.saveToFile("");
		int fileCount = directory.list().length;
		assertEquals(1, fileCount);
	}
	
	@Test
	void testNullFileName() {
		saver.saveToFile(null);
		int fileCount = directory.list().length;
		assertEquals(1, fileCount);
	}
	
	
	//https://stackoverflow.com/questions/7768071/how-to-delete-directory-content-in-java/8632891
	private static void deleteFolderContent(File folder) {
	    File[] files = folder.listFiles();
	    if(files!=null) { //some JVMs return null for empty dirs
	        for(File f: files) {
	        	f.delete();
	        }
	    }
	}

	private String readFileToString(String filename) {
		try {
			return new String(Files.readAllBytes(Paths.get(filename)));
		} catch (IOException e) {
			return "Error";
		}		
	}
	

}