/*
 * References:
 * 	- http://docs.oracle.com/javase/tutorial/uiswing/events/changelistener.html
 */

package ccasola.man2oh.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ccasola.man2oh.model.ManParser;

/**
 * The main window for the application
 */
@SuppressWarnings("serial")
public class ManFrame extends JFrame implements ChangeListener {
	
	/** The model, contains the help data, parsed on demand from the XML file */
	ManParser model;
	
	/** The main panel containing all GUI elements */
	JPanel mainPanel;
	
	/** The toolbar panel containing the lookup button and help level slider */
	ToolbarPanel toolbarPanel;
	
	/** The panel containing the tab panel for viewing help documents */
	TabPane tabPanel;

	/**
	 * Constructs a new main window
	 */
	public ManFrame() {
		
		// Configure the window
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(800, 600));
		this.setTitle("Man2Oh Help Viewer");
		
		// Position the window in the center of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (dim.width - 800) / 2;
		int yPos = (int)((dim.height - 600) / 2 * .75);
		this.setBounds(xPos, yPos, 800, 600);
		
		// Construct the panel to be displayed in this frame
		mainPanel = new JPanel();
		SpringLayout layout = new SpringLayout();
		mainPanel.setLayout(layout);
		
		// Setup the ToolbarPanel
		this.toolbarPanel = new ToolbarPanel(this);
		layout.putConstraint(SpringLayout.NORTH, toolbarPanel, 0, SpringLayout.NORTH, mainPanel);
		layout.putConstraint(SpringLayout.SOUTH, toolbarPanel, 60, SpringLayout.NORTH, toolbarPanel);
		layout.putConstraint(SpringLayout.WEST, toolbarPanel, 0, SpringLayout.WEST, mainPanel);
		layout.putConstraint(SpringLayout.EAST, toolbarPanel, 0, SpringLayout.EAST, mainPanel);
		mainPanel.add(toolbarPanel);
		
		// Setup the TabPanel
		this.tabPanel = new TabPane();
		this.tabPanel.addChangeListener(this);
		layout.putConstraint(SpringLayout.NORTH, tabPanel, 0, SpringLayout.SOUTH, toolbarPanel);
		layout.putConstraint(SpringLayout.WEST, tabPanel, 5, SpringLayout.WEST, mainPanel);
		layout.putConstraint(SpringLayout.EAST, tabPanel, -5, SpringLayout.EAST, mainPanel);
		layout.putConstraint(SpringLayout.SOUTH, tabPanel, -5, SpringLayout.SOUTH, mainPanel);
		mainPanel.add(tabPanel);
		
		// Make the window visible
		mainPanel.validate();
		this.add(mainPanel);
		this.pack();
		this.toolbarPanel.getLookupButton().doClick();
		this.setVisible(true);
	}
	
	/**
	 * Returns the tab panel
	 * @return the tab panel
	 */
	public TabPane getTabPanel() {
		return tabPanel;
	}

	/**
	 * Method required by ChangeListener interface.
	 * This method is called when the tab panel or slider change
	 */
	@Override
	public void stateChanged(ChangeEvent ce) {
		if (ce.getSource() instanceof JSlider) { // the slider changed, so notify the currently displayed ManPage
			JSlider slider = (JSlider)ce.getSource();
			if (!slider.getValueIsAdjusting()) {
				ISliderUpdated currentManPage = (ISliderUpdated)tabPanel.getSelectedComponent();
				currentManPage.sliderChanged(slider);
			}
		}
		else if (ce.getSource() instanceof TabPane) { // the active tab changed, so update the state of the slider
			TabPane tabPanel = (TabPane)ce.getSource();
			if (tabPanel.getTabCount() > 0) {
				switch(((ManPage)tabPanel.getSelectedComponent()).getHelpLevel()) {
				case DETAIL:
					toolbarPanel.getHelpLevelSlider().setValue(3);
					break;
				case SUMMARY:
					toolbarPanel.getHelpLevelSlider().setValue(2);
					break;
				case TOPIC:
					toolbarPanel.getHelpLevelSlider().setValue(1);
					break;
				default:
					break;
				}
			}
		}
	}
}
