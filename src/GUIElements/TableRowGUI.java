package GUIElements;

import java.awt.Graphics;
import java.util.ArrayList;

public class TableRowGUI extends GUIElement {

	
	private ArrayList<GUIElement> guiElements;
	
	
	public TableRowGUI(ArrayList<GUIElement> guiElements, int x, int y, int w, int h) {
		super(x, y, w, h);
		this.setGuiElements(guiElements);
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<GUIElement> getGuiElements() {
		return guiElements;
	}

	public void setGuiElements(ArrayList<GUIElement> guiElements) {
		this.guiElements = guiElements;
	}



	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Zoek naar de grootste hoogte van zijn elementen
	 */
	@Override
	public int getHeight(){
		int maxHeight=0;
		for(int i=0; i<this.getGuiElements().size();i++) {
			int height = this.getGuiElements().get(i).getHeight();
			if(height>maxHeight) {
				maxHeight=height;
			}
		}
		return maxHeight;
	}
	
	/**
	 * Telt de widths van elke gui op, deze moeten even groot zijn als de grootste GUI in de rij
	 */
	@Override
	public int getWidth(){
		return this.getGuiElements().get(0).getWidth()*this.getGuiElements().size();
	}
	

}
