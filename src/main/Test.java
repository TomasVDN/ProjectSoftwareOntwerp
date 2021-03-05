package main;

import java.util.ArrayList;

import GUIElements.GUIElement;
import canvaswindow.MyCanvasWindow;
import htmlElement.ContentSpan;
import inputReader.InputReader;
import toNew.HTMLToGUI;

/**
 * Deze klasse dient voor stuff uit te testen, alles mag verwijderd worden wat hier staat
 * @author kobe
 *
 */
public class Test {
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(() -> {
			 MyCanvasWindow window = new MyCanvasWindow("Browsr");
	         InputReader input = new InputReader();
	         HTMLToGUI toGui = new HTMLToGUI(0, 0);
	         ArrayList<ContentSpan> content = input.readFile("");	         
	         ArrayList<GUIElement> allGUI = toGui.transformToGUI(10, 110, window.getWindowManager().getWidth(), window.getWindowManager().getHeight(), content,window.getWindowManager().getFontMetricsHandler());
	         window.getWindowManager().addAllGUIToPage(allGUI);
	         //window.show();
		});
	}

}
