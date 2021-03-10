package canvaswindow;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.DisplayName;

import canvaswindow.MyCanvasWindow;
import container.Container;
import htmlElement.ContentSpan;
import htmlElement.HTMLHyperlink;
import htmlElement.HTMLTable;
import htmlElement.HTMLTableRow;
import htmlElement.HTMLText;

import org.junit.jupiter.api.Test;

import GUIElements.Hyperlink;
import GUIElements.TableGUI;
import GUIElements.TableRowGUI;
import GUIElements.Text;

class TestMyCanvasWindow {

	void setUpReference() {
		//nog niks
	}
	
	@Test
	@DisplayName("WindowManager setup test")
	void test() {	
		boolean testSuccess = true;
		try {
			java.awt.EventQueue.invokeAndWait(() -> {
				setUpReference();
//				MyCanvasWindow referenceWindow = new MyCanvasWindow("Test");
				MyCanvasWindow mainWindow = new MyCanvasWindow("Browsr");
				
				int referenceBarContainerHeight = mainWindow.getWindowManager().getBar().getHeight();
				int referenceBarContainerWidth = mainWindow.getWindowManager().getBar().getWidth();
				int referenceBarContainerX = mainWindow.getWindowManager().getBar().getX();
				int referenceBarContainerY = mainWindow.getWindowManager().getBar().getY();
				
				MyCanvasWindow.replayRecording("recordings/recording", mainWindow);
				
				int mainBarContainerHeight = mainWindow.getWindowManager().getBar().getHeight();
				int mainBarContainerWidth = mainWindow.getWindowManager().getBar().getWidth();
				int mainBarContainerX = mainWindow.getWindowManager().getBar().getX();
				int mainBarContainerY = mainWindow.getWindowManager().getBar().getY();
				
				//Get all the main page elements to check
				ContentSpan mainPageFirstContentSpan = mainWindow.getWindowManager().getBrowsr().getDomainFacade().getHtmlElements().get(0);
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

				//checks if the windows are correctly placed, even after searching
				assertEquals(mainBarContainerHeight ,referenceBarContainerHeight);
				assertEquals(mainBarContainerWidth ,referenceBarContainerWidth);
				assertEquals(mainBarContainerX ,referenceBarContainerX);
				assertEquals(mainBarContainerY ,referenceBarContainerY);
				
				//HTML elements correctly created
				assertEquals("CELL: TEXT: HTML elements partially supported by Browsr:", mainPageFirstText);
				
				//hyperlink and table correctly created
				assertEquals(aHTMLHyperlink.getUrl(), mainPageSecondHyperlink1.getUrl());
				assertEquals(aHTMLHyperlink.getText().getText(), mainPageSecondHyperlink1.getText().getText());
				//assertEquals(aHTMLHyperlink.getUrl(), mainPageSecondHyperlink1.getUrl());
				//assertEquals(aHTMLHyperlink.getText().getText(), mainPageSecondHyperlink1.getText().getText());
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
				
				//testing GUI elements
				Container pageContainer = mainWindow.getWindowManager().getPage();
				TableGUI pageTable = (TableGUI) (pageContainer.getElements().get(0));
				TableRowGUI pageTableRow1 = pageTable.getGuiRows().get(0);
				TableRowGUI pageTableRow2 = pageTable.getGuiRows().get(1);
				
				Text pageTableRow1Text = (Text) (pageTableRow1.getGuiElements().get(0).getGui());
				
				TableGUI pageTableRow2Table = (TableGUI) (pageTableRow2.getGuiElements().get(0).getGui());
				
				Hyperlink pageTableRow2TableHyperlink1 = (Hyperlink) (pageTableRow2Table.getGuiRows().get(0).getGuiElements().get(0).getGui());
				Hyperlink pageTableRow2TableHyperlink2 = (Hyperlink) (pageTableRow2Table.getGuiRows().get(1).getGuiElements().get(0).getGui());
				Hyperlink pageTableRow2TableHyperlink3= (Hyperlink) (pageTableRow2Table.getGuiRows().get(2).getGuiElements().get(0).getGui());
				Hyperlink pageTableRow2TableHyperlink4 = (Hyperlink) (pageTableRow2Table.getGuiRows().get(3).getGuiElements().get(0).getGui());

				assertEquals("a.html", pageTableRow2TableHyperlink1.getUrl());
				assertEquals("table.html", pageTableRow2TableHyperlink2.getUrl());
				assertEquals("tr.html", pageTableRow2TableHyperlink3.getUrl());
				assertEquals("td.html", pageTableRow2TableHyperlink4.getUrl());

			  });
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			testSuccess = false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// TODO: Ik wou de cause by in de stacktrace printen ma da werkte nie, geen idee why
			e.printStackTrace();
			testSuccess = false;
		}
		assertEquals(testSuccess, true);
	}
	

}
