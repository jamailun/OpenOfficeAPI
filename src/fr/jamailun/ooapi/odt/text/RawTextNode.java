package fr.jamailun.ooapi.odt.text;

import fr.jamailun.ooapi.odt.ODNode;
import fr.jamailun.ooapi.odt.TextContainer;
import fr.jamailun.ooapi.utils.Indent;
import fr.jamailun.ooapi.xml.XmlNode;

/**
 * For raw text value in the xml. OpenOffice loves to do that.
 */
public class RawTextNode extends ODNode implements TextContainer {
	
	public static final String XML_NAME = "";
	private String text;
	
	public RawTextNode(XmlNode node) {
		super(node);
		text = node.getTextContent();
	}
	
	@Override
	public String getText() {
		return text;
	}
	
	@Override
	public void setText(String text) {
		this.text = text == null ? "" : text;
	}
	
	@Override
	public final String getNodeName() {
		return XML_NAME;
	}
	
	@Override
	public String toXml(Indent indent, String endl) {
		return indent + getText() + endl;
	}
}
