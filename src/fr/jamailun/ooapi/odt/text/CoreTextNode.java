package fr.jamailun.ooapi.odt.text;

import fr.jamailun.jamlogger.JamLogger;
import fr.jamailun.ooapi.odt.ODIterableNode;
import fr.jamailun.ooapi.odt.ODNode;
import fr.jamailun.ooapi.odt.TextContainer;
import fr.jamailun.ooapi.odt.table.TableNode;
import fr.jamailun.ooapi.utils.Indent;
import fr.jamailun.ooapi.xml.XmlNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The only child of the [office:body] node.
 */
public class CoreTextNode extends ODIterableNode<ODNode> {
	
	public final static String XML_NAME = "office:text";
	
	private final List<XmlNode> otherXml = new ArrayList<>();
	
	public CoreTextNode(XmlNode node) {
		super(node);
		for(XmlNode child : node.getChildren()) {
			if(child.getName().equals(ParagraphNode.XML_NAME)) {
				children.add(new ParagraphNode(child));
			} else if(child.getName().equals(TableNode.XML_NAME)) {
				children.add(new TableNode(child));
			} else {
				otherXml.add(child);
			}
		}
	}
	
	public String getRawText() {
		StringBuilder sb = new StringBuilder();
		for(TextContainer tc : collectAllTextContainers()) {
			sb.append(tc.getText());
		}
		return sb.toString();
	}
	
	public void replaceAll(Map<String, String> replacements) {
		for(String k : replacements.keySet()) {
			int count = 0;
			for(TextContainer tc : collectAllTextContainers()) {
				count += tc.countOccurences(k);
			}
			JamLogger.info("count of ("+k+") = " + count);
			replaceAll(k, replacements.get(k));
		}
	}
	
	public void replaceAll(String source, String target) {
		for(TextContainer tc : collectAllTextContainers()) {
			tc.replace(source, target);
		}
	}
	
	public List<XmlNode> getOtherXml() {
		return otherXml;
	}
	public String getOtherXmlToString(boolean nicePrint) {
		StringBuilder sb = new StringBuilder();
		for(XmlNode node : getOtherXml()) {
			sb.append(node.niceString(nicePrint));
		}
		return sb.toString();
	}
	
	public void applyToRealXml() {
		String xml = toXmlPrefix() + ">"
				+ getOtherXmlToString(false)
				+ toXmlChildren("", new Indent())
				+ "</"+getNodeName()+">";
		getNodeReference().forceRawWriting(xml);
	}
	
	@Override
	public final String getNodeName() {
		return XML_NAME;
	}
	
}
