package domain;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import htmlElement.*;

class TestHTMLDecoder {

	@Test
	void testHTMLDecoderFormInForm() {
		HTMLDecoder htmlDecoder = new HTMLDecoder("""
				<form action="browsrformactiontest.php">
				<form action="browsrformactiontest.php">
				<table>
					<tr><td>List words from the Woordenlijst Nederlandse Taal
					<tr><td>
					<table>
					<tr>
					<td>Starts with:
					<td><input type="text" name="starts_with">
					<tr>
					<td>Max. results:
					<td><input type="text" name="max_nb_results">
					</table>
					<tr><td><input type="submit">
				</table>
			</form>
			</form>
				""");
		Exception exception = assertThrows(RuntimeException.class, () -> {
			ArrayList<ContentSpan> array = htmlDecoder.createElements();
		});
		assertEquals("A form cannot exist inside a form", exception.getMessage());
	}


	
	

	@Test
	void testWithString() {
		HTMLDecoder htmlDecoder = new HTMLDecoder("""
				<table>
				  <tr><td>HTML elements partially supported by Browsr:
				  <tr><td>
				    <table>
				      <tr><td><a href="a.html">a</a><td>Hyperlink anchors
				      <tr><td><a href="table.html">table</a><td>Tables
				      <tr><td><a href="tr.html">tr</a><td>Table rows
				      <tr><td><a href="td.html">td</a><td>Table cells containing table data
				    </table>
				</table>
				""");
		ArrayList<ContentSpan> array = htmlDecoder.createElements();
		assertEquals(1, array.size());
		assertTrue(array.get(0) instanceof HTMLTable);
		HTMLTable table = (HTMLTable) array.get(0);
		assertEquals(2,table.getRows().size());
		assertTrue(table.getRows().get(1).getcells().get(0).getContent() instanceof HTMLTable);
		HTMLTable table2 = (HTMLTable) table.getRows().get(1).getcells().get(0).getContent();
		assertEquals(4,table2.getRows().size());
		assertTrue(table2.getRows().get(0).getcells().get(0).getContent() instanceof HTMLHyperlink);
		assertTrue(table2.getRows().get(1).getcells().get(0).getContent() instanceof HTMLHyperlink);
		assertTrue(table2.getRows().get(2).getcells().get(0).getContent() instanceof HTMLHyperlink);
		assertTrue(table2.getRows().get(3).getcells().get(0).getContent() instanceof HTMLHyperlink);
		
	}
	
	
	@Test
	void testWithForms() {
		HTMLDecoder htmlDecoder = new HTMLDecoder("""
				<form action="browsrformactiontest.php">
					<table>
						<tr><td>List words from the Woordenlijst Nederlandse Taal
						<tr><td>
						<table>
						<tr>
						<td>Starts with:
						<td><input type="text" name="starts_with">
						<tr>
						<td>Max. results:
						<td><input type="text" name="max_nb_results">
						</table>
						<tr><td><input type="submit">
					</table>
				</form>
				""");
		ArrayList<ContentSpan> array = htmlDecoder.createElements();
		assertEquals(1, array.size());
		assertTrue(array.get(0) instanceof HTMLForm);
		HTMLForm form = (HTMLForm) array.get(0);
		assertTrue(form.getElement() instanceof HTMLTable);
		HTMLTable table = (HTMLTable) form.getElement();
		assertEquals(3, table.getRows().size());
		assertTrue(table.getRows().get(0).getcells().get(0).getContent() instanceof HTMLText );
		assertTrue(table.getRows().get(1).getcells().get(0).getContent() instanceof HTMLTable);
		assertTrue(table.getRows().get(2).getcells().get(0).getContent() instanceof HTMLButton);
		HTMLTable table2 = (HTMLTable) table.getRows().get(1).getcells().get(0).getContent();
		assertEquals(2, table2.getRows().size());
		assertEquals(2, table2.getRows().get(0).getcells().size());
		assertTrue(table2.getRows().get(0).getcells().get(1).getContent() instanceof HTMLTextBox);
		assertTrue(table2.getRows().get(1).getcells().get(1).getContent() instanceof HTMLTextBox);
	}
	

	
	
	
}


