package useCases;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import GUIElements.Hyperlink;
import GUIElements.SearchBar;
import GUIElements.TableGUI;
import GUIElements.TableRowGUI;
import GUIElements.Text;
import canvaswindow.MyCanvasWindow;
import GUIElements.Container;
import GUIElements.HTMLDocument;


class TestActivateHyperlink {

	private MyCanvasWindow mainWindow;
	
	@BeforeEach
	void setUp() throws InvocationTargetException, InterruptedException {
		java.awt.EventQueue.invokeAndWait(() -> {
			mainWindow = new MyCanvasWindow("BrowsrController");
		});
	}
	
	@Test
	@DisplayName("Use Case 4.1: Activate Hyperlink")
	public void test() {
		//load the page for the test
		//Event event = new RunUrlEvent("https://konikoko.github.io/");
		mainWindow.getWindowManager().getSearchbar().replaceBox("https://konikoko.github.io/");
		mainWindow.getWindowManager().getSearchbar().handleEnter();
		//window.getWindowManager().getEventReader().readEvent(event);

		//get the url bar
		SearchBar mainBar = mainWindow.getWindowManager().getSearchbar();

		//click on the hyperlink (manually)
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 76, 155, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 76, 155, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 76, 155, 1, MouseEvent.BUTTON1, 0);

		//testing GUI elements
		HTMLDocument htmlDocument = mainWindow.getWindowManager().getMainDialog().getActiveHTMLDocument();
		TableGUI pageTable = (TableGUI) (htmlDocument.getElements().get(0));
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
		assertEquals("a", pageTableRow2TableHyperlink1.getText());
		assertEquals("table", pageTableRow2TableHyperlink2.getText());
		assertEquals("tr", pageTableRow2TableHyperlink3.getText());
		assertEquals("td", pageTableRow2TableHyperlink4.getText());
			
		//check table text
		assertEquals("Hyperlink anchors", pageTableRow2TableHyperlink1Text.getText());
		assertEquals("Tables", pageTableRow2TableHyperlink2Text.getText());
		assertEquals("Table rows", pageTableRow2TableHyperlink3Text.getText());
		assertEquals("Table cells containing table data", pageTableRow2TableHyperlink4Text.getText());
		
		//check the current active element is null and check the url
		assertEquals(null, mainWindow.getWindowManager().getElementWithKeyboardFocus());
		assertEquals("https://konikoko.github.io/goodwork.html", mainBar.getText());
	}
}
