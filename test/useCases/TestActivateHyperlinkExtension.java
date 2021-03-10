package useCases;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.MouseEvent;
import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import GUIElements.Hyperlink;
import GUIElements.SearchBar;
import GUIElements.TableGUI;
import GUIElements.TableRowGUI;
import GUIElements.Text;
import canvaswindow.MyCanvasWindow;
import container.Container;
import events.EventReader;
import events.FileOpenEvent;

class TestActivateHyperlinkExtension {

	private MyCanvasWindow window;
	
	@BeforeEach
	void setUp() throws InvocationTargetException, InterruptedException {
		java.awt.EventQueue.invokeAndWait(() -> {
			window = new MyCanvasWindow("Browsr");
		});
	}
	
	@Test
	void test() {
		FileOpenEvent event = new FileOpenEvent(new File("src/hyperlinktest.html"));
		EventReader e = EventReader.getInstance();
		e.readEvent(event);
		
		SearchBar mainBar = window.getWindowManager().getSearchbar();

		window.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 56, 136, 1, MouseEvent.BUTTON1, 0);
		window.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 56, 136, 1, MouseEvent.BUTTON1, 0);
		window.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 56, 136, 1, MouseEvent.BUTTON1, 0);

		Container pageContainer = window.getWindowManager().getPage();
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
			
		//check bar active and url
		assertEquals(null, window.getWindowManager().getActiveElement());
		assertEquals("https://people.cs.kuleuven.be/~bart.jacobs/browsrtest.html", mainBar.getText());
		
	}

}
