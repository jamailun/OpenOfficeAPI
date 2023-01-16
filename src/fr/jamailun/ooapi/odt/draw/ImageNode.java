package fr.jamailun.ooapi.odt.draw;

import fr.jamailun.ooapi.odt.ODNode;
import fr.jamailun.ooapi.utils.Indent;
import fr.jamailun.ooapi.xml.XmlNode;

public class ImageNode extends ODNode {
	
	public final static String XML_NAME = "draw:image";
	
	public final static String IMAGE_TYPE = "xlink:type";
	public final static String MIME_TYPE = "draw:mime-type";
	public final static String SHOW_MODE = "xlink:show";
	public final static String ACTIVATE = "xlink:actuate";
	public final static String HREF = "xlink:href";
	
	public ImageNode(XmlNode node) {
		super(node);
		properties.from(IMAGE_TYPE, node.getAttributes());
		properties.from(MIME_TYPE, node.getAttributes());
		properties.from(SHOW_MODE, node.getAttributes());
		properties.from(ACTIVATE, node.getAttributes());
		properties.from(HREF, node.getAttributes());
	}
	
	public String getImageType() {
		return properties.get(IMAGE_TYPE);
	}
	
	public void setImageType(String imageType) {
		properties.set(IMAGE_TYPE, imageType);
	}
	
	public String getMimetype() {
		return properties.get(MIME_TYPE);
	}
	
	public void setMimetype(String mimetype) {
		properties.set(MIME_TYPE, mimetype);
	}
	
	public String getShowMode() {
		return properties.get(SHOW_MODE);
	}
	
	public void setShowMode(String showMode) {
		properties.set(SHOW_MODE, showMode);
	}
	
	public String getActivate() {
		return properties.get(ACTIVATE);
	}
	
	public void setActivate(String activate) {
		properties.set(ACTIVATE, activate);
	}
	
	public String getHref() {
		return properties.get(HREF);
	}
	
	public void setHref(String href) {
		properties.set(HREF, href);
	}
	
	@Override
	public String getNodeName() {
		return XML_NAME;
	}
	
	@Override
	public String toXml(Indent indent, String endl) {
		return indent + "<" + XML_NAME + properties.toXml() + "/>" + endl;
	}
}
