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

	public void actionPerformed(ActionEvent arg0) {
		String path = startFrame.getSelectedFile();
		MainFrame mf = MainFrame.getInstance();
		startFrame.dispose();
		mf.setVisible(true);
	}

}
