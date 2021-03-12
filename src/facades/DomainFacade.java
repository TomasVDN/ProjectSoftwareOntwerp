package facades;

import java.util.ArrayList;

import domain.InputReader;
import htmlElement.ContentSpan;

public class DomainFacade {
	

	/**
	 * Empty constructor for the Domain Facade. Used to keep track in Browsr.
	 */
	public DomainFacade() {
	}

	/**
	 * Makes a new Inputreader to process the URL. Transfers the returned list of HTMLElements to caller.
	 * @param path - URL to process
	 * @return ArrayList with processed HTMLElements.
	 */
	public  ArrayList<ContentSpan> runUrl(String path) {
		InputReader reader = new InputReader();
		ArrayList<ContentSpan> htmlElements = reader.readFile(path);
		return htmlElements;
	}
}
