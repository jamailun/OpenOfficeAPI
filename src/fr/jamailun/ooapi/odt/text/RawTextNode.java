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
	
	public RawTextNode(XmlNode node) {
		super(node);
	}
	
	@Override
	public String getText() {
		return getNodeReference().getTextContent();
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
