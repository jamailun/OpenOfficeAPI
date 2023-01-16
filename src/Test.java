import fr.jamailun.ooapi.odt.OpenDocument;
import fr.jamailun.ooapi.odt.OpenDocumentParser;

import java.io.IOException;
import java.util.zip.ZipFile;

public class Test {
	public static void main(String[] args) throws IOException {
		OpenDocument doc = OpenDocumentParser.parse(new ZipFile("F:/tests/test2.odt"));
		System.out.println(doc.getContentRoot().toXml(true));
	}
}
