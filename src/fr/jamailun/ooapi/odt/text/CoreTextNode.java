package fr.jamailun.ooapi.odt.text;

import fr.jamailun.jamlogger.JamLogger;
import fr.jamailun.ooapi.odt.ODIterableNode;
import fr.jamailun.ooapi.odt.ODNode;
import fr.jamailun.ooapi.xml.XmlNode;

import java.util.ArrayList;
import java.util.List;

/**
 * The only child of the [office:body] node.
 */
public class CoreTextNode extends ODIterableNode<ODNode> {
	
	public final static String XML_NAME = "office:text";
	
	private final List<XmlNode> otherXml = new ArrayList<>();
	
	public CoreTextNode(XmlNode node) {
		super(node);
		for(int i = 0; i < node.getChildrenCount(); i++) {
			XmlNode child = node.getChild(i);
			if(child.getName().equals(ParagraphNode.XML_NAME)) {
				children.add(new ParagraphNode(child));
			} else if(child.getName().equals("table:table")) {
				JamLogger.warning("TODO : tables.");
			} else {
				otherXml.add(node);
			}
		}
	}
	
	public List<XmlNode> getOtherXml() {
		return otherXml;
	}
	
	@Override
	public final String getNodeName() {
		return XML_NAME;
	}
	
}
