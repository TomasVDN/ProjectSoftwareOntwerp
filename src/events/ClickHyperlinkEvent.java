package events;

import facades.Browsr;

public class ClickHyperlinkEvent implements Event {
	
	private String URLAttribute;
	
	public ClickHyperlinkEvent(String URLAttribute) {
		this.URLAttribute = URLAttribute;
	}

	@Override
	public void execute(Browsr browsr) {
		browsr.handleHyperlink(this.getURLAttribute());
	}

	
	public String getURLAttribute() {
		return URLAttribute;
	}

	public void setURLAttribute(String uRLAttribute) {
		URLAttribute = uRLAttribute;
	}
	
}
