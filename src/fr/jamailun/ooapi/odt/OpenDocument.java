package fr.jamailun.ooapi.odt;

import fr.jamailun.ooapi.common.Hrefable;
import fr.jamailun.ooapi.odt.text.CoreTextNode;
import fr.jamailun.ooapi.xml.MalformedXmlException;
import fr.jamailun.ooapi.xml.XmlDocument;
import fr.jamailun.ooapi.xml.XmlNode;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class OpenDocument {
	
	public static final String MIMETYPE = "application/vnd.oasis.opendocument.text";
	
	private final ZipFile zipFile;
	
	private final CoreTextNode contentNode;
	
	private final XmlDocument styles;
	private final XmlDocument content;
	private final XmlDocument meta;
	
	public OpenDocument(ZipFile zipFile, XmlDocument content, XmlDocument styles, XmlDocument meta) {
		assert zipFile != null : "ZipFile cannot be null.";
		this.zipFile = zipFile;
		this.content = content;
		this.styles = styles;
		this.meta = meta;
		
		XmlNode contentBody = content.getRoot().getFirstChild("office:body");
		if(contentBody == null)
			throw new MalformedXmlException("Could not get <office:body> of content in root: " + content.getRoot());
		
		//JamLogger.log(contentBody);
		
		XmlNode text = contentBody.getFirstChild(CoreTextNode.XML_NAME);
		if(text == null)
			throw new MalformedXmlException("The BODY of the content doesn't contain any <"+CoreTextNode.XML_NAME+">.");
		contentNode = new CoreTextNode(text);
	}
	
	public <T extends ODNode> List<T> getAllOfType(Class<T> clazz) {
		return contentNode.collectAllChildren(n -> clazz.isAssignableFrom(n.getClass()));
	}
	public List<? extends ODNode> getAllOfType(String xmlType) {
		return contentNode.collectAllChildren(n -> n.getNodeName().equals(xmlType));
	}
	
	public ByteArrayInputStream getHref(Hrefable node) throws IOException {
		return getHref(node.getHref());
	}
	
	public ByteArrayInputStream getHref(String href) throws IOException {
		Enumeration<? extends ZipEntry> entries = zipFile.entries();
		while(entries.hasMoreElements()) {
			ZipEntry entry = entries.nextElement();
			String name = entry.getName();
			if(name.equals(href)) {
				return new ByteArrayInputStream(zipFile.getInputStream(entry).readAllBytes());
			}
		}
		throw new IllegalArgumentException("Could not find the href '"+href+"'. Is the archive valid ?");
	}
	
	public CoreTextNode getContentRoot() {
		return contentNode;
	}
	
	@Override
	public String toString() {
		return "OpenDocument{\""+zipFile.getName()+"\"}";
	}
}
