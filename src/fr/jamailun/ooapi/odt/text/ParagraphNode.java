package fr.jamailun.ooapi.odt.text;

import fr.jamailun.ooapi.odt.LibrairyODT;
import fr.jamailun.ooapi.odt.ODIterableNode;
import fr.jamailun.ooapi.odt.ODNode;
import fr.jamailun.ooapi.xml.XmlNode;

/**
 * For [text:p] nodes.
 */
public class ParagraphNode extends ODIterableNode<ODNode> {
	
	public static final String XML_NAME = "text:p";
	
	public ParagraphNode(XmlNode node) {
		super(node);
		fillFromXmlNode(LibrairyODT::containsNodeName, LibrairyODT::create);
	}
	
	@Override
	public final String getNodeName() {
		return XML_NAME;
	}
	
}
