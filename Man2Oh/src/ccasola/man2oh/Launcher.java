package ccasola.man2oh;

import java.io.File;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import ccasola.man2oh.model.ManParser;
import ccasola.man2oh.model.UnknownEntryException;
import ccasola.man2oh.model.ManParser.HELP_LEVEL;
import ccasola.man2oh.view.ManFrame;


/**
 * The main class for this help system
 * @author Chris Casola
 *
 */
public class Launcher {

	public static void main(String[] args) {
		
		final File manFile = new File("./man2.xml");
		ManParser.getInstance(manFile);
		
		// Set the look and feel
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, use the default look and feel
		}
		
		// Start the GUI
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ManFrame manFrame = new ManFrame();				
			}
		});
		
		/*
		List<String> entryTitles = manParser.getEntryTitles();
		
		System.out.println("Table of Contents:");
		for (String entry : entryTitles) {
			System.out.println(entry);
		}
		System.out.println("--------------------------------------------------------\n\n");
		
		System.out.println("Fork summary help:");
		try {
			System.out.println(manParser.getEntry("fork", HELP_LEVEL.SUMMARY));
		} catch (UnknownEntryException e) {
			System.out.println("Couldn't find man entry");
		}
		System.out.println("--------------------------------------------------------\n\n");
		*/
	}

}
