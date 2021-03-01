package htmlelement;

public class HTMLText extends ContentSpan{

	private String text;
	
	public HTMLText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "TEXT: " + text;
	}

}
