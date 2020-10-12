package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.StartFrame;

public class StartCancelAction implements ActionListener {

	StartFrame startFrame;

	public StartCancelAction(StartFrame startFrame) {
		this.startFrame = startFrame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		startFrame.dispose();
	}

}
