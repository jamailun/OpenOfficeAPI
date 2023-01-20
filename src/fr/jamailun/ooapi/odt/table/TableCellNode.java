package fr.jamailun.ooapi.odt.table;

import fr.jamailun.ooapi.common.TableConstants;
import fr.jamailun.ooapi.odt.LibrairyODT;
import fr.jamailun.ooapi.odt.ODIterableNode;
import fr.jamailun.ooapi.odt.ODNode;
import fr.jamailun.ooapi.utils.StringUtils;
import fr.jamailun.ooapi.xml.XmlNode;

public class TableCellNode extends ODIterableNode<ODNode> {
	
	public static final String XML_NAME = "table:table-cell";
	
	public TableCellNode(XmlNode node) {
		super(node);
		fillFromXmlNode(LibrairyODT::containsNodeName, LibrairyODT::create);
	}
	
	public int getColumnSpan() {
		return StringUtils.toIntOrZero(properties.get(TableConstants.COLUMN_SPAN));
	}
	
	public void setColumnSpan(int count) {
		properties.set(TableConstants.COLUMN_SPAN, count + "");
	}
	
	@Override
	public String getNodeName() {
		return XML_NAME;
	}
	
	public static class CoveredCellNode extends TableCellNode {
		public static final String XML_NAME = "table:covered-table-cell";
		public CoveredCellNode(XmlNode node) {
			super(node);
		}
		@Override
		public String getNodeName() {
			return XML_NAME;
		}
	}
	
}
