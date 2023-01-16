package fr.jamailun.ooapi.odt;

import fr.jamailun.jamlogger.JamLogger;
import fr.jamailun.ooapi.utils.StringUtils;
import fr.jamailun.ooapi.xml.XmlDocument;
import fr.jamailun.ooapi.xml.XmlParser;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class OpenDocumentParser {
	private OpenDocumentParser() {}
	
	private static final String MIMETYPE = "mimetype";
	private static final List<String> LOOKED_FILES = List.of("content.xml", "styles.xml", "meta.xml");
	
	public static OpenDocument parse(ZipFile zipFile) throws IOException {
		assert zipFile != null : "ZipFile cannot be null.";
		Enumeration<? extends ZipEntry> entries = zipFile.entries();
		
		XmlDocument content = null, styles = null, meta = null;
		boolean typeChecked = false;
		
		while(entries.hasMoreElements()){
			ZipEntry entry = entries.nextElement();
			String name = entry.getName();
			
			if(name.equals(MIMETYPE)) {
				String type = new String(zipFile.getInputStream(entry).readAllBytes());
				if(type.equals(OpenDocument.MIMETYPE)) {
					typeChecked = true;
				} else {
					throw new IllegalArgumentException("The mimetype is not correct for file " + zipFile.getName()+". Expected " + OpenDocument.MIMETYPE + ". Got '"+type+"'.");
				}
			}
			else if(LOOKED_FILES.contains(entry.getName())) {
				JamLogger.log("Parsing entry '" + entry.getName()+"'.");
				XmlDocument document = XmlParser.parse(zipFile.getInputStream(entry));
				switch (entry.getName()) {
					case "content.xml" -> content = document;
					case "styles.xml" -> styles = document;
					case "meta.xml" -> meta = document;
				}
			}
		}
		
		if(content == null || styles == null || meta == null) {
			throw new IllegalArgumentException("Incorrect open document file. Some inner-files are missing: "
					+ StringUtils.toStringWhenNull(content, "content", styles, "styles", meta, "meta"));
		}
		if(!typeChecked)
			throw new IllegalArgumentException("Incorrect open document file. Mimetype is missing.");
		
		return new OpenDocument(zipFile, content, styles, meta);
	}
	
}
