package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import gui.MainFrame;
import gui.UpdateDialog;

public class UpdateAction extends AbstractAction {

	public UpdateAction() {
		putValue(NAME, "Update Entity");
		// putValue(SMALL_ICON, new ImageIcon("images/new-entity.png"));
		// putValue(SHORT_DESCRIPTION, "New Entity");

	}

	public void actionPerformed(ActionEvent arg0) {
		UpdateDialog dialog = new UpdateDialog(MainFrame.getInstance(), "UpdateEntuty", true);
		dialog.setVisible(true);
	}
}