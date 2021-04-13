package GUIElements;

import EventListeners.HyperLinkListener;
import events.EventReader;

public class BookmarkHyperlink extends Hyperlink {

	public BookmarkHyperlink(int x, int y, Text text, String url, EventReader eventReader) {
		super(x, y, text, url, eventReader);
	}
	
	@Override
	protected void initiateClickListeners() {
		this.addSingleClickListener(() ->{
			System.out.println("CLICK OP HYPERLINK");
			for(HyperLinkListener listener: this.getHyperListeners()) {
				listener.handleBookmarkHyperLinkClicked(this.getUrl());
			}
		});
	}
}
