package fr.jamailun.ooapi.xml;

public class XmlNodeRawText extends XmlNode {
	public XmlNodeRawText(String text) {
		super("", new XmlAttributesMap());
		setTextContent(text);
	}
	
	@Override
	public String niceString(String indent, String endl) {
		return indent + getTextContent() + endl;
	}
	
}
