/*
 * References:
 *	- http://www.trevorsimonton.com/blog/jbutton-spacing-remove-extra-padding-margin-border
 *  - http://stackoverflow.com/questions/10059020/transparent-jpanel
 *  - http://docs.oracle.com/javase/tutorial/uiswing/components/tabbedpane.html
 */

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
 * This class provides an alternate tab component for the
 * JTabbedPane. This tab component adds a close button to
 * each tab.
 */
@SuppressWarnings("serial")
public class TabComponent extends JPanel {
	
	/** The main JTabbedPane that this TabComponent is used in */
	final JTabbedPane parent;
	
	/** The close button for this tab */
	final JButton closeButton;
	
	/** The title of this tab */
	final JLabel title;

	/**
	 * Constructs the tab component
	 * @param tabTitle the title of the tab
	 * @param parentTabPane the tab pane to which this tab belongs
	 */
	public TabComponent(String tabTitle, JTabbedPane parentTabPane) {
		super(new FlowLayout(FlowLayout.LEFT, 0, 0));
		this.parent = parentTabPane;
		this.title = new JLabel(tabTitle);
		
		// Label the tab
		title.setFont(title.getFont().deriveFont(14f));
		title.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		this.add(title);
		
		// Construct and configure the close button
		closeButton = new JButton("\u2716");
		closeButton.setMargin(new Insets(0, 0, 0, 0));
		closeButton.setBorder(BorderFactory.createEmptyBorder(3, 6, 3, 6));
		closeButton.setFont(closeButton.getFont().deriveFont(10f));
		
		// Create the action listener for the close button so tabs are closed
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = parent.indexOfTab(title.getText()); // Get the index of this tab
				index = (index > 0) ? index : 0; // make sure the index is not less than 0
				parent.removeTabAt(index); // remove this tab from the tab pane
			}
		});
		
		// Add the close button to the tab
		this.add(closeButton);
		
		// Make the tab component transparent
		this.setOpaque(false);
	}
	
	/**
	 * Sets the title of the tab to the given title
	 * @param newTitle the new title of the tab
	 */
	public void setTitle(String newTitle) {
		this.title.setText(newTitle);
		this.validate();
		this.repaint();
	}
}
