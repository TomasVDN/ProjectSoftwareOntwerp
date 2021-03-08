package events;

import facades.Browsr;

public class RunUrlEvent implements Event {

	private String url;
	
	public RunUrlEvent(String url) {
		this.setUrl(url);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public void execute(Browsr browsr) {
		browsr.runUrl(this.getUrl());
	}
}
