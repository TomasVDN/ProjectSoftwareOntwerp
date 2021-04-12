package events;

import facades.Browsr;

public class SavePageEvent implements Event{

	private String filename;
	public SavePageEvent(String filename) {
		this.filename = filename;
	}

	@Override
	public void execute(Browsr browsr) {
		browsr.savePage(filename);
	}

}
