package fr.jamailun.ooapi.odt.table;

import fr.jamailun.jamlogger.JamLogger;
import fr.jamailun.ooapi.odt.ODNode;
import fr.jamailun.ooapi.utils.Indent;
import fr.jamailun.ooapi.xml.XmlNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class TableNode extends ODNode {
	
	public static final String XML_NAME = "table:table";
	
	private final List<TableColumnNode> columns = new ArrayList<>();
	private final List<TableRowNode> rows = new ArrayList<>();
	
	public TableNode(XmlNode node) {
		super(node);
		for(XmlNode child : node.getChildren()) {
			if(child.getName().equals(TableColumnNode.XML_NAME)) {
				columns.add(new TableColumnNode(child));
			} else if(child.getName().equals(TableRowNode.XML_NAME)) {
				rows.add(new TableRowNode(child));
			} else {
				JamLogger.error("Unknown child '"+child+"' in TABLE node " + getNodeReference().getName()+".");
			}
		}
	}
	
	@Override
	public String getNodeName() {
		return XML_NAME;
	}
	
	@Override
	public List<? extends ODNode> getChildren() {
		return Stream.concat(columns.stream(), rows.stream()).toList();
	}
	
	@Override
	public String toXml(Indent indent, String endl) {
		if(columns.isEmpty() && rows.isEmpty())
			return indent + toXmlPrefix() + "/>" + endl;
		
		StringBuilder children = new StringBuilder();
		indent.add();
		columns.forEach(child -> children.append(child.toXml(indent, endl)));
		rows.forEach(child -> children.append(child.toXml(indent, endl)));
		indent.remove();
		
		return indent + toXmlPrefix() + ">" + endl
				+ children
				+ indent + "</"+getNodeName()+">" + endl;
	}
}
