package events;

import facades.Browsr;

public class SavePageEvent implements Event{

	public SavePageEvent() {
	}

	@Override
	public void execute(Browsr browsr) {
		browsr.savePage();
	}

}
