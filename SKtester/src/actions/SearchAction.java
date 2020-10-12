package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import gui.AddSubentityDialog;
import gui.MainFrame;
import gui.SearchDialog;

public class SearchAction  extends AbstractAction {

	public SearchAction() {
		putValue(NAME, "Search");
		// putValue(SMALL_ICON, new ImageIcon("images/new-entity.png"));
		//putValue(SHORT_DESCRIPTION, "New");

	}

	public void actionPerformed(ActionEvent arg0) {
		SearchDialog dialog = new SearchDialog(MainFrame.getInstance(), "New Entity", true);
		dialog.setVisible(true);
	}
}