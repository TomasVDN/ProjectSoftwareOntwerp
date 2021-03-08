package test;

import java.util.ArrayList;

import GUIElements.GUIElement;
import canvaswindow.MyCanvasWindow;
import converter.HTMLToGUI;
import domain.InputReader;
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
	         InputReader input = new InputReader();
	         HTMLToGUI toGui = new HTMLToGUI();
	         ArrayList<ContentSpan> content = input.readFile("");
	         ArrayList<GUIElement> allGUI = toGui.transformToGUI(10, 10, window.getWidth(), window.getHeight(), content);
	         //window.addAllElements(allGUI);
	         //window.show();
		});
	}

}
