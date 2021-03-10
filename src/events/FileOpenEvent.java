package events;

import java.io.File;

import facades.Browsr;

public class FileOpenEvent implements Event {

	/**
	 * The file linked to this RunUrlEvent.
	 */
	private File file;
	
	/**
	 * Contstructor of FileOpenEvent class. Sets this.file to given file.
	 * @param url - value of this.url
	 */
	public FileOpenEvent(File file) {
		this.setFile(file);
	}

	/**
	 * @return this.file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * Sets this.file to the given file.
	 * @param file - new value of this.file
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * Calls the runFile method from the given browsr. Gives as argument this.file.
	 * @param browsr - browsr instance in which to execute runFile(this.file)
	 */
	@Override
	public void execute(Browsr browsr) {
		browsr.openFile(this.getFile());
	}
}
