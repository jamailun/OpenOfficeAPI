package fr.jamailun.ooapi.odt.text;

import fr.jamailun.ooapi.odt.ODNode;
import fr.jamailun.ooapi.odt.TextContainer;
import fr.jamailun.ooapi.utils.Indent;
import fr.jamailun.ooapi.xml.XmlNode;

/**
 * For [text:sequence] nodes inside a text:p.
 *
 *  <p>
 *      http://docs.oasis-open.org/office/v1.2/os/OpenDocument-v1.2-os-part1.html#element-text_sequence-decls
 *  </p>
 */
public class SequenceNode extends ODNode implements TextContainer {
	
	public static final String XML_NAME = "text:sequence";
	
	public static final String XML_ATTR_NAME = "text:name";
	public static final String XML_ATTR_FORMULA = "text:formula";
	public static final String XML_ATTR_NUMFORMAT = "style:num-format";
	public static final String XML_ATTR_REFNAME = "text:ref-name";
	
	private String text;
	
	public SequenceNode(XmlNode node) {
		super(node);
		text = node.getTextContent();
		properties.from(XML_ATTR_NAME, node);
		properties.from(XML_ATTR_FORMULA, node);
		properties.from(XML_ATTR_NUMFORMAT, node);
		properties.from(XML_ATTR_REFNAME, node);
	}
	
	public String getSequenceName() {
		return properties.get(XML_ATTR_NAME);
	}
	
	public String getFormula() {
		return properties.get(XML_ATTR_FORMULA);
	}
	
	public String getNumFormat() {
		return properties.get(XML_ATTR_NUMFORMAT);
	}
	
	public String getRefName() {
		return properties.get(XML_ATTR_REFNAME);
	}
	
	public void setSequenceName(String sequenceName) {
		properties.set(XML_ATTR_NAME, sequenceName);
	}
	
	public void setFormula(String formula) {
		properties.set(XML_ATTR_FORMULA, formula);
	}
	
	public void setNumFormat(String numFormat) {
		properties.set(XML_ATTR_NUMFORMAT, numFormat);
	}
	
	public void setRefName(String refName) {
		properties.set(XML_ATTR_REFNAME, refName);
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public String getText() {
		return text;
	}
	
	@Override
	public final String getNodeName() {
		return XML_NAME;
	}
	
	@Override
	public String toXml(Indent indent, String endl) {
		return indent + toXmlPrefix() + ">" + endl
				+ indent.add() + text + endl
				+ indent.remove() + "</"+getNodeName()+">" + endl;
	}
}
