import java.io.File;
import java.util.List;
import java.util.Map;

import ccasola.man2oh.xml.ManParser;


/**
 * The main class for this help system
 * @author Chris Casola
 *
 */
public class Man2Oh {

	public static void main(String[] args) {
		
		File manFile = new File("./man.xml");
		ManParser manParser = new ManParser(manFile);
		List<String> entryTitles = manParser.getEntryTitles();
		
		for (String entry : entryTitles) {
			System.out.println(entry);
		}
		System.out.flush();
		
		Map<String, String> manEntries = manParser.getEntryHeaders();
		for (String title : manEntries.keySet()) {
			System.out.println("Title: " + title + "\nSummary: " + manEntries.get(title));
		}
		System.out.flush();
		
		System.out.println(manParser.getManEntry("Title 2"));
	}

}
