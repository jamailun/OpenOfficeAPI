package fr.jamailun.ooapi.odt.table;

import fr.jamailun.ooapi.odt.LibrairyODT;
import fr.jamailun.ooapi.odt.ODIterableNode;
import fr.jamailun.ooapi.odt.ODNode;
import fr.jamailun.ooapi.xml.XmlNode;

public class TableRowNode extends ODIterableNode<TableCellNode> {
	
	public static final String XML_NAME = "table:table-row";
	
	public TableRowNode(XmlNode node) {
		super(node);
		fillFromXmlNode(
				n -> n.getName().equals(TableCellNode.XML_NAME),
				TableCellNode::new
		);
	}
	
	@Override
	public String getNodeName() {
		return XML_NAME;
	}
	
}
