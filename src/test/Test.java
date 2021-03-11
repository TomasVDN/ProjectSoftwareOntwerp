package test;

import java.io.File;
import java.util.ArrayList;

import GUIElements.GUIElement;
import canvaswindow.MyCanvasWindow;
import converter.HTMLToGUI;
import domain.InputReader;
import events.Event;
import events.EventReader;
import events.RunUrlEvent;
import htmlElement.ContentSpan;

/**
 * Deze klasse dient voor stuff uit te testen, alles mag verwijderd worden wat hier staat
 * @author kobe
 *
 */
public class Test {
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(() -> {
			 MyCanvasWindow window = new MyCanvasWindow("Browsr");
//			 FileOpenEvent event = new FileOpenEvent(new File("src/hyperlinktest.html"));
//			 EventReader e = EventReader.getInstance();
//			 e.readEvent(event);
			 Event event = new RunUrlEvent("https://konikoko.github.io/");
			 EventReader e = EventReader.getInstance();
			 e.readEvent(event);
 	         window.show();
		});
	}

}
