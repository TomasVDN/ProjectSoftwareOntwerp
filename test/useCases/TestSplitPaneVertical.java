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

class TestSplitPaneVertical {

private MyCanvasWindow mainWindow;
	
	@BeforeEach
	void setUp() throws InvocationTargetException, InterruptedException {
		java.awt.EventQueue.invokeAndWait(() -> {
			mainWindow = new MyCanvasWindow("BrowsrController");
		});
	}
	
	@Test
	@DisplayName("Use Case 4.7: Split Pane (Vertical)")
	// Use Case 4.7
	public void TestSplitPane() throws InvocationTargetException, InterruptedException {
		
		// check if active dialog is main dialog
		assertEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		assertEquals(null, mainWindow.getWindowManager().getElementWithKeyboardFocus());
		
		//calculate the click coordinates
		HTMLDocument originalDocument = mainWindow.getWindowManager().getMainDialog().getDocumentArea().getActiveHTMLDocument();
		int yHeight = originalDocument.getY() + (originalDocument.getHeight() / 2);
		int xWidthLeftPane = originalDocument.getX() + (originalDocument.getWidth() / 2);
		int xWidthRightPane = ((3 * originalDocument.getWidth()) / 4) + originalDocument.getX();
				
		//click on control H
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 17, '?', 128); 
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 72, 'h', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 72, 'h', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 17, '?', 128);
		
		//click on the left pane
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, xWidthLeftPane, yHeight, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, xWidthLeftPane, yHeight, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, xWidthLeftPane, yHeight, 1, MouseEvent.BUTTON1, 0);
		
		//save the left pane
		HTMLDocument leftPane = mainWindow.getWindowManager().getMainDialog().getDocumentArea().getActiveHTMLDocument();
		
		//click on the right pane
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, xWidthRightPane, yHeight, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, xWidthRightPane, yHeight, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, xWidthRightPane, yHeight, 1, MouseEvent.BUTTON1, 0);
		
		//save the right pane
		HTMLDocument rightPane = mainWindow.getWindowManager().getMainDialog().getDocumentArea().getActiveHTMLDocument();
		
		//check if the panes are different
		assertNotEquals(leftPane, rightPane,"The original pane has not been split correctly");
		
	}
	
}
