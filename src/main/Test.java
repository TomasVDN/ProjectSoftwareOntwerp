package main;

import java.util.ArrayList;

import GUIElements.GUIElement;
import canvaswindow.MyCanvasWindow;
import htmlElement.ContentSpan;
import domain.InputReader;
import converter.HTMLToGUI;

/**
 * Deze klasse dient voor stuff uit te testen, alles mag verwijderd worden wat hier staat
 * @author kobe
 *test
 */
public class Test {
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(() -> {
			 MyCanvasWindow window = new MyCanvasWindow("Browsr");
	         InputReader input = new InputReader();
	         HTMLToGUI toGui = new HTMLToGUI();
	         ArrayList<ContentSpan> content = input.readFile("");	         
	         //ArrayList<GUIElement> allGUI = toGui.transformToGUI(10, 110, window.getWindowManager().getWidth(), window.getWindowManager().getHeight(),window.getWindowManager().getEventReader(), content);
	         //window.getWindowManager().addAllGUIToPage(allGUI);
	         //window.show();
		});
	}

}
