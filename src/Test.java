import fr.jamailun.jamlogger.JamLogger;
import fr.jamailun.ooapi.odt.OpenDocument;
import fr.jamailun.ooapi.odt.OpenDocumentParser;

import java.io.IOException;
import java.util.Map;
import java.util.StringJoiner;
import java.util.zip.ZipFile;

public class Test {
	public static void main(String[] args) throws IOException {
		OpenDocument doc = OpenDocumentParser.parse(new ZipFile("F:/tests/test2.odt"));
		System.out.println(doc.getContentRoot().toXml(true));
		
		JamLogger.info(doc.getContentRoot());
		
		Map<String, String> replacements = Map.of(
				"{recipient}", "Duponltdh",
				"{state}", "fou, voir \"crazy\" comme disent les djeuns",
				"{figure}", "PTITE IMAGE"
		);
		
		JamLogger.log(doc.getContentRoot().getRawText());
		doc.getContentRoot().replaceAll(replacements);
		JamLogger.log(doc.getContentRoot().getRawText());
		
		doc.getContentRoot().applyToRealXml();
		doc.recreateFile("F:/tests/non.odt");
		
		/*JamLogger.info("Images ? " + coll(doc.getAllOfType(ImageNode.class)));
		JamLogger.info("Images ? " + coll(doc.getAllOfType(ImageNode.XML_NAME)));
		
		ImageNode image = doc.getAllOfType(ImageNode.class).get(0);
		image.exportImage(doc, "F:/tests/xxxx.jpeg");*/
	}
	
	private static String coll(Iterable<?> collection) {
		StringJoiner sj = new StringJoiner(", ");
		collection.forEach(o -> sj.add(o.toString()));
		return "[" + sj + "]";
	}
}
