package GUIElements;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Shape;
import org.junit.jupiter.api.Test;

import GUIElements.TestForm.FormListenerClass;
import static org.mockito.Mockito.*;

class TestPaintTableGUI {
	// test
	Text textGUI = new Text(0, 0, "test");
	TableCellGUI cell1 = new TableCellGUI(textGUI, 10, 10, 50, 50);
	Container container1 = new Container(0,0,0,0);
	TextBox textbox1 = new TextBox(0,0,0,0);
	Text textOfButton = new Text(0, 0, "click");
	Button button1 = new Button(0, 0,textOfButton,true ,Color.black);
	FormListenerClass formListener = new FormListenerClass();
	Form streetForm = new Form(container1, 0, 0, "searchAdress");


	@Test
	void testPaint() {
		container1.addElement(cell1);
		
		FontMetrics metrics = mock(FontMetrics.class);
        when(metrics.stringWidth(any())).thenReturn(30);
        when(metrics.getHeight()).thenReturn(20);
        when(metrics.getAscent()).thenReturn(5);
        
        Shape fakeshape = mock(Shape.class);
        
		Graphics fakeGraphics= mock(Graphics.class);
		when(fakeGraphics.getFontMetrics(any())).thenReturn(metrics);
		when(fakeGraphics.getClip()).thenReturn(fakeshape);
		
		cell1.paint(fakeGraphics);
		
		verify(fakeGraphics, times(1)).drawString(eq("test"), anyInt(), anyInt());
		
		container1.paint(fakeGraphics);
		
		verify(fakeGraphics,  times(2)).drawString(anyString(), anyInt(), anyInt());
		
		textbox1.setActive(true);
		textbox1.setSelectedText("test");
		textbox1.paint(fakeGraphics);
		
		verify(fakeGraphics,  times(3)).fillRect(anyInt(), anyInt(), anyInt(), anyInt());
		verify(fakeGraphics,  times(1)).drawRect(anyInt(), anyInt(), anyInt(), anyInt());
		verify(fakeGraphics,  times(3)).drawString(anyString(), anyInt(), anyInt());
		
		button1.paint(fakeGraphics);
		
		verify(fakeGraphics,  times(4)).fillRect(anyInt(), anyInt(), anyInt(), anyInt());
		verify(fakeGraphics,  times(2)).drawRect(anyInt(), anyInt(), anyInt(), anyInt());
		verify(fakeGraphics,  times(4)).drawString(anyString(), anyInt(), anyInt());
		
		streetForm.paint(fakeGraphics);

		verify(fakeGraphics,  times(5)).drawString(anyString(), anyInt(), anyInt());
		
	}

}
