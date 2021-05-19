package GUIElements;

/**
 * Hyperlink like class with overrided click handler.
 */
public class BookmarkHyperlink extends Hyperlink {

	/**
	 * Constructor of the BookmarkHyperlink class. It extends the Hyperlink class.
	 * @param x - x coordinate of this BookmarkHyperlink
     * @param y - y coordinate of this BookmarkHyperlink
     * @param text - text of this bookmarkHyperlink
     * @param url - URL of this BookmarkHyperlink
	 */
	public BookmarkHyperlink(int x, int y, Text text, String url) {
		super(x, y, text, url);
	}
	
	/**
	 * Handle click on bookmark.
	 */
	@Override
	public void handleClick() {
		getHyperListeners().forEach(listener -> listener.runUrl(this.getUrl()));
	}
}
