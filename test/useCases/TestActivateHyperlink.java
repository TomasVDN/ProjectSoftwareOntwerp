package useCases;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import canvaswindow.MyCanvasWindow;
import events.EventReader;
import events.FileOpenEvent;

class TestActivateHyperlink {

	@BeforeEach
	void setUp() throws InvocationTargetException, InterruptedException {
		java.awt.EventQueue.invokeAndWait(() -> {
			MyCanvasWindow window = new MyCanvasWindow("Browsr");
		});
	}
	
	@Test
	void test() {
		FileOpenEvent event = new FileOpenEvent(new File("src/hyperlinktest.html"));
		EventReader e = EventReader.getInstance();
		e.readEvent(event);
	}

}
