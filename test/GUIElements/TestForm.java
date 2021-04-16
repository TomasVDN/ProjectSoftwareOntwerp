/**
 * 
 */
package GUIElements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author kobe
 *
 */
class TestForm {
	Text hyperText = new Text(0,0,"This is a hyperlink");
	Hyperlink hyper = new Hyperlink(0, 0, hyperText, "https://blabla.com", null);

	@Test
	void test() {
		
	}
	
	@Test
	void testNoAction() {
		Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
			Form guiCell = new Form(hyper, 0, 0, null,null);
		});
		assertTrue(exception2.getMessage().contains("not a valid action"));
	}
	

}
