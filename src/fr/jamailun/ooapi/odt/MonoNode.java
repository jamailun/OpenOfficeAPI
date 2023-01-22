package fr.jamailun.ooapi.odt;

import fr.jamailun.ooapi.utils.Indent;
import fr.jamailun.ooapi.xml.XmlNode;

import java.util.ArrayList;
import java.util.List;

public abstract class MonoNode extends ODNode {
	
	private final List<XmlNode> otherXml = new ArrayList<>();
	private final ODProperties otherProperties = new ODProperties();
	
	public MonoNode(XmlNode node) {
		super(node);
		for(XmlNode child : node) {
			otherXml.add(child);
			otherProperties.from(child);
		}
	}
	
	@Override
	public String getPropertie(String name) {
		if(properties.has(name))
			return properties.get(name);
		return otherProperties.get(name);
	}
	
	@Override
	public String toXml(Indent indent, String endl) {
		String prefix = indent + toXmlPrefix();
		if(otherXml.isEmpty())
			return prefix + "/>" + endl;
		return prefix + ">" + endl
				+ indent.add() + XmlNode.toStringCollection(otherXml, endl.equals("\n"))
				+ indent.remove() + "<" + getNodeName() + "/>";
	}
}
