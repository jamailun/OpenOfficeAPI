import fr.jamailun.jamlogger.JamLogger;
import fr.jamailun.ooapi.odt.OpenDocument;
import fr.jamailun.ooapi.odt.OpenDocumentParser;
import fr.jamailun.ooapi.odt.draw.ImageNode;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.StringJoiner;
import java.util.zip.ZipFile;

public class Test {
	public static void main(String[] args) throws IOException {
		OpenDocument doc = OpenDocumentParser.parse(new ZipFile("F:/tests/non.odt"));
		///System.out.println(doc.getContentRoot().toXml(true));
		//System.out.println(doc.getMeta().toXml(true));
		
		//System.out.println(doc.getMeta().getNodeReference().niceString(true));
		
		JamLogger.log(doc.getMeta().getDocumentStatistics());
		JamLogger.info("paragraphs ? " + doc.getMeta().getDocumentStatistics().getParagraphsCount());
		JamLogger.info("Tables ? " + doc.getMeta().getDocumentStatistics().getTablesCount());
		JamLogger.info("generator ? " + doc.getMeta().getGenerator());
		JamLogger.info("last edit ? " + doc.getMeta().getLastEdtitingDate());
		
		doc.getMeta().setEditingCycles(1);
		doc.getMeta().setEditingDuration(Duration.ofMinutes(10));
		doc.getMeta().applyToRealXml();
		
		//System.out.println(doc.getMeta().getNodeReference().niceString(true));
		
		JamLogger.info(doc.getMeta());
	}
	
	private static void replaceText(OpenDocument doc) {
		Map<String, String> replacements = Map.of(
				"{recipient}", "Duponltdh",
				"{state}", "fou, voir \"crazy\" comme disent les djeuns",
				"{figure}", "PTITE IMAGE"
		);
		
		JamLogger.log(doc.getContentRoot().getRawText());
		doc.getContentRoot().replaceAll(replacements);
		doc.getContentRoot().replaceAll("other_string", "replacment");
		JamLogger.log(doc.getContentRoot().getRawText());
		
		doc.getContentRoot().applyToRealXml();
		
		//doc.recreateFile("F:/tests/non.odt");
	}
	
	private static void exportImages(OpenDocument doc) throws IOException {
		JamLogger.info("Images ? " + coll(doc.getAllOfType(ImageNode.class))); //also works with ImageNode.XML_NAME
		
		ImageNode image = doc.getAllOfType(ImageNode.class).get(0);
		image.exportImage(doc, "F:/tests/xxxx.jpeg");
	}
	
	private static String coll(Iterable<?> collection) {
		StringJoiner sj = new StringJoiner(", ");
		collection.forEach(o -> sj.add(o.toString()));
		return "[" + sj + "]";
	}
}
