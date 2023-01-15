package fr.jamailun.ooapi.xml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XmlNode implements Iterable<XmlNode> {

	private final String name;
	private final XmlAttributesMap attributes;
	private String textContent;
	
	private final List<XmlNode> children = new ArrayList<>();
	
	public XmlNode(String name, XmlAttributesMap attributes) {
		this.name = name;
		this.attributes = attributes;
	}
	
	void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	
	void addChild(XmlNode node) {
		children.add(node);
	}
	
	public List<XmlNode> getChildren() {
		return children;
	}
	
	public int getChildrenCount() {
		return children.size();
	}
	
	public XmlNode getChild(int index) {
		return children.get(index);
	}
	
	public XmlNode getFirstChild(String name) {
		for(XmlNode child : children)
			if(child.getName().equals(name))
				return child;
		return null;
	}
	
	public List<XmlNode> getChildren(String name) {
		return children.stream()
				.filter(n -> n.getName().equals(name))
				.toList();
	}
	
	public String getName() {
		return name;
	}
	
	public String getTextContent() {
		return textContent;
	}
	
	public XmlAttributesMap getAttributes() {
		return attributes;
	}
	
	public String niceString(String indent, String endl) {
		StringBuilder sb = new StringBuilder(indent);
		sb.append("<").append(name);
		if(attributes.size() > 0)
			sb.append(" ").append(attributes);
		if(textContent != null || !children.isEmpty()) {
			sb.append(">").append(endl);
		} else {
			sb.append("/>");
			return sb.toString();
		}
		
		String indentChildren = indent + "\t";
		if(textContent != null)
			sb.append(indentChildren).append(textContent).append(endl);
		for(XmlNode child : children) {
			sb.append(child.niceString(indentChildren, endl)).append(endl);
		}
		return sb.append(indent).append("</").append(name).append(">").toString();
	}
	
	@Override
	public String toString() {
		return niceString("", "\n");
	}
	
	@Override
	public Iterator<XmlNode> iterator() {
		return children.listIterator();
	}
}
