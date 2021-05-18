package converter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import GUIElements.*;
import domain.HTMLToGUI;
import htmlElement.*;

class TestHTMLToGUI {

	HTMLToGUI converter = new HTMLToGUI();
	HTMLText textHyper1 = new HTMLText("hyperlink1");
	HTMLHyperlink hyp1 = new HTMLHyperlink("hyp1", textHyper1);
	HTMLText textHyper2 = new HTMLText("hyperlink2");
	HTMLHyperlink hyp2 = new HTMLHyperlink("hyp2", textHyper2);
	
	HTMLText text1 = new HTMLText("this is a text");
	HTMLText text2 = new HTMLText("this is a text too");
	
	
	HTMLTableCell tableCell1 = new HTMLTableCell(text1);
	HTMLTableCell tableCell2 = new HTMLTableCell(text2);
	HTMLTableCell tableCell3 = new HTMLTableCell(text1);
	HTMLTableCell tableCell4 = new HTMLTableCell(text2);
	HTMLTableCell tableCell5 = new HTMLTableCell(text1);
	HTMLTableCell tableCell6 = new HTMLTableCell(text2);
	HTMLTableCell tableCell7 = new HTMLTableCell(text1);
	HTMLTableCell tableCell8 = new HTMLTableCell(text2);
	HTMLTableCell tableCell9 = new HTMLTableCell(text1);
	
	HTMLTableRow row1 = new HTMLTableRow();
	HTMLTableRow row2 = new HTMLTableRow();
	HTMLTableRow row3 = new HTMLTableRow();
	

	
	HTMLTable createSmallTable() {
		HTMLTable table =new HTMLTable();
		row1.addCell(tableCell1);
		row1.addCell(tableCell2);
		row2.addCell(tableCell3);
		row2.addCell(tableCell4);
		table.addRow(row1);
		table.addRow(row2);
		return table;
	}
	
	HTMLTable createBigTable() {
		HTMLTable table =new HTMLTable();
		row1.addCell(tableCell1);
		row1.addCell(tableCell2);
		row1.addCell(tableCell3);
		row2.addCell(tableCell4);
		row2.addCell(tableCell5);
		row2.addCell(tableCell6);
		row3.addCell(tableCell7);
		row3.addCell(tableCell8);
		row3.addCell(tableCell9);
		table.addRow(row1);
		table.addRow(row2);
		table.addRow(row3);
		return table;
	}	
	
	@Test
	void testTextToGUI() {
		GUIElement gui =converter.toGUI(text1,0, 0, 500, 500);
		assertTrue(gui instanceof Text);
		Text textGui = (Text) gui;
		assertEquals(textGui.getText(),"this is a text");
		assertEquals(textGui.getX(),0);
		assertEquals(textGui.getY(),0);
	}

	@Test
	void testHyperlinkToGUI() {
		GUIElement gui =converter.toGUI(hyp1,0, 0, 500, 500);
		assertTrue(gui instanceof Hyperlink);
		Hyperlink hyperGui = (Hyperlink) gui;
		assertEquals(hyperGui.getText(),"hyperlink1");
		assertEquals(hyperGui.getUrl(),"hyp1");
		assertEquals(hyperGui.getX(),0);
		assertEquals(hyperGui.getY(),0);
	}
	
	@Test
	void testTableToGUI() {
		GUIElement gui =converter.toGUI(createSmallTable(),0, 0, 500, 500);
		assertTrue(gui instanceof TableGUI);
		TableGUI tableGui = (TableGUI) gui;
		assertEquals(tableGui.getAllColumns().size(),2);
		assertEquals(tableGui.getGuiRows().size(),2);
		assertTrue(tableGui.getGuiRows().get(0).getGUIAtGivenIndex(0) instanceof Text);
		assertTrue(tableGui.getGuiRows().get(0).getGUIAtGivenIndex(1) instanceof Text);
		assertTrue(tableGui.getGuiRows().get(1).getGUIAtGivenIndex(0) instanceof Text);
		assertTrue(tableGui.getGuiRows().get(1).getGUIAtGivenIndex(1) instanceof Text);
		Text textInTable1 = (Text) tableGui.getGuiRows().get(0).getGUIAtGivenIndex(0);
		Text textInTable2 = (Text) tableGui.getGuiRows().get(0).getGUIAtGivenIndex(1);
		Text textInTable3 = (Text) tableGui.getGuiRows().get(1).getGUIAtGivenIndex(0);
		Text textInTable4 = (Text) tableGui.getGuiRows().get(1).getGUIAtGivenIndex(1);
		assertEquals(textInTable1.getText(),"this is a text");
		assertEquals(textInTable2.getText(),"this is a text too");
		assertEquals(textInTable3.getText(),"this is a text");
		assertEquals(textInTable4.getText(),"this is a text too");
	}
	
	@Test 
	void testGenerateMultipleGUI() {
		ArrayList<ContentSpan> list = new ArrayList<ContentSpan>();
		list.add(text1);
		list.add(hyp1);
		list.add(createBigTable());
		list.add(text2);
		ArrayList<GUIElement> listOfGui= converter.transformToGUI(0, 0, 500, 500, list);
		int xspace = HTMLToGUI.getXSPACE();
		int yspace = HTMLToGUI.getYSPACE();
		assertEquals( listOfGui.get(0).getX(),xspace);
		assertEquals( listOfGui.get(0).getY(),0);
		int height= listOfGui.get(0).getHeight();
		assertEquals( listOfGui.get(1).getX(),xspace);
		assertEquals( listOfGui.get(1).getY(),height + yspace);
		height+= listOfGui.get(1).getHeight() + yspace;
		assertEquals( listOfGui.get(2).getX(),xspace);
		assertEquals( listOfGui.get(2).getY(),height + yspace);
		height+= listOfGui.get(2).getHeight() + yspace;
		assertEquals( listOfGui.get(3).getX(),xspace);
		assertEquals( listOfGui.get(3).getY(),height + yspace);
	}
	
}
