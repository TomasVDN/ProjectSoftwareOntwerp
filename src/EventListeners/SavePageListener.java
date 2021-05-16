package EventListeners;

/**
 * Interface that implements the savePage method for the EventListener class.
 */
public interface SavePageListener extends EventListener {

	/**
	 * Saves the htmlCode to the given file
	 * @param filename - The name of the file where to save
	 * @param htmlCode - The string of the html
	 */
	void savePage(String filename,String htmlCode);
}
