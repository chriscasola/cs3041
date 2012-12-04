/*
 * References:
 *	- http://stackoverflow.com/questions/2174319/is-it-possible-to-have-a-java-swing-border-only-on-the-top-side
 */

package ccasola.man2oh.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 * A panel that contains the main toolbar, including the help level slider and the lookup button
 */
@SuppressWarnings("serial")
public class ToolbarPanel extends JPanel {
	
	/** The lookup button */
	protected JButton btnLookup;
	
	/** The help level slider */
	protected HelpLevelSlider helpLevelSlider;
	
	/** The main application window */
	protected final ManFrame manFrame;

	/**
	 * Constructs a ToolbarPanel
	 * @param parent the parent application window
	 */
	public ToolbarPanel(ManFrame parent) {
		this.manFrame = parent;
		
		// Set the layout manager and border of this panel
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		
		// Construct and configure the lookup button
		btnLookup = new JButton("Lookup Item");
		btnLookup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				manFrame.getTabPanel().addTab("Lookup Item", new ManPage(manFrame));
				((ManPage)manFrame.getTabPanel().getSelectedComponent()).getLookupField().requestFocus();
			}
		});
		
		// Add the lookup button to this panel
		this.add(Box.createRigidArea(new Dimension(14, 0)));
		this.add(btnLookup);
		
		// Construct and configure the slider and add it to this panel
		helpLevelSlider = new HelpLevelSlider();
		helpLevelSlider.addChangeListener(manFrame);
		this.add(Box.createRigidArea(new Dimension(100, 0)));
		this.add(new JLabel("Help Level:"));
		this.add(Box.createRigidArea(new Dimension(15, 0)));
		this.add(helpLevelSlider);
		
		// Refresh the layout of the panel
		this.validate();
	}
	
	/**
	 * Returns the lookup button
	 * @return the lookup button
	 */
	public JButton getLookupButton() {
		return btnLookup;
	}
	
	/**
	 * Returns the help level slider
	 * @return the help level slider
	 */
	public JSlider getHelpLevelSlider() {
		return helpLevelSlider;
	}
}
