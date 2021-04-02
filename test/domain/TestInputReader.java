package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import htmlElement.ContentSpan;

class TestInputReader {
	
	Saver saver = new Saver();
	InputReader inputReader = new InputReader();
	String normalWebPage = "TABLE: (ROW: (CELL: TEXT: HTML elements partially supported by Browsr:),"
			+ "ROW: (CELL: TABLE: (ROW: (CELL: HYPER: (a.html,TEXT: a),CELL: TEXT: Hyperlink anchors),"
			+ "ROW: (CELL: HYPER: (table.html,TEXT: table),CELL: TEXT: Tables),"
			+ "ROW: (CELL: HYPER: (tr.html,TEXT: tr),CELL: TEXT: Table rows),"
			+ "ROW: (CELL: HYPER: (td.html,TEXT: td),CELL: TEXT: Table cells containing table data))))";
	String malformedUrl = "TEXT: Error occured. Make sure you entered a valid URL.";
	String illegalBrowsrWebPage = "TEXT: Error occured. Reason: not a valid Browsr document.";
	@Test
	void legalUrl() {
		ArrayList<ContentSpan> list = inputReader.readFile("https://people.cs.kuleuven.be/~bart.jacobs/browsrtest.html", saver);
		
		String output = list.get(0).toString();
		assertEquals(normalWebPage, output);
	}
	
	@Test
	void illegalUrl() {
		ArrayList<ContentSpan> list = inputReader.readFile("https://people.cs.kuleuven.be/~bart.jacobs/malformed.html", saver);
		
		String output = list.get(0).toString();
		assertEquals(malformedUrl, output);
	}
	
	@Test
	void nullUrl() {
		ArrayList<ContentSpan> list = inputReader.readFile(null, saver);
		
		String output = list.get(0).toString();
		assertEquals(malformedUrl, output);
	}
	
	@Test
	void legalUrlNotBrowsrFile() {
		ArrayList<ContentSpan> list = inputReader.readFile("https://www.google.com", saver);
		
		String output = list.get(0).toString();
		assertEquals(illegalBrowsrWebPage, output);
	}
	

}
