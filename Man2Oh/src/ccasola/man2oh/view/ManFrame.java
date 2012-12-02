package ccasola.man2oh.view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ccasola.man2oh.model.ManParser;

/**
 * 
 * References:
 * 	- http://docs.oracle.com/javase/tutorial/uiswing/events/changelistener.html
 */
@SuppressWarnings("serial")
public class ManFrame extends JFrame implements ChangeListener {
	
	ManParser model;
	
	JPanel mainPanel;
	
	ToolbarPanel toolbarPanel;
	
	TabPanel tabPanel;

	public ManFrame() {
		
		// Configure the window
		this.setBounds(150, 150, 800, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(800, 600));
		this.setTitle("Man2Oh Help Viewer");
		
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
		this.tabPanel = new TabPanel();
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
	
	public TabPanel getTabPanel() {
		return tabPanel;
	}

	@Override
	public void stateChanged(ChangeEvent ce) {
		if (ce.getSource() instanceof JSlider) {
			JSlider slider = (JSlider)ce.getSource();
			if (!slider.getValueIsAdjusting()) {
				ISliderUpdated currentManPage = (ISliderUpdated)tabPanel.getSelectedComponent();
				currentManPage.sliderChanged(slider);
			}
		}
		else if (ce.getSource() instanceof TabPanel) {
			TabPanel tabPanel = (TabPanel)ce.getSource();
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
