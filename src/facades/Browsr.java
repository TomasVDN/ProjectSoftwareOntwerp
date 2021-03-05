package facades;

import java.awt.Graphics;

import GUIElements.Button;
import GUIElements.Text;
import container.Container;

//Dit is de controller
public class Browsr {
	
	private DomainFacade domainFacade;
	private WindowManager windowManager;
	
	public Browsr(WindowManager windowManager) {
		domainFacade = new DomainFacade();
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

	//TODO
	public void runUrl() {
//		System.out.println("\n This should use the url \n");
//		Container c = new Container(0, 100, 500, 600);
//		windowManager.addToListOfContainers(c);
//		Button b = new Button(10, 10, 100, 100, "hello", true);
//		c.addElement(b);
//		b.addClickListener(() -> {System.out.print("\nThis button works\n\n");});
//		
	}
	
	

}
