package GUIElements;

import EventListeners.HyperLinkListener;

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
	 * Initiate the click listeners.
	 */
	@Override
	protected void initiateClickListeners() {
		this.addSingleClickListener(() ->{
			for(HyperLinkListener listener: this.getHyperListeners()) {
				listener.runUrl(this.getUrl());
			}
		});
	}
}
