package fr.jamailun.ooapi.xml;

import fr.jamailun.ooapi.utils.Indent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XmlNode implements Iterable<XmlNode> {

	private String name;
	private XmlAttributesMap attributes;
	private final List<XmlNode> children = new ArrayList<>();
	
	public XmlNode(String name, XmlAttributesMap attributes) {
		this.name = name;
		this.attributes = attributes;
	}
	
	public void forceRawWriting(String xml) {
		XmlDocument doc = XmlParser.parse(xml);
		name = doc.getRoot().getName();
		attributes = doc.getRoot().getAttributes();
		children.clear();
		children.addAll(doc.getRoot().getChildren());
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
		StringBuilder sb = new StringBuilder();
		children.forEach(c -> sb.append(c.getTextContent()));
		String s = sb.toString();
		return s.isEmpty() ? null : s;
	}
	
	public XmlAttributesMap getAttributes() {
		return attributes;
	}
	
	public String niceString(boolean n) {
		return niceString(new Indent(n ? "\t" : ""), n ? "\n" : "");
	}
	
	public String niceString(Indent indent, String endl) {
		StringBuilder sb = new StringBuilder(indent.toString());
		sb.append("<").append(name);
		if(attributes.size() > 0)
			sb.append(" ").append(attributes);
		if(!children.isEmpty()) {
			sb.append(">").append(endl);
		} else {
			sb.append("/>");
			return sb.toString();
		}
		
		indent.add();
		for(XmlNode child : children) {
			sb.append(child.niceString(indent, endl)).append(endl);
		}
		indent.remove();
		return sb.append(indent).append("</").append(name).append(">").toString();
	}
	
	@Override
	public String toString() {
		return niceString(true);
	}
	
	@Override
	public Iterator<XmlNode> iterator() {
		return children.listIterator();
	}
}
