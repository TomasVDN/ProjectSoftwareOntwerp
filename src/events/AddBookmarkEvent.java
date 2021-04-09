package events;

import facades.Browsr;

public class AddBookmarkEvent implements Event{

	private String bookmarkHyperlinkName;
	private String bookmarkHyperlinkUrl;
	
	public AddBookmarkEvent(String bookmarkHyperlinkName, String bookmarkHyperlinkUrl) {
		this.bookmarkHyperlinkName = bookmarkHyperlinkName;
		this.bookmarkHyperlinkUrl = bookmarkHyperlinkUrl;
	}

	@Override
	public void execute(Browsr browsr) {
		browsr.addBookmark(this.bookmarkHyperlinkName, this.bookmarkHyperlinkUrl);
	}
}
