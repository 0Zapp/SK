package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.MainFrame;
import gui.StartFrame;

public class StartLaunchAction implements ActionListener {

	StartFrame startFrame;

	public StartLaunchAction(StartFrame startFrame) {
		this.startFrame = startFrame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		MainFrame mf = MainFrame.getInstance();
		startFrame.dispose();
		mf.setVisible(true);
	}

}
