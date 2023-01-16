import fr.jamailun.jamlogger.JamLogger;
import fr.jamailun.ooapi.odt.OpenDocument;
import fr.jamailun.ooapi.odt.OpenDocumentParser;
import fr.jamailun.ooapi.odt.draw.ImageNode;

import java.io.IOException;
import java.util.Collection;
import java.util.StringJoiner;
import java.util.zip.ZipFile;

public class Test {
	public static void main(String[] args) throws IOException {
		OpenDocument doc = OpenDocumentParser.parse(new ZipFile("F:/tests/test2.odt"));
		System.out.println(doc.getContentRoot().toXml(true));
		
		JamLogger.info("Images ? " + coll(doc.getAllOfType(ImageNode.class)));
		JamLogger.info("Images ? " + coll(doc.getAllOfType(ImageNode.XML_NAME)));
		
		ImageNode image = doc.getAllOfType(ImageNode.class).get(0);
		image.exportImage(doc, "F:/tests/xxxx.jpeg");
	}
	private static String coll(Collection<?> collection) {
		StringJoiner sj = new StringJoiner(", ");
		collection.forEach(o -> sj.add(o.toString()));
		return "[" + sj + "]";
	}
}
