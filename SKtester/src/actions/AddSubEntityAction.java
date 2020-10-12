package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import gui.AddSubentityDialog;
import gui.MainFrame;
import gui.NewEntityDialog;

public class AddSubEntityAction extends AbstractAction {

	public AddSubEntityAction() {
		putValue(NAME, "Add SubEntity");
		// putValue(SMALL_ICON, new ImageIcon("images/new-entity.png"));
		// putValue(SHORT_DESCRIPTION, "Add SubEntity");

	}

	public void actionPerformed(ActionEvent arg0) {
		AddSubentityDialog dialog = new AddSubentityDialog(MainFrame.getInstance(), "New Entity", true);
		dialog.setVisible(true);
	}
}