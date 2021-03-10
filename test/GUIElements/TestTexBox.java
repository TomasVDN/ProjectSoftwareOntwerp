package GUIElements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.event.KeyEvent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestTexBox {

	private TextBox textBox;
	
	@BeforeEach
	public void setup() {
		textBox = new TextBox(0, 0, 100, 50);
	}
	
	@Test
	public void addCharacter() {
		textBox.handleClick();
		
		textBox.handleKeyEvent(65, 'a', 0);
		
		assertEquals(textBox.getText(), "a");
	}
	
	@Test
	public void addMultipleCharacter() {
		textBox.handleClick();
		
		textBox.handleKeyEvent(65, 'a', 0);
		textBox.handleKeyEvent(66, 'b', 0);
		textBox.handleKeyEvent(65, 'a', 0);
		
		assertEquals(textBox.getText(), "aba");
	}
	
	@Test
	public void backspace() {
		textBox.handleClick();
		
		textBox.setLeftText("aaa");
		textBox.handleKeyEvent(8, '\b', 0);
		textBox.handleKeyEvent(8, '\b', 0);
		
		assertEquals(textBox.getText(), "a");
		
		textBox.handleKeyEvent(8, '\b', 0);
		assertEquals(textBox.getText(), "");
		
		textBox.handleKeyEvent(8, '\b', 0);
		assertEquals(textBox.getText(), "");
	}
	
	@Test
	public void left() {
		textBox.handleClick();
		
		textBox.setLeftText("a");
		textBox.setRigthText("b");
		textBox.handleKeyEvent(37, '\0', 0);
		
		assertEquals(textBox.getText(), "ab");
		assertEquals(textBox.getLeftText(), "");
		assertEquals(textBox.getRightText(), "ab");
	}
	
	@Test
	public void leftAndBackSpace() {
		textBox.handleClick();
		
		textBox.setLeftText("abc");
		textBox.handleKeyEvent(37, '\0', 0);
		textBox.handleKeyEvent(8, '\b', 0);
		
		assertEquals(textBox.getText(), "ac");
		assertEquals(textBox.getLeftText(), "a");
		assertEquals(textBox.getRightText(), "c");
	}
	
	@Test
	public void right() {
		textBox.handleClick();
		
		textBox.setLeftText("a");
		textBox.setRigthText("b");
		textBox.handleKeyEvent(39, '\0', 0);
		
		assertEquals(textBox.getText(), "ab");
		assertEquals(textBox.getLeftText(), "ab");
		assertEquals(textBox.getRightText(), "");
	}
	
	@Test
	public void rightAndBackSpace() {
		textBox.handleClick();
		
		textBox.setRigthText("abc");
		textBox.handleKeyEvent(39, '\0', 0);
		textBox.handleKeyEvent(8, '\b', 0);
		
		assertEquals(textBox.getText(), "bc");
		assertEquals(textBox.getLeftText(), "");
		assertEquals(textBox.getRightText(), "bc");
	}
	
	@Test
	public void home() {
		textBox.handleClick();
		
		textBox.setLeftText("a");
		textBox.setRigthText("b");
		textBox.handleKeyEvent(36, '\0', 0);
		
		assertEquals(textBox.getText(), "ab");
		assertEquals(textBox.getLeftText(), "");
		assertEquals(textBox.getRightText(), "ab");
	}
	
	@Test
	public void end() {
		textBox.handleClick();
		
		textBox.setLeftText("a");
		textBox.setRigthText("b");
		textBox.handleKeyEvent(35, '\0', 0);
		
		assertEquals(textBox.getText(), "ab");
		assertEquals(textBox.getLeftText(), "ab");
		assertEquals(textBox.getRightText(), "");
	}

	@Test
	public void escape() {
		textBox.setLeftText("a");
		textBox.setRigthText("b");
		
		textBox.handleClick();
		textBox.handleKeyEvent(27, '\0', 0);
		
		assertEquals(textBox.getLeftText(), "ab");
	}
}

//java.awt.event.KeyEvent[KEY_RELEASED,keyCode=8,keyText=Backspace,keyChar=Backspace,keyLocation=KEY_LOCATION_STANDARD,rawCode=22,primaryLevelUnicode=8,scancode=0,extendedKeyCode=0x8] on canvaswindow.CanvasWindow$Panel[,0,0,600x600,layout=java.awt.FlowLayout,alignmentX=0.0,alignmentY=0.0,border=,flags=9,maximumSize=,minimumSize=,preferredSize=java.awt.Dimension[width=600,height=600]]
//java.awt.event.KeyEvent[KEY_PRESSED,keyCode=36,keyText=Home,keyChar=Undefined keyChar,keyLocation=KEY_LOCATION_STANDARD,rawCode=110,primaryLevelUnicode=0,scancode=0,extendedKeyCode=0x24] on canvaswindow.CanvasWindow$Panel[,0,0,600x600,layout=java.awt.FlowLayout,alignmentX=0.0,alignmentY=0.0,border=,flags=9,maximumSize=,minimumSize=,preferredSize=java.awt.Dimension[width=600,height=600]]
//java.awt.event.KeyEvent[KEY_PRESSED,keyCode=35,keyText=End,keyChar=Undefined keyChar,keyLocation=KEY_LOCATION_STANDARD,rawCode=115,primaryLevelUnicode=0,scancode=0,extendedKeyCode=0x23] on canvaswindow.CanvasWindow$Panel[,0,0,600x600,layout=java.awt.FlowLayout,alignmentX=0.0,alignmentY=0.0,border=,flags=9,maximumSize=,minimumSize=,preferredSize=java.awt.Dimension[width=600,height=600]]
//java.awt.event.KeyEvent[KEY_PRESSED,keyCode=37,keyText=Left,keyChar=Undefined keyChar,keyLocation=KEY_LOCATION_STANDARD,rawCode=113,primaryLevelUnicode=0,scancode=0,extendedKeyCode=0x25] on canvaswindow.CanvasWindow$Panel[,0,0,600x600,layout=java.awt.FlowLayout,alignmentX=0.0,alignmentY=0.0,border=,flags=9,maximumSize=,minimumSize=,preferredSize=java.awt.Dimension[width=600,height=600]]
//java.awt.event.KeyEvent[KEY_PRESSED,keyCode=39,keyText=Right,keyChar=Undefined keyChar,keyLocation=KEY_LOCATION_STANDARD,rawCode=114,primaryLevelUnicode=0,scancode=0,extendedKeyCode=0x27] on canvaswindow.CanvasWindow$Panel[,0,0,600x600,layout=java.awt.FlowLayout,alignmentX=0.0,alignmentY=0.0,border=,flags=9,maximumSize=,minimumSize=,preferredSize=java.awt.Dimension[width=600,height=600]]
//java.awt.event.KeyEvent[KEY_PRESSED,keyCode=27,keyText=Escape,keyChar=Escape,keyLocation=KEY_LOCATION_STANDARD,rawCode=9,primaryLevelUnicode=27,scancode=0,extendedKeyCode=0x1b] on canvaswindow.CanvasWindow$Panel[,0,0,600x600,layout=java.awt.FlowLayout,alignmentX=0.0,alignmentY=0.0,border=,flags=9,maximumSize=,minimumSize=,preferredSize=java.awt.Dimension[width=600,height=600]]
