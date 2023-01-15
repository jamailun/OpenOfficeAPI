package fr.jamailun.ooapi.odt.draw;

import fr.jamailun.ooapi.odt.LibrairyODT;
import fr.jamailun.ooapi.odt.ODIterableNode;
import fr.jamailun.ooapi.odt.ODNode;
import fr.jamailun.ooapi.xml.XmlNode;

public class FrameNode extends ODIterableNode<ODNode> {
	
	public static final String XML_NAME = "draw:frame";
	
	public FrameNode(XmlNode node) {
		super(node);
		fillFromXmlNode(LibrairyODT::containsNodeName, LibrairyODT::create);
	}
	
	@Override
	public String getNodeName() {
		return XML_NAME;
	}
	
}