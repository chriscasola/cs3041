package ccasola.man2oh.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ccasola.man2oh.model.ManParser;
import ccasola.man2oh.model.ManParser.HELP_LEVEL;
import ccasola.man2oh.model.UnknownEntryException;

@SuppressWarnings("serial")
public class ManPage extends JPanel implements ActionListener, ISliderUpdated {

	String entryTitle = "";
	
	final JTextField txtLookup;
	
	final JButton btnFind;
	
	final ManFrame view;
	
	final JTextArea helpContents;
	final JScrollPane helpContentsScrollPane;
	
	HELP_LEVEL helpLevel;
	
	String helpText = "";
	
	public ManPage(ManFrame mainPanel) {
		this.view = mainPanel;
		
		// Construct and configure the help content text area
		this.helpContents = new JTextArea();
		this.helpContents.setWrapStyleWord(true);
		this.helpContents.setEditable(false);
		this.helpContentsScrollPane = new JScrollPane(helpContents);
		
		// Construct the lookup text field
		this.txtLookup = new JTextField(20);
		
		// Construct the find button
		this.btnFind = new JButton("Find");
		btnFind.addActionListener(this);
		
		// Set the layout manager of the panel
		this.setLayout(new BorderLayout());
		
		// Add lookup controls to the panel
		JPanel lookupPanel = new JPanel();
		lookupPanel.setLayout(new FlowLayout());
		lookupPanel.add(new JLabel("Enter item name: "));
		lookupPanel.add(txtLookup);
		lookupPanel.add(btnFind);
		this.add(lookupPanel, BorderLayout.PAGE_START);
		
		// The default help level is Topic
		this.helpLevel = HELP_LEVEL.TOPIC;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (txtLookup.getText().length() < 1) {
			JOptionPane.showMessageDialog(this, "You must enter the name of an item to lookup", "Invalid Item Name", JOptionPane.WARNING_MESSAGE);
			txtLookup.requestFocus();
		}
		else {
			try {
				// Get the new help text
				helpText = ManParser.getInstance().getEntry(txtLookup.getText(), HELP_LEVEL.TOPIC);
				
				// Update the man page viewer
				this.entryTitle = txtLookup.getText();
				this.helpContents.setText(helpText);
				this.removeAll();
				this.add(helpContentsScrollPane, BorderLayout.CENTER);
				this.validate();
				this.repaint();
				
				// Update the name of the tab
				((TabComponent)view.getTabPanel().getTabComponentAt(view.getTabPanel().getSelectedIndex())).setTitle(entryTitle);
			}
			catch (UnknownEntryException e) {
				JOptionPane.showMessageDialog(this, "The item you entered was not found. Please try again.", "Item Not Found", JOptionPane.INFORMATION_MESSAGE);
				txtLookup.setText("");
				txtLookup.requestFocus();
			}
		}
	}

	@Override
	public void sliderChanged(JSlider slider) {
		switch(slider.getValue()) {
		case 1:
			helpLevel = HELP_LEVEL.TOPIC;
			break;
		case 2:
			helpLevel = HELP_LEVEL.SUMMARY;
			break;
		case 3:
			helpLevel = HELP_LEVEL.DETAIL;
			break;
		}
		
		try {
			helpText = ManParser.getInstance().getEntry(entryTitle, helpLevel);
			helpContents.setText(helpText);
			this.validate();
			this.repaint();
		} catch (UnknownEntryException e) {
			// do nothing
		}
	}
	
	public JTextField getLookupField() {
		return txtLookup;
	}
	
	public HELP_LEVEL getHelpLevel() {
		return helpLevel;
	}
}
