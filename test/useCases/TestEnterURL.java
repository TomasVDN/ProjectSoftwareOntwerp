package useCases;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.Ignore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import GUIElements.GUIElement;
import GUIElements.Hyperlink;
import GUIElements.SearchBar;
import GUIElements.TableGUI;
import GUIElements.TableRowGUI;
import GUIElements.Text;
import GUIElements.TextBox;
import canvaswindow.MyCanvasWindow;
import container.Container;

class TestEnterURL {


	@Test
	@DisplayName("Use Case: Enter URL")
	void test() throws InvocationTargetException, InterruptedException {	
			java.awt.EventQueue.invokeAndWait(() -> {
				MyCanvasWindow mainWindow = new MyCanvasWindow("Browsr");
				mainWindow.show();
				
				SearchBar mainBar = mainWindow.getWindowManager().getSearchbar();
				
				int referenceBarContainerHeight = mainWindow.getWindowManager().getBar().getHeight();
				int referenceBarContainerWidth = mainWindow.getWindowManager().getBar().getWidth();
				int referenceBarContainerX = mainWindow.getWindowManager().getBar().getX();
				int referenceBarContainerY = mainWindow.getWindowManager().getBar().getY();
				
				MyCanvasWindow.replayRecording("recordings/recordEnterUrlSuccess/recording", mainWindow);
				
				int mainBarContainerHeight = mainWindow.getWindowManager().getBar().getHeight();
				int mainBarContainerWidth = mainWindow.getWindowManager().getBar().getWidth();
				int mainBarContainerX = mainWindow.getWindowManager().getBar().getX();
				int mainBarContainerY = mainWindow.getWindowManager().getBar().getY();
				
				//checks if the windows are correctly placed, even after searching
				assertEquals(mainBarContainerHeight ,referenceBarContainerHeight);
				assertEquals(mainBarContainerWidth ,referenceBarContainerWidth);
				assertEquals(mainBarContainerX ,referenceBarContainerX);
				assertEquals(mainBarContainerY ,referenceBarContainerY);

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

				Text pageTableRow2TableHyperlink1Text = (Text) (pageTableRow2Table.getGuiRows().get(0).getGuiElements().get(1).getGui());
				Text pageTableRow2TableHyperlink2Text = (Text) (pageTableRow2Table.getGuiRows().get(1).getGuiElements().get(1).getGui());
				Text pageTableRow2TableHyperlink3Text = (Text) (pageTableRow2Table.getGuiRows().get(2).getGuiElements().get(1).getGui());
				Text pageTableRow2TableHyperlink4Text = (Text) (pageTableRow2Table.getGuiRows().get(3).getGuiElements().get(1).getGui());

				//check hyperlink urls
				assertEquals("HTML elements partially supported by Browsr:", pageTableRow1Text.getText());
				assertEquals("a.html", pageTableRow2TableHyperlink1.getUrl());
				assertEquals("table.html", pageTableRow2TableHyperlink2.getUrl());
				assertEquals("tr.html", pageTableRow2TableHyperlink3.getUrl());
				assertEquals("td.html", pageTableRow2TableHyperlink4.getUrl());
				
				//check hyperlink text
				assertEquals("a", pageTableRow2TableHyperlink1.getText().getText());
				assertEquals("table", pageTableRow2TableHyperlink2.getText().getText());
				assertEquals("tr", pageTableRow2TableHyperlink3.getText().getText());
				assertEquals("td", pageTableRow2TableHyperlink4.getText().getText());
				
				//check table text
				assertEquals("Hyperlink anchors", pageTableRow2TableHyperlink1Text.getText());
				assertEquals("Tables", pageTableRow2TableHyperlink2Text.getText());
				assertEquals("Table rows", pageTableRow2TableHyperlink3Text.getText());
				assertEquals("Table cells containing table data", pageTableRow2TableHyperlink4Text.getText());
				
				//check the current active element is null and check the url
				assertEquals(null, mainWindow.getWindowManager().getActiveElement());
				assertEquals("https://people.cs.kuleuven.be/~bart.jacobs/browsrtest.html", mainBar.getText());
				
			  });
	}

}
