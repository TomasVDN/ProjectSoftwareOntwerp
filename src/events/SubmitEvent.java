package events;

import GUIElements.Form;
import facades.Browsr;

public class SubmitEvent implements Event,FormEvent {

	@Override
	public void execute(Browsr browsr) {
		
	}

	@Override
	public void execute(Form form) {
		form.submit();
	}
}
