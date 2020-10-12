package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import gui.DeleteSingleDialog;
import gui.MainFrame;
import gui.SearchDialog;

public class DeleteSingleAction  extends AbstractAction {

	public DeleteSingleAction() {
		putValue(NAME, "Delete Single");
		// putValue(SMALL_ICON, new ImageIcon("images/new-entity.png"));
		//putValue(SHORT_DESCRIPTION, "Delete Single");

	}

	public void actionPerformed(ActionEvent arg0) {
		DeleteSingleDialog dialog = new DeleteSingleDialog(MainFrame.getInstance(), "New Entity", true);
		dialog.setVisible(true);
	}
}