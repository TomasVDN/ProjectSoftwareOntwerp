package facades;

import java.util.ArrayList;

import domain.InputReader;
import htmlElement.ContentSpan;

public class DomainFacade {
	
	private ArrayList<ContentSpan> htmlElements = new ArrayList<ContentSpan>();

	public DomainFacade(Browsr browsr) {
	}
	

	/**
	 * @return the htmlElements
	 */
	public ArrayList<ContentSpan> getHtmlElements() {
		return htmlElements;
	}

	/**
	 * @param htmlElements the htmlElements to set
	 */
	public void setHtmlElements(ArrayList<ContentSpan> htmlElements) {
		this.htmlElements = htmlElements;
	}



	public  ArrayList<ContentSpan> runUrl(String path) {
		InputReader reader = new InputReader();
		htmlElements = reader.readFile(path);
		return htmlElements;
	}
}
