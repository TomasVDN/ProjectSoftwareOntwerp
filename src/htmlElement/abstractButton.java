package htmlElement;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public abstract class abstractButton extends Box {


	
	//text en button kleur variabelen
	    private Color buttonColor =  Color.BLUE;
	    private Text text;
		
	    /**
	     * Constructor van de button
	     * 
	     * @param x - x coordinaat
	     * @param y - y coordinaat
	     * @param size - groote van de font
	     * @param text - text van de button
	     */
		public abstractButton(int x, int y, int size, Color color, String textButton){
			super(x, y, size, size,color);
			setWidth((3*textButton.length()/4) * 40);
			setHeight(size);	
			int textX = getLeftX() + (getWidth() - metrics.stringWidth(textButton)) / 2;
		    int textY = getLowerY() + ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
		    Text textGuiButton = Text.constructText(textButton,textX, textY,metrics);
		    this.setText(textGuiButton);
		}
		

		
		/**
		 * Set de text van de button
		 * @param text
		 */
		public void setText(Text newText) {
			this.text = newText;
		}
		
		/**
		 * Geeft de text van de button
		 * @return text
		 */
		public Text getText() {
			return this.text;
		}
		

		
		
		/**
		 * Checkt of er een event zich op de button bevindt
		 * @param x - x coordinaat van event
		 * @param y - y coordinaat van event
		 * @return
		 */
		public boolean checkCoordinates(int x, int y) {
			if ((getLeftX() <= x && getRightX() >= x) && (getLowerY() <= y && getUpperY() >= y)) {
				return true;
			} else {
				return false;
			}	
		}
		
		/**
		 * Handelt wat er moet gebeuren tijdens een muisklik
		 * @param id
		 * 	geeft aan wat voor muisevent het is
		 * @param x
		 * 	x coordinaat van het event
		 * @param y
		 * 	y coordinaat van het event
		 */
		abstract public void performAction(int id, int x, int y) ;

		/**
		 * Deze functie tekent de button
		 * 
		 * g - Graphics
		 * 
		 */
		@Override
		public void paint(Graphics g) {
			this.paintBox(g);
			this.getText().paintText(g);
		}

	


}
