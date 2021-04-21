package GUIElements;

import EventListeners.HyperLinkListener;
import events.EventReader;

public class BookmarkHyperlink extends Hyperlink {

	/**
	 * Constructor of the BookmarkHyperlink class. It extends the Hyperlink class.
	  * @param x - x coordinate of this BookmarkHyperlink
     * @param y - y coordinate of this BookmarkHyperlink
     * @param w - width of this BookmarkHyperlink
     * @param h - height of this BookmarkHyperlink
	 * @param eventReader - eventReader of this BookmarkHyperlink
	 */
	public BookmarkHyperlink(int x, int y, Text text, String url, EventReader eventReader) {
		super(x, y, text, url, eventReader);
	}
	
	@Override
	protected void initiateClickListeners() {
		this.addSingleClickListener(() ->{
			for(HyperLinkListener listener: this.getHyperListeners()) {
				listener.handleBookmarkHyperLinkClicked(this.getUrl());
			}
		});
	}
}
