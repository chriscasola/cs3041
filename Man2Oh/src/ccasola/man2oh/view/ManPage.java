package ccasola.man2oh.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ccasola.man2oh.model.ManParser;
import ccasola.man2oh.model.ManParser.HELP_LEVEL;
import ccasola.man2oh.model.UnknownEntryException;

@SuppressWarnings("serial")
public class ManPage extends JPanel implements ActionListener {

	String entryTitle = "";
	
	final JTextField txtLookup;
	
	final JButton btnFind;
	
	final ManFrame view;
	
	final HELP_LEVEL helpLevel;
	
	String helpText = "";
	
	public ManPage(ManFrame mainPanel) {
		this.view = mainPanel;
		this.setLayout(new FlowLayout());
		this.txtLookup = new JTextField(20);
		this.add(new JLabel("Enter item name: "));
		this.add(txtLookup);
		this.btnFind = new JButton("Find");
		btnFind.addActionListener(this);
		this.add(btnFind);
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
				helpText = ManParser.getInstance().getEntry(txtLookup.getText(), helpLevel);
			}
			catch (UnknownEntryException e) {
				JOptionPane.showMessageDialog(this, "The item you entered was not found. Please try again.");
				txtLookup.setText("");
				txtLookup.requestFocus();
				return;
			} 
			this.removeAll();
			this.add(new JLabel(helpText));
			this.validate();
			this.repaint();
		}
	}
}
