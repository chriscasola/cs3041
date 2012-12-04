package ccasola.man2oh.view;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JTabbedPane;

/**
 * The tab pane that displays multiple {@link ManPage} panels.
 */
@SuppressWarnings("serial")
public class TabPane extends JTabbedPane {

	/**
	 * Constructs a new TabPane
	 */
	public TabPane() {
		super();
		this.setTabPlacement(TOP);
	}
	
	@Override
	public void addTab(String title, Component component) {
		insertTab(title, null, component, null, this.getTabCount());
		this.setSelectedIndex(this.getTabCount() - 1);
	}
	
	@Override
	public void insertTab(String title, Icon icon, Component component, String tip, int index) {
		super.insertTab(title, icon, component, tip, index);
		this.setTabComponentAt(index, new TabComponent(this.getTitleAt(index), this));
	}
}
