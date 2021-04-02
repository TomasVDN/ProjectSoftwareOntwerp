package useCases;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.event.KeyEvent;
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

public class TestEnterUrlNoRecording {

	private MyCanvasWindow mainWindow;
	
	@BeforeEach
	void setUp() throws InvocationTargetException, InterruptedException {
		java.awt.EventQueue.invokeAndWait(() -> {
			mainWindow = new MyCanvasWindow("Browsr");
		});
	}
	
	@Test
	@DisplayName("Use Case 4.2: Main Success Scenario")
	// Use Case 4.2
	public void enterUrlNoRecording() throws InvocationTargetException, InterruptedException {
		assertTrue(!mainWindow.getWindowManager().getSearchbar().isActive());
		
		// make search bar get focus
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 132, 28, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 132, 28, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 132, 28, 1, MouseEvent.BUTTON1, 0);
		
		assertTrue(mainWindow.getWindowManager().getSearchbar().isActive());
		
		// put some text in search bar
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 72, 'h', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 84, 't', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 84, 't', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 80, 'p', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 83, 's', 0);
		
		// make search bar lose focus
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 132, 300, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 132, 300, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 132, 300, 1, MouseEvent.BUTTON1, 0);
		
		assertTrue(!mainWindow.getWindowManager().getSearchbar().isActive());
		assertEquals("https", mainWindow.getWindowManager().getSearchbar().getText());
		
		// Step 4.2.1
		// make search bar get focus
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 132, 28, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 132, 28, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 132, 28, 1, MouseEvent.BUTTON1, 0);
		
		// Step 4.2.2
		assertTrue(mainWindow.getWindowManager().getSearchbar().isActive());
		assertEquals("https", mainWindow.getWindowManager().getSearchbar().getSelectedText());
		
		// Step 4.2.3
		// put some text in search bar
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 72, 'h', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 84, 't', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 84, 't', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 80, 'p', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 513, '/', 0);
		
		// press left key
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 37, '\0', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 37, '\0', 0);
		
		// check if cursor moves correctly
		assertEquals("http", mainWindow.getWindowManager().getSearchbar().getLeftText());
		assertEquals("/", mainWindow.getWindowManager().getSearchbar().getRightText());
		
		// put some text in search bar
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 83, 's', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 513, ':', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 513, '/', 0);
		
		// press right key
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 39, '\0', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 39, '\0', 0);
		
		// put some text in search bar
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 80, 'p', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 69, 'e', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 79, 'o', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 80, 'p', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 76, 'l', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 69, 'e', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 59, '.', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 67, 'c', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 83, 's', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 59, '.', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 75, 'k', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 85, 'u', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 76, 'l', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 69, 'e', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 85, 'u', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 86, 'v', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 69, 'e', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 78, 'n', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 59, '.', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 66, 'b', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 69, 'e', 0);
		
		// Step 4.2.4
		// check if text gets updated
		assertEquals("https://people.cs.kuleuven.be", mainWindow.getWindowManager().getSearchbar().getText());
		
		// make search bar lose focus
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 132, 300, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 132, 300, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 132, 300, 1, MouseEvent.BUTTON1, 0);
		
		// type "hello"
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 72, 'h', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 69, 'e', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 76, 'l', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 76, 'l', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 79, 'o', 0);
		
		// check if text in search bar is unaffected
		assertEquals("https://people.cs.kuleuven.be", mainWindow.getWindowManager().getSearchbar().getText());
		
		// make search bar get focus
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 132, 28, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 132, 28, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 132, 28, 1, MouseEvent.BUTTON1, 0);
		
		// check if previous text is selected
		assertEquals("https://people.cs.kuleuven.be", mainWindow.getWindowManager().getSearchbar().getSelectedText());
		
		// press again to unselect all text in search bar to keep editing the text
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 132, 28, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 132, 28, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 132, 28, 1, MouseEvent.BUTTON1, 0);
		
		// check if previous text is unselected now 
		assertEquals("", mainWindow.getWindowManager().getSearchbar().getSelectedText());
		
		// keep editing text
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 513, '/', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 131, "~".charAt(0), 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 66, 'b', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 65, 'a', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 82, 'r', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 84, 't', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 59, '.', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 74, 'j', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 65, 'a', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 67, 'c', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 79, 'o', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 66, 'b', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 83, 's', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 513, '/', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 66, 'b', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 82, 'r', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 79, 'o', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 87, 'w', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 83, 's', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 82, 'r', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 84, 't', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 68, 'e', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 83, 's', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 84, 't', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 59, '.', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 72, 'h', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 84, 't', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 77, 'm', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 76, 'l', 0);

		assertEquals("https://people.cs.kuleuven.be/~bart.jacobs/browsrtest.html", mainWindow.getWindowManager().getSearchbar().getText());
		
		// Step 4.2.5
		// press enter
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 10, '\n', 0);
		
		// Step 4.2.6
		// check if everything gets loaded correctly
		SearchBar mainBar = mainWindow.getWindowManager().getSearchbar();
		
		int referenceBarContainerHeight = mainWindow.getWindowManager().getMainPage().getSearchBarContainer().getHeight();
		int referenceBarContainerWidth = mainWindow.getWindowManager().getMainPage().getSearchBarContainer().getWidth();
		int referenceBarContainerX = mainWindow.getWindowManager().getMainPage().getSearchBarContainer().getX();
		int referenceBarContainerY = mainWindow.getWindowManager().getMainPage().getSearchBarContainer().getY();
				
		int mainBarContainerHeight = mainWindow.getWindowManager().getMainPage().getSearchBarContainer().getHeight();
		int mainBarContainerWidth = mainWindow.getWindowManager().getMainPage().getSearchBarContainer().getWidth();
		int mainBarContainerX = mainWindow.getWindowManager().getMainPage().getSearchBarContainer().getX();
		int mainBarContainerY = mainWindow.getWindowManager().getMainPage().getSearchBarContainer().getY();
		
		//checks if the windows are correctly placed, even after searching
		assertEquals(mainBarContainerHeight ,referenceBarContainerHeight);
		assertEquals(mainBarContainerWidth ,referenceBarContainerWidth);
		assertEquals(mainBarContainerX ,referenceBarContainerX);
		assertEquals(mainBarContainerY ,referenceBarContainerY);

		//testing GUI elements
		Container pageContainer = mainWindow.getWindowManager().getMainPage().getPageContainer();
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
	}
}
