package GUIElements;

import events.ClickBookmarkHyperlinkEvent;
import events.Event;
import events.EventReader;

public class BookmarkHyperlink extends Hyperlink {

	public BookmarkHyperlink(int x, int y, Text text, String url, EventReader eventReader) {
		super(x, y, text, url, eventReader);
	}
	
	/**
	 * If clicked, send a runUrlEvent
	 */
	@Override
	public void handleClick() {
		Event event = new ClickBookmarkHyperlinkEvent(this.getUrl());
		this.eventReader.readEvent(event);
	}

}
