package fr.jamailun.ooapi.odt.text;

import fr.jamailun.ooapi.odt.LibrairyODT;
import fr.jamailun.ooapi.odt.ODIterableNode;
import fr.jamailun.ooapi.odt.ODNode;
import fr.jamailun.ooapi.odt.TextContainer;
import fr.jamailun.ooapi.xml.XmlNode;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * For [text:p] nodes.
 */
public class ParagraphNode extends ODIterableNode<ODNode> implements TextContainer {
	
	public static final String XML_NAME = "text:p";
	
	public ParagraphNode(XmlNode node) {
		super(node);
		fillFromXmlNode(LibrairyODT::containsNodeName, LibrairyODT::create);
	}
	
	public <NT extends ODNode & TextContainer> List<NT> getTextualElements() {
		return children.stream()
				.filter(c -> c instanceof TextContainer)
				.map(c -> (NT) c) // tqt frr
				.toList();
	}
	
	public Stream<ODNode> streamFilter(Predicate<ODNode> predicate) {
		return children.stream()
				.filter(predicate);
	}
	
	@Override
	public String getText() {
		return "";
		//StringBuilder sb = new StringBuilder();
		//getTextualElements().forEach(s -> sb.append(s.getText()));
		//return sb.toString();
	}
	
	@Override
	public void setText(String value) {
		//TODO settext of paragraph
	}
	
	@Override
	public final String getNodeName() {
		return XML_NAME;
	}
	
}
