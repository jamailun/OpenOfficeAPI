package fr.jamailun.ooapi.odt;

import fr.jamailun.jamlogger.JamLogger;
import fr.jamailun.ooapi.common.TextConstants;
import fr.jamailun.ooapi.common.XLinkConstants;
import fr.jamailun.ooapi.utils.Indent;
import fr.jamailun.ooapi.utils.XmlHelper;
import fr.jamailun.ooapi.xml.MalformedXmlException;
import fr.jamailun.ooapi.xml.XmlNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * The base class of all XML nodes.
 */
public abstract class ODNode {
	
	// Reference to the XML equivalent. Used for XML rebuild.
	private final XmlNode nodeReference;
	
	protected final ODProperties properties = new ODProperties();
	
	/**
	 * Create the node instance. Will get the ID and style name attributes.
	 * @param node the XML node. Cannot be null.
	 */
	public ODNode(XmlNode node) {
		assert node != null : "Cannot have a null node";
		this.nodeReference = node;
		if(getNodeName() != null && !node.getName().equals(getNodeName())) {
			String error = "ERROR incorrect node name. Expected <"+getNodeName()+">, got <"+node.getName()+">.";
			JamLogger.error(error);
			throw new MalformedXmlException(error);
		}
		properties.from(node);
	}
	
	/**
	 * Change the style name of the node.
	 * @param styleName the new style name.
	 */
	public void setStyleName(String styleName) {
		properties.set(TextConstants.STYLE, styleName);
	}
	
	/**
	 * Get the current style name of the node.
	 * @return null if no style name is provided in the XML.
	 */
	public String getStyleName() {
		return properties.get(TextConstants.STYLE);
	}
	
	public boolean hasStyleName() {
		return getStyleName() != null;
	}
	
	/**
	 * Get the XML source instance.
	 * @return the instance of the XML node. Cannot be null.
	 */
	public XmlNode getNodeReference() {
		return nodeReference;
	}
	
	/**
	 * Get the node name.
	 * @return the XML_NAME value of the node.
	 */
	public abstract String getNodeName();
	
	/**
	 * Create the XML equivalent of this node.
	 * @param nicePrint if true, nice and clear XML. If not, one-line code.
	 * @return the valid XML string for this node.
	 */
	public String toXml(boolean nicePrint) {
		return nicePrint ? toXml(new Indent("\t"), "\n") : toXml(new Indent(), "");
	}
	
	public abstract String toXml(Indent indent, String endl);
	
	protected String toXmlPrefix() {
		return "<" + getNodeName() + properties.toXml();
	}
	
	/**
	 * Get the current XML id.
	 * @return null if no id exists.
	 */
	public String getId() {
		return properties.get(XLinkConstants.XML_ID);
	}
	
	/**
	 * Test if this element as a specified XML id.
	 * @return true if the id exists.
	 */
	public boolean hasId() {
		return getId() != null;
	}
	
	/**
	 * Change the xml ID of the current element.
	 * @param newId the new id. Put a null value to remove the id.
	 * @throws IllegalArgumentException if the argument is not xml-compatible
	 */
	public void setId(String newId) {
		if(newId == null) {
			properties.set(XLinkConstants.XML_ID, null);
			return;
		}
		if(! XmlHelper.isXmlCompatible(newId))
			throw new IllegalArgumentException("Cannot accept string '"+newId+"' as xml ID for " + this + ".");
		//TODO faire de la validation sur le document ? check si un autre elem n'a pas déjà cet id.
		properties.set(XLinkConstants.XML_ID, newId);
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "{id="+getId()+"}";
	}
	
	public List<? extends ODNode> getChildren() {
		return Collections.emptyList();
	}
	
	public <T> List<T> collectAllInterfaces(Class<T> clazz) {
		return collectAllChildren(c -> clazz.isAssignableFrom(c.getClass())).stream().map(n -> clazz.cast(n)).toList();
	}
	
	public <T extends ODNode & TextContainer> List<T> collectAllTextContainers() {
		return collectAllChildren(c -> TextContainer.class.isAssignableFrom(c.getClass()));
	}
	
	public <T extends ODNode> List<T> collectAllChildren() {
		List<T> all = new ArrayList<>();
		all.add((T) this);
		for(ODNode node : getChildren()) {
			all.addAll(node.collectAllChildren());
		}
		return all;
	}
	
	public <T extends ODNode> List<T> collectAllChildren(Predicate<T> pred) {
		List<T> all = new ArrayList<>();
		if(pred.test((T) this))
			all.add((T) this);
		for(ODNode node : getChildren()) {
			all.addAll(node.collectAllChildren(pred));
		}
		return all;
	}
	
	public int getChildrenCount() {
		return getChildren().size();
	}
	
	public boolean hasChildren() {
		return ! getChildren().isEmpty();
	}
	
}
