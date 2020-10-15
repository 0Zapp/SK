package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import gui.MainFrame;
import gui.NewEntityDialog;

public class NewEntityAction extends AbstractAction {

	public NewEntityAction() {
		putValue(NAME, "New Entity");
		// putValue(SMALL_ICON, new ImageIcon("images/new-entity.png"));
		// putValue(SHORT_DESCRIPTION, "New Entity");

	}

	public void actionPerformed(ActionEvent arg0) {
		NewEntityDialog dialog = new NewEntityDialog(MainFrame.getInstance(), "New Entity", true);
		dialog.setVisible(true);
	}
}