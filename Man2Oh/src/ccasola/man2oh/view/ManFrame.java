package ccasola.man2oh.view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import ccasola.man2oh.model.ManParser;

@SuppressWarnings("serial")
public class ManFrame extends JFrame {
	
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
		layout.putConstraint(SpringLayout.NORTH, tabPanel, 0, SpringLayout.SOUTH, toolbarPanel);
		layout.putConstraint(SpringLayout.WEST, tabPanel, 0, SpringLayout.WEST, mainPanel);
		layout.putConstraint(SpringLayout.EAST, tabPanel, 0, SpringLayout.EAST, mainPanel);
		layout.putConstraint(SpringLayout.SOUTH, tabPanel, 0, SpringLayout.SOUTH, mainPanel);
		mainPanel.add(tabPanel);
		
		// Make the window visible
		mainPanel.validate();
		this.add(mainPanel);
		this.pack();
		this.setVisible(true);
	}
	
	public TabPanel getTabPanel() {
		return tabPanel;
	}
}
