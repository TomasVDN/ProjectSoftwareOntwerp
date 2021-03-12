package facades;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import htmlElement.ContentSpan;
import htmlElement.HTMLHyperlink;
import htmlElement.HTMLTable;
import htmlElement.HTMLTableRow;
import htmlElement.HTMLText;

class TestDomainFacade {

	@Test
	void test() {

		DomainFacade mainDomainFacade = new DomainFacade();
		ContentSpan mainPageFirstContentSpan = mainDomainFacade.runUrl("https://people.cs.kuleuven.be/~bart.jacobs/browsrtest.html").get(0);
		
		String mainPageFirstText = "";
		String mainPageSecondHyperlink1Text = "";
		String mainPageSecondHyperlink2Text = "";
		String mainPageSecondHyperlink3Text = "";
		String mainPageSecondHyperlink4Text = "";

		HTMLHyperlink mainPageSecondHyperlink1 = null;
		HTMLHyperlink mainPageSecondHyperlink2 = null;
		HTMLHyperlink mainPageSecondHyperlink3 = null;
		HTMLHyperlink mainPageSecondHyperlink4 = null;

		if (mainPageFirstContentSpan instanceof HTMLTable) {
			HTMLTableRow mainPageFirstContentSpanRow = ((HTMLTable) mainPageFirstContentSpan).getRows().get(0);	
			HTMLTableRow mainPageSecondContentSpanRow = ((HTMLTable) mainPageFirstContentSpan).getRows().get(1);	
			mainPageFirstText = (((HTMLTable) mainPageFirstContentSpan).getRows().get(0).getcells().get(0)).toString();
			
			HTMLTable mainPageImbeddedTable = ((HTMLTable) (mainPageSecondContentSpanRow.getcells().get(0)).getContent());
			
			mainPageSecondHyperlink1 = (HTMLHyperlink) (mainPageImbeddedTable.getRows().get(0)).getcells().get(0).getContent();
			mainPageSecondHyperlink1Text = (mainPageImbeddedTable.getRows().get(0)).getcells().get(1).getContent().toString();
			
			mainPageSecondHyperlink2 = (HTMLHyperlink) (mainPageImbeddedTable.getRows().get(1)).getcells().get(0).getContent();
			mainPageSecondHyperlink2Text = (mainPageImbeddedTable.getRows().get(1)).getcells().get(1).getContent().toString();
			
			mainPageSecondHyperlink3 = (HTMLHyperlink) (mainPageImbeddedTable.getRows().get(2)).getcells().get(0).getContent();
			mainPageSecondHyperlink3Text = (mainPageImbeddedTable.getRows().get(2)).getcells().get(1).getContent().toString();
			
			mainPageSecondHyperlink4 = (HTMLHyperlink) (mainPageImbeddedTable.getRows().get(3)).getcells().get(0).getContent();
			mainPageSecondHyperlink4Text = (mainPageImbeddedTable.getRows().get(3)).getcells().get(1).getContent().toString();
		  }
		
		//Build all references
		HTMLText aHTMLText = new HTMLText("a");
		HTMLHyperlink aHTMLHyperlink = new HTMLHyperlink("a.html", aHTMLText);
		
		HTMLText tableHTMLText = new HTMLText("table");
		HTMLHyperlink tableHTMLHyperlink = new HTMLHyperlink("table.html", tableHTMLText);
		
		HTMLText trHTMLText = new HTMLText("tr");
		HTMLHyperlink trHTMLHyperlink = new HTMLHyperlink("tr.html", trHTMLText);
		
		HTMLText tdHTMLText = new HTMLText("td");
		HTMLHyperlink tdHTMLHyperlink = new HTMLHyperlink("td.html", tdHTMLText);
		
		//HTML elements correctly created
		assertEquals("CELL: TEXT: HTML elements partially supported by Browsr:", mainPageFirstText);
		
		//hyperlink and table correctly created
		assertEquals(aHTMLHyperlink.getUrl(), mainPageSecondHyperlink1.getUrl());
		assertEquals(aHTMLHyperlink.getText().getText(), mainPageSecondHyperlink1.getText().getText());
		assertEquals("TEXT: Hyperlink anchors", mainPageSecondHyperlink1Text);
		
		assertEquals(tableHTMLHyperlink.getUrl(), mainPageSecondHyperlink2.getUrl());
		assertEquals(tableHTMLHyperlink.getText().getText(), mainPageSecondHyperlink2.getText().getText());
		assertEquals("TEXT: Tables", mainPageSecondHyperlink2Text);
		
		assertEquals(trHTMLHyperlink.getUrl(), mainPageSecondHyperlink3.getUrl());
		assertEquals(trHTMLHyperlink.getText().getText(), mainPageSecondHyperlink3.getText().getText());
		assertEquals("TEXT: Table rows", mainPageSecondHyperlink3Text);
		
		assertEquals(tdHTMLHyperlink.getUrl(), mainPageSecondHyperlink4.getUrl());
		assertEquals(tdHTMLHyperlink.getText().getText(), mainPageSecondHyperlink4.getText().getText());
		assertEquals("TEXT: Table cells containing table data", mainPageSecondHyperlink4Text);

	
	}

}
