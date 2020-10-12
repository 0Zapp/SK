package gui;

import javax.swing.JToolBar;

public class MyToolbar extends JToolBar {

	public MyToolbar() {

		add(MainFrame.getInstance().getActionManager().getNewEntityAction());
		addSeparator();
		add(MainFrame.getInstance().getActionManager().getAddSubEntityAction());
		addSeparator();
		add(MainFrame.getInstance().getActionManager().getSearchAction());
		addSeparator();
		add(MainFrame.getInstance().getActionManager().getDeleteSingleAction());
		addSeparator();
		add(MainFrame.getInstance().getActionManager().getDeleteMultipleAction());
		addSeparator();
		add(MainFrame.getInstance().getActionManager().getConfigurationAction());

	}
}
