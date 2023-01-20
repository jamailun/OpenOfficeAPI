package fr.jamailun.ooapi.odt;

import fr.jamailun.jamlogger.JamLogger;
import fr.jamailun.ooapi.utils.Indent;
import fr.jamailun.ooapi.xml.XmlNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class ODIterableNode<T extends ODNode> extends ODNode implements Iterable<T> {
	
	protected final List<T> children;
	
	public ODIterableNode(XmlNode node) {
		super(node);
		children = new ArrayList<>();
	}
	
	protected void fillFromXmlNode(Predicate<XmlNode> p, Function<XmlNode, T> f) {
		for(XmlNode s : getNodeReference()) {
			if(p.test(s)) {
				children.add(f.apply(s));
			} else {
				JamLogger.error("Unknown child '"+s+"' in " + getNodeReference().getName()+".");
			}
		}
	}
	
	@Override
	public String toXml(Indent indent, String endl) {
		if(hasChildren()) {
			return indent + toXmlPrefix() + ">" + endl
					+ toXmlChildren(endl, indent)
					+ indent + "</"+getNodeName()+">" + endl;
		} else {
			return indent + toXmlPrefix() + "/>" + endl;
		}
	}
	
	@Override
	public Iterator<T> iterator() {
		return getChildren().listIterator();
	}
	
	@Override
	public List<T> getChildren() {
		return Collections.unmodifiableList(children);
	}
	
}
