package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import gui.DeleteMultipleDialog;
import gui.DeleteSingleDialog;
import gui.MainFrame;

public class DeleteMultipleAction  extends AbstractAction {

	public DeleteMultipleAction() {
		putValue(NAME, "Delete Multiple");
		// putValue(SMALL_ICON, new ImageIcon("images/new-entity.png"));
		//putValue(SHORT_DESCRIPTION, "Delete Multiple");

	}

	public void actionPerformed(ActionEvent arg0) {
		DeleteMultipleDialog dialog = new DeleteMultipleDialog(MainFrame.getInstance(), "New Entity", true);
		dialog.setVisible(true);
	}
}