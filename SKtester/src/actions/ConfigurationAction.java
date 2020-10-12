package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import gui.ConfigurationDialog;
import gui.DeleteMultipleDialog;
import gui.MainFrame;

public class ConfigurationAction extends AbstractAction {

	public ConfigurationAction() {
		putValue(NAME, "Configuration");
		// putValue(SMALL_ICON, new ImageIcon("images/new-entity.png"));
		// putValue(SHORT_DESCRIPTION, "Configuration");

	}

	public void actionPerformed(ActionEvent arg0) {
		ConfigurationDialog dialog = new ConfigurationDialog(MainFrame.getInstance(), "New Entity", true);
		dialog.setVisible(true);
	}
}