package facades;

import java.util.ArrayList;

import domain.InputReader;
import htmlElement.ContentSpan;

public class DomainFacade {
	
	private Browsr browsr;
	private ArrayList<ContentSpan> htmlElements = new ArrayList<ContentSpan>(); //TODO nog nodig?

	public DomainFacade(Browsr browsr) {
		this.browsr = browsr;
	}
	
	/**
	 * @return the browsr
	 */
	public Browsr getBrowsr() {
		return browsr;
	}

	/**
	 * @param browsr the browsr to set
	 */
	public void setBrowsr(Browsr browsr) {
		this.browsr = browsr;
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



	public void runUrl(String path) {
		InputReader reader = new InputReader();
		htmlElements = reader.readFile(path);
		browsr.draw(htmlElements);
	}
}
