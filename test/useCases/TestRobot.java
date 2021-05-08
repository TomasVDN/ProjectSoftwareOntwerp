package useCases;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

/**
 * Java test robot
 * 
 * @author william
 *
 * */
public class TestRobot
{
  Robot robot = new Robot();

  public TestRobot() throws AWTException {
	  System.out.println("Robot is starting...");
  }
  
  public void leftClick() {
    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    robot.delay(200);
    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    robot.delay(200);
  }
  
  public void type(int i) {
    robot.delay(40);
    robot.keyPress(i);
    robot.keyRelease(i);
  }
  
  public void mouseMove(int x, int y) {
	 robot.mouseMove(x, y);
  }
  
  public void type(String s) {
    byte[] bytes = s.getBytes();
    for (byte b : bytes) {
      int code = b;
      // keycode only handles [A-Z] (which is ASCII decimal [65-90])
      if (code > 96 && code < 123) code = code - 32;
      robot.delay(40);
      robot.keyPress(code);
      robot.keyRelease(code);
    }
  }
}