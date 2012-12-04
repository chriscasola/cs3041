/*
 * References:
 *	- http://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
 * 	- http://stackoverflow.com/questions/307024/native-swing-menu-bar-support-for-macos-x-in-java
 */

package ccasola.man2oh;

import java.io.File;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import ccasola.man2oh.model.ManParser;
import ccasola.man2oh.view.ManFrame;


/**
 * The main class for this help system
 * 
 * CS 3041 - Project 4
 * 12/04/2012
 * 
 * @author Chris Casola
 */
public class Launcher {

	/**
	 * The main method that opens the XML file and launches the GUI
	 * @param args command line arguments (none are used)
	 */
	public static void main(String[] args) {
		
		// Set application name (for OS X only)
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Man2Oh");
		
		// Load the XML file containing help data
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
			
		} catch (Exception e1) {
			// could not use the Nimbus look and feel
		}
		
		// If Nimbus is not used, try to use the cross platform look and feel
		if (UIManager.getLookAndFeel().getName() != "Nimbus") {
			try { // try to use the cross platform look and feel
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			}
			catch (Exception e) {
				// could not use cross platform look and feel so use the system one, but warn the user
				System.out.println("Error: Falling back to the system default look and feel. The GUI may not display as intended!");
			}
		}

		// Start the GUI
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ManFrame();				
			}
		});
	}

}
