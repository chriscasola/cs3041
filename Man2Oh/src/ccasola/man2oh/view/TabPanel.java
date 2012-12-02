package ccasola.man2oh.view;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class TabPanel extends JTabbedPane {

	public TabPanel() {
		super();
		this.setTabPlacement(TOP);
		this.insertTab("atoi()", null, new JPanel(), null, 0);
		this.insertTab("fork()", null, new JPanel(), null, 1);
	}
	
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
