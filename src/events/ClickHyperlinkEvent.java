package events;

import facades.Browsr;

public class ClickHyperlinkEvent implements Event {
	
	private String URLAttribute;
	
	/**
	 * Constructor of the ClickHyperlinkEvent class.
	 * @param URLAttribute - the url to open.
	 */
	public ClickHyperlinkEvent(String URLAttribute) {
		this.URLAttribute = URLAttribute;
	}

	@Override
	public void execute(Browsr browsr) {
		browsr.runUrlAttribute(this.getURLAttribute());
	}

	/**
	 * @return this.URLAttribute
	 */
	public String getURLAttribute() {
		return URLAttribute;
	}

	/**
	 * @param uRLAttribute - the new value of this.URLAttribute
	 */
	public void setURLAttribute(String uRLAttribute) {
		URLAttribute = uRLAttribute;
	}
	
}
