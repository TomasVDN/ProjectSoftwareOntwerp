package GUIElements;

public class HTMLDocument extends Panel {
	
	private String url;
	private String HTMLCode;
	private boolean isActiveHTMLDocument;

	public HTMLDocument(int x, int y, int w, int h, String url, String HTMLCode) {
		super(x, y, w, h);
		this.setUrl(url);
		this.setHTMLCode(HTMLCode);
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the hTMLCode
	 */
	public String getHTMLCode() {
		return HTMLCode;
	}

	/**
	 * @param hTMLCode the hTMLCode to set
	 */
	public void setHTMLCode(String hTMLCode) {
		HTMLCode = hTMLCode;
	}

	@Override
	public Panel getActiveHTMLDocument() {
		return this;
	}

	@Override
	public void changeActiveHTMLDocument(int x, int y) {
		this.isActiveHTMLDocument = true;
	}

	@Override
	public void resetActiveHTMLDocument() {
		this.isActiveHTMLDocument = false;
	}

	@Override
	public Panel splitActiveHTMLDocument() {
		return new SplitHTMLDocument(this);
	}

	@Override
	public Panel deleteActiveHTMLDocument() {
		return null;
	}
	
}
