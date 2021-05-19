package useCases;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import GUIElements.Button;
import GUIElements.GUIElement;
import GUIElements.HTMLDocument;
import GUIElements.SaveDialog;
import GUIElements.ScrollableTextBox;
import GUIElements.Text;
import GUIElements.TextBox;
import canvaswindow.MyCanvasWindow;
import helperFunctions.StringTyping;

class TestSplitPaneHorizontal {

private MyCanvasWindow mainWindow;
	
	@BeforeEach
	void setUp() throws InvocationTargetException, InterruptedException {
		java.awt.EventQueue.invokeAndWait(() -> {
			mainWindow = new MyCanvasWindow("BrowsrController");
			mainWindow.show();

		});
	}
	
	@Test
	@DisplayName("Use Case 4.7: Split Pane (Horizontal)")
	// Use Case 4.7
	public void TestSplitPane() throws InvocationTargetException, InterruptedException {
		// check if active dialog is main dialog
		assertEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		assertEquals(null, mainWindow.getWindowManager().getElementWithKeyboardFocus());
		
		//calculate the click coordinates
		HTMLDocument originalDocument = mainWindow.getWindowManager().getMainDialog().getDocumentArea().getActiveHTMLDocument();
		int xWidth = originalDocument.getX() + (originalDocument.getWidth() / 2);
		int yHeightUpperPane = originalDocument.getY() + (originalDocument.getHeight() / 2);
		int yHeightLowerPane = (originalDocument.getHeight()) + originalDocument.getY();
				
		//click on control V
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 17, '?', 128); 
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 86, 'v', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 86, 'v', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 17, '?', 128);
		
		//click on the Upper pane
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, xWidth, yHeightUpperPane, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, xWidth, yHeightUpperPane, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, xWidth, yHeightUpperPane, 1, MouseEvent.BUTTON1, 0);
		
		//save the Upper pane
		HTMLDocument UpperPane = mainWindow.getWindowManager().getMainDialog().getDocumentArea().getActiveHTMLDocument();
		
		//click on the Lower pane
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, xWidth, yHeightLowerPane, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, xWidth, yHeightLowerPane, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, xWidth, yHeightLowerPane, 1, MouseEvent.BUTTON1, 0);
		
		//save the Lower pane
		HTMLDocument LowerPane = mainWindow.getWindowManager().getMainDialog().getDocumentArea().getActiveHTMLDocument();
		
		//check if the panes are different
		assertNotEquals(UpperPane, LowerPane,"The original pane has not been split correctly");
		
	}
	
}
