package fr.jamailun.ooapi.odt.text;

import fr.jamailun.ooapi.odt.MonoNode;
import fr.jamailun.ooapi.odt.TextContainer;
import fr.jamailun.ooapi.xml.XmlNode;

/**
 * For [text:tab] nodes.
 */
public class TabNode extends MonoNode implements TextContainer {
	
	public static final String XML_NAME = "text:tab";
	
	public TabNode(XmlNode node) {
		super(node);
	}
	
	@Override
	public String getText() {
		return "\t";
	}
	@Override
	public void setText(String s) {}
	
	@Override
	public void replace(String toFind, String replaceWith) {}
	
	@Override
	public final String getNodeName() {
		return XML_NAME;
	}
	
}
