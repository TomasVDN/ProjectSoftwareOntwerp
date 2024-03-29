package domain;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TestSaver {
	
	Saver saver = new Saver();
	String filename = "test";
	String filePath = "savedPages/test.html";
	
	String normalWebPage = "<table>\n"
			+ "  <tr><td>HTML elements partially supported by BrowsrController:\n"
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
		deleteFolderContent(directory);
	}
	
	@AfterEach
	void cleanUp() {
		deleteFolderContent(directory);
	}
	
	
	@Test
	void testValidFileName() {
		saver.saveToFile(filename, normalWebPage);
		
		File file = new File(filePath);
		assertTrue(file.exists());
		assertEquals(normalWebPage, readFileToString(filePath));
	}
	
	@Test
	void testEmptyFileName() {
		saver.saveToFile("", normalWebPage);
		int fileCount = directory.list().length;
		assertEquals(1, fileCount);
	}
	
	@Test
	void testNullFileName() {
		saver.saveToFile(null, normalWebPage);
		int fileCount = directory.list().length;
		assertEquals(1, fileCount);
	}
	
	//TODO test when htmlCode is null && add safeguard for it in Saver class
	
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