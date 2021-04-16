package useCases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Shape;
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
import events.Event;


public class TestGUI {

	public TestGUI(MyCanvasWindow window, String test) {
		
		
		int fillrectmax = 0;
		int drawRectmax = 0;
		int setFontmax = 0;
		int setColormax = 0;
		int setClipmax = 0;
		int drawStringmax = 0;
		if (test == "TestEnterUrl") {
			fillrectmax = 1;
			drawRectmax = 1;
			setFontmax = 12;
			setColormax = 18;
			setClipmax = 12;
			drawStringmax = 12;
		}
		
		FontMetrics metrics = mock(FontMetrics.class);
        when(metrics.stringWidth(any())).thenReturn(30);
        when(metrics.getHeight()).thenReturn(20);
        when(metrics.getAscent()).thenReturn(5);
        
        Shape fakeshape = mock(Shape.class);
        
		Graphics fakeGraphics= mock(Graphics.class);
		when(fakeGraphics.getFontMetrics(any())).thenReturn(metrics);
		when(fakeGraphics.getClip()).thenReturn(fakeshape);
				
		window.getWindowManager().paint(fakeGraphics, 600, 1000);

		verify(fakeGraphics, times(fillrectmax)).fillRect(anyInt(), anyInt(), anyInt(), anyInt());
		verify(fakeGraphics, times(drawRectmax)).drawRect(anyInt(), anyInt(), anyInt(), anyInt());
		verify(fakeGraphics, times(drawStringmax)).drawString(anyString(), anyInt(), anyInt());

	}
}
