package facades;

import java.util.ArrayList;

import domain.InputReader;
import htmlElement.ContentSpan;

public class DomainFacade {
	
	private ArrayList<ContentSpan> htmlElements = new ArrayList<ContentSpan>();

	public DomainFacade(Browsr browsr) {
	}
	

	/**
	 * @return this.htmlElements
	 */
	public ArrayList<ContentSpan> getHtmlElements() {
		return htmlElements;
	}

	/**
	 * @param htmlElements - the htmlElements to set
	 */
	public void setHtmlElements(ArrayList<ContentSpan> htmlElements) {
		this.htmlElements = htmlElements;
	}

	/**
	 * Makes a new Inputreader to process the URL. Transfers the returned list of HTMLElements to caller.
	 * @param path - URL to process
	 * @return ArrayList with processed HTMLElements.
	 */
	public  ArrayList<ContentSpan> runUrl(String path) {
		InputReader reader = new InputReader();
		htmlElements = reader.readFile(path);
		return htmlElements;
	}
}
