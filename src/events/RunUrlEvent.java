package events;

public class RunUrlEvent extends Event {

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
}
