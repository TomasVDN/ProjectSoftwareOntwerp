package htmlElement;

public class HTMLHyperlink extends ContentSpan{

	private String url;
	private HTMLText text;
	public HTMLHyperlink(String url, HTMLText text) {
		this.url = url;
		this.text = text;
	}
	
	
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @return the text
	 */
	public HTMLText getText() {
		return text;
	}


	@Override
	public String toString() {
		return "HYPER: (" + url + "," + text.toString() + ")";
	}

}
