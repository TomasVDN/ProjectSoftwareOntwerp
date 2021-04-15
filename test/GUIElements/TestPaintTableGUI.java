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
	Text textGUI = new Text(0, 0, "");
	TableCellGUI cell1 = new TableCellGUI(textGUI, 0, 0, 0, 0);
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
		
		verify(fakeGraphics,atMost(1)).setFont(textGUI.getFont());
		verify(fakeGraphics, atMost(2)).setColor(Color.black);
		verify(fakeGraphics, atMost(1)).setClip(fakeshape);
		verify(fakeGraphics, atMost(1)).drawString(anyString(), anyInt(), anyInt());
		
		container1.paint(fakeGraphics);
		
		verify(fakeGraphics,atMost(2)).setFont(textGUI.getFont());
		verify(fakeGraphics, atMost(4)).setColor(Color.black);
		verify(fakeGraphics, atMost(2)).setClip(fakeshape);
		verify(fakeGraphics, atMost(2)).drawString(anyString(), anyInt(), anyInt());
		
		textbox1.paint(fakeGraphics);
		
		verify(fakeGraphics, atMost(1)).fillRect(anyInt(), anyInt(), anyInt(), anyInt());
		verify(fakeGraphics, atMost(1)).drawRect(anyInt(), anyInt(), anyInt(), anyInt());
		verify(fakeGraphics,atMost(3)).setFont(textGUI.getFont());
		verify(fakeGraphics, atMost(5)).setColor(Color.black);
		verify(fakeGraphics, atMost(3)).setClip(fakeshape);
		verify(fakeGraphics, atMost(3)).drawString(anyString(), anyInt(), anyInt());

				
	}

}
