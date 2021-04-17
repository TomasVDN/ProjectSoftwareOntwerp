package facades;

import java.util.ArrayList;

import domain.InputReader;
import domain.Saver;
import htmlElement.ContentSpan;

public class DomainFacade {
	
	private Saver saver;

	/**
	 * Empty constructor for the Domain Facade. Used to keep track in Browsr.
	 */
	public DomainFacade() {
		this.saver = new Saver();
	}

	/**
	 * Makes a new Inputreader to process the URL. Transfers the returned list of HTMLElements to caller.
	 * @param path - URL to process
	 * @return ArrayList with processed HTMLElements.
	 */
	public  ArrayList<ContentSpan> runUrl(String path) {
		InputReader reader = new InputReader();
		ArrayList<ContentSpan> htmlElements = reader.readFile(path, saver);
		return htmlElements;
	}
	
	/**
	 * Asks this.saver to save the last loaded HTML code.
	 * @param filename - file name under which to save the HTML code.
	 */
	public void savePage(String filename) {
		saver.saveToFile(filename);
	}
}
