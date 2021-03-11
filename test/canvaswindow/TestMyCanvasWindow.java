package canvaswindow;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.DisplayName;

import container.Container;

import org.junit.jupiter.api.Test;

import GUIElements.Hyperlink;
import GUIElements.TableGUI;
import GUIElements.TableRowGUI;
import GUIElements.Text;

class TestMyCanvasWindow {

//	@Test
//	@DisplayName("WindowManager setup test")
//	void test() throws InvocationTargetException, InterruptedException {	
//			java.awt.EventQueue.invokeAndWait(() -> {
//				MyCanvasWindow mainWindow = new MyCanvasWindow("Browsr");
//				
//				int referenceBarContainerHeight = mainWindow.getWindowManager().getBar().getHeight();
//				int referenceBarContainerWidth = mainWindow.getWindowManager().getBar().getWidth();
//				int referenceBarContainerX = mainWindow.getWindowManager().getBar().getX();
//				int referenceBarContainerY = mainWindow.getWindowManager().getBar().getY();
//				
//				MyCanvasWindow.replayRecording("recordings/recording", mainWindow);
//				
//				int mainBarContainerHeight = mainWindow.getWindowManager().getBar().getHeight();
//				int mainBarContainerWidth = mainWindow.getWindowManager().getBar().getWidth();
//				int mainBarContainerX = mainWindow.getWindowManager().getBar().getX();
//				int mainBarContainerY = mainWindow.getWindowManager().getBar().getY();
//				
//				//checks if the windows are correctly placed, even after searching
//				assertEquals(mainBarContainerHeight ,referenceBarContainerHeight);
//				assertEquals(mainBarContainerWidth ,referenceBarContainerWidth);
//				assertEquals(mainBarContainerX ,referenceBarContainerX);
//				assertEquals(mainBarContainerY ,referenceBarContainerY);
//
//				//testing GUI elements
//				Container pageContainer = mainWindow.getWindowManager().getPage();
//				TableGUI pageTable = (TableGUI) (pageContainer.getElements().get(0));
//				TableRowGUI pageTableRow1 = pageTable.getGuiRows().get(0);
//				TableRowGUI pageTableRow2 = pageTable.getGuiRows().get(1);
//				
//				Text pageTableRow1Text = (Text) (pageTableRow1.getGuiElements().get(0).getGui());
//				
//				TableGUI pageTableRow2Table = (TableGUI) (pageTableRow2.getGuiElements().get(0).getGui());
//				
//				Hyperlink pageTableRow2TableHyperlink1 = (Hyperlink) (pageTableRow2Table.getGuiRows().get(0).getGuiElements().get(0).getGui());
//				Hyperlink pageTableRow2TableHyperlink2 = (Hyperlink) (pageTableRow2Table.getGuiRows().get(1).getGuiElements().get(0).getGui());
//				Hyperlink pageTableRow2TableHyperlink3= (Hyperlink) (pageTableRow2Table.getGuiRows().get(2).getGuiElements().get(0).getGui());
//				Hyperlink pageTableRow2TableHyperlink4 = (Hyperlink) (pageTableRow2Table.getGuiRows().get(3).getGuiElements().get(0).getGui());
//
//				assertEquals("a.html", pageTableRow2TableHyperlink1.getUrl());
//				assertEquals("table.html", pageTableRow2TableHyperlink2.getUrl());
//				assertEquals("tr.html", pageTableRow2TableHyperlink3.getUrl());
//				assertEquals("td.html", pageTableRow2TableHyperlink4.getUrl());
//				
//			  });
//	}
	

}
