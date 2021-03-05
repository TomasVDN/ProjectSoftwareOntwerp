package facades;

import java.awt.Graphics;
import java.util.ArrayList;

import htmlElement.ContentSpan;

//Dit is de controller
public class Browsr {
	
	private DomainFacade domainFacade;
	private WindowManager windowManager;
	
	public Browsr(WindowManager windowManager) {
		domainFacade = new DomainFacade(this);
		this.windowManager = windowManager;
	}

	/**
	 * @return the domainFacade
	 */
	public DomainFacade getDomainFacade() {
		return domainFacade;
	}

	/**
	 * @return the windowManager
	 */
	public WindowManager getWindowManager() {
		return windowManager;
	}

	public void paint(Graphics g) {
		windowManager.paint(g);		
	}

	public void runUrl(String path) {
		System.out.println("\n This should use " + path + "\n");
		domainFacade.runUrl(path);
	}

	public void draw(ArrayList<ContentSpan> htmlElements) {
		windowManager.draw(htmlElements);
	}
	
	

}
