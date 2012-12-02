package ccasola.man2oh;

import java.io.File;
import java.util.List;

import ccasola.man2oh.xml.ManParser;
import ccasola.man2oh.xml.ManParser.HELP_LEVEL;
import ccasola.man2oh.xml.UnknownEntryException;


/**
 * The main class for this help system
 * @author Chris Casola
 *
 */
public class Man2Oh {

	public static void main(String[] args) {
		
		File manFile = new File("./man2.xml");
		ManParser manParser = new ManParser(manFile);
		List<String> entryTitles = manParser.getEntryTitles();
		
		System.out.println("Table of Contents:");
		for (String entry : entryTitles) {
			System.out.println(entry);
		}
		System.out.println("--------------------------------------------------------\n\n");
		
		System.out.println("Fork summary help:");
		try {
			System.out.println(manParser.getManEntry("fork", HELP_LEVEL.SUMMARY));
		} catch (UnknownEntryException e) {
			System.out.println("Couldn't find man entry");
		}
		System.out.println("--------------------------------------------------------\n\n");
	}

}
