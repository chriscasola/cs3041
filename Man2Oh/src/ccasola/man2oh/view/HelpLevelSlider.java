package ccasola.man2oh.view;

import java.awt.Dimension;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JSlider;

/**
 * 
 * Reference: http://docs.oracle.com/javase/tutorial/uiswing/components/slider.html
 */
@SuppressWarnings("serial")
public class HelpLevelSlider extends JSlider {

	public HelpLevelSlider() {
		super(JSlider.HORIZONTAL, 1, 3, 1);
		
		// Set up the tick marks and their labels
		this.setMajorTickSpacing(1);
		this.setPaintTicks(true);
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(new Integer(1), new JLabel("Topic"));
		labelTable.put(new Integer(2), new JLabel("Summary"));
		labelTable.put(new Integer(3), new JLabel("Detail"));
		this.setLabelTable(labelTable);
		this.setPaintLabels(true);
		this.setSnapToTicks(true);
		
		// Set the maximum width of the slider
		this.setMaximumSize(new Dimension(250, this.getPreferredSize().height));
		
	}
}
