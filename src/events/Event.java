package events;

import facades.Browsr;

public interface Event {

	/**
	 * Executes the corresponding handling.
	 * @param browsr - the browsr (controller) that controls the window.
	 */
	public void execute(Browsr browsr);
	
}
