package useCases;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import GUIElements.Container;
import GUIElements.TableGUI;
import GUIElements.TableRowGUI;
import GUIElements.Text;
import canvaswindow.MyCanvasWindow;
import helperFunctions.StringTyping;

class TestFillAndSubmitForm {

	private MyCanvasWindow mainWindow;
	
	@BeforeEach
	void setUp() throws InvocationTargetException, InterruptedException {
		java.awt.EventQueue.invokeAndWait(() -> {
			mainWindow = new MyCanvasWindow("BrowsrController");
		});
	}
	
	@Test
	@DisplayName("Use Case 4.3: Fill and Submit Form")
	// Use Case 4.3
	public void addBookmarkSuccess() throws InvocationTargetException, InterruptedException {
		// check if active dialog is main dialog
		assertEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		assertEquals(null, mainWindow.getWindowManager().getElementWithKeyboardFocus());
		
		// check if right title is shown
		assertEquals("BrowsrController", mainWindow.getTitle());
		
		// make search bar get focus
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 132, 28, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 132, 28, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 132, 28, 1, MouseEvent.BUTTON1, 0);
		
		//got to the correct address
		StringTyping.generateKeyEventsForString(mainWindow, "https://people.cs.kuleuven.be/~bart.jacobs/swop/browsrformtest.html");
		
		// make search bar lose focus
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 200, 200, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 200, 200, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 200, 200, 1, MouseEvent.BUTTON1, 0);
		
		// make select first text box
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 380, 155, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 380, 155, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 380, 155, 1, MouseEvent.BUTTON1, 0);
		
		//fill the text box
		StringTyping.generateKeyEventsForString(mainWindow, "t");
		
		// make select second text box
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 380, 175, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 380, 175, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 380, 175, 1, MouseEvent.BUTTON1, 0);
		
		//fill the text box
		StringTyping.generateKeyEventsForString(mainWindow, "5");
		
		//click submit button
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 55, 200, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 55, 200, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 55, 200, 1, MouseEvent.BUTTON1, 0);
		
		//testing GUI elements
		Container pageContainer = mainWindow.getWindowManager().getMainDialog().getDocumentArea();
		TableGUI pageTable = (TableGUI) (pageContainer.getElements().get(0));
		TableRowGUI pageTableRow1 = pageTable.getGuiRows().get(0);
		TableRowGUI pageTableRow2 = pageTable.getGuiRows().get(1);
		TableRowGUI pageTableRow3 = pageTable.getGuiRows().get(2);
		TableRowGUI pageTableRow4 = pageTable.getGuiRows().get(3);
		TableRowGUI pageTableRow5 = pageTable.getGuiRows().get(4);

				
		Text pageTableRow1Text = (Text) (pageTableRow1.getGuiElements().get(0).getGui());
		Text pageTableRow2Text = (Text) (pageTableRow2.getGuiElements().get(0).getGui());
		Text pageTableRow3Text = (Text) (pageTableRow3.getGuiElements().get(0).getGui());
		Text pageTableRow4Text = (Text) (pageTableRow4.getGuiElements().get(0).getGui());
		Text pageTableRow5Text = (Text) (pageTableRow5.getGuiElements().get(0).getGui());
				
		//check resulting page
		assertEquals("t", pageTableRow1Text.getText());
		assertEquals("t-groep", pageTableRow2Text.getText());
		assertEquals("t.a.v.", pageTableRow3Text.getText());
		assertEquals("t.b.v.", pageTableRow4Text.getText());
		assertEquals("t.g.v.", pageTableRow5Text.getText());


	}

}
