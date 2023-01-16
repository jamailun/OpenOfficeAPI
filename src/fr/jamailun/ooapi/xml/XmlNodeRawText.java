package fr.jamailun.ooapi.xml;

public class XmlNodeRawText extends XmlNode {
	
	private final String textContent;
	
	public XmlNodeRawText(String text) {
		super("", new XmlAttributesMap());
		this.textContent = text;
	}
	
	@Override
	public String getTextContent() {
		return textContent;
	}
	
	@Override
	public String niceString(String indent, String endl) {
		return indent + getTextContent() + endl;
	}
	
}
