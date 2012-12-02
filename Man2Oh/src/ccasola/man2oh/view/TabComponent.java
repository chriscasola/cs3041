package ccasola.man2oh.view;

import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * 
 * References:
 * 	- http://www.trevorsimonton.com/blog/jbutton-spacing-remove-extra-padding-margin-border
 *  - http://stackoverflow.com/questions/10059020/transparent-jpanel
 *  - http://docs.oracle.com/javase/tutorial/uiswing/components/tabbedpane.html
 */
@SuppressWarnings("serial")
public class TabComponent extends JPanel {
	
	final JTabbedPane parent;
	
	final String title;
	
	final JButton closeButton;

	public TabComponent(String tabTitle, JTabbedPane parentTabPanel) {
		super(new FlowLayout(FlowLayout.LEFT, 0, 0));
		this.parent = parentTabPanel;
		this.title = tabTitle;
		
		// Label the tab
		JLabel label = new JLabel(title);
		label.setFont(label.getFont().deriveFont(14f));
		label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		this.add(label);
		
		// Add the close button
		closeButton = new JButton("\u2716");
		closeButton.setMargin(new Insets(0, 0, 0, 0));
		closeButton.setBorder(BorderFactory.createEmptyBorder(3, 6, 3, 6));
		closeButton.setFont(closeButton.getFont().deriveFont(10f));
		
		// Create the action listener for the close button so tabs are closed
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = parent.indexOfTab(title);
				parent.removeTabAt(index);
			}
		});
		this.add(closeButton);
		
		// Make the tab component transparent
		this.setOpaque(false);
	}
}
