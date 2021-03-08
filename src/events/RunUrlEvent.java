package events;

import facades.Browsr;

public class RunUrlEvent implements Event {

	/**
	 * The URL linked to this RunUrlEvent.
	 */
	private String url;
	
	/**
	 * Contstructor of RunUrlEvent class. Sets this.url to given url.
	 * @param url - value of this.url
	 */
	public RunUrlEvent(String url) {
		this.setUrl(url);
	}

	/**
	 * @return this.url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets this.url to the given url.
	 * @param url - new value of this.url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Calls the runUrl method from the given browsr. Gives as argument this.url.
	 * @param browsr - browsr instance in wich to execute runUrl(this.url)
	 */
	@Override
	public void execute(Browsr browsr) {
		browsr.runUrl(this.getUrl());
	}
}
