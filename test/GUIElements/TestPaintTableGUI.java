package GUIElements;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Shape;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import events.EventReader;
import facades.Browsr;

import static org.mockito.Mockito.*;

class TestPaintTableGUI {
	// test
	Text textGUI = new Text(0, 0, "test");
	TableCellGUI cell1 = new TableCellGUI(textGUI, 10, 10, 50, 50);
	Container container1 = new Container(0,0,0,0);
	TextBox textbox1 = new TextBox(0,0,0,0);

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
		
		//verify(fakeGraphics, atMost(1)).setColor(textGUI.getColor());
		verify(fakeGraphics, times(1)).drawString(eq("test"), anyInt(), anyInt());
		
		container1.paint(fakeGraphics);
		
		verify(fakeGraphics,  times(2)).drawString(anyString(), anyInt(), anyInt());
		
		textbox1.paint(fakeGraphics);
		
		verify(fakeGraphics,  times(1)).fillRect(anyInt(), anyInt(), anyInt(), anyInt());
		verify(fakeGraphics,  times(1)).drawRect(anyInt(), anyInt(), anyInt(), anyInt());
		verify(fakeGraphics,  times(3)).drawString(anyString(), anyInt(), anyInt());

				
	}

}
