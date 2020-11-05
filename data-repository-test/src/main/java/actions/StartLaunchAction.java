package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import DBHandler.DBHandler;
import customHandler.CustomHandler;
import gui.MainFrame;
import gui.StartFrame;
//import jsonHandler.JsonHandler;
//import yamlHandler.YamlHandler;

public class StartLaunchAction implements ActionListener {

	StartFrame startFrame;

	public StartLaunchAction(StartFrame startFrame) {
		this.startFrame = startFrame;
	}

	public void actionPerformed(ActionEvent arg0) {
		String path = startFrame.getSelectedFile();
		DBHandler db = null;
		if (!isValidPath(path)) {
			System.out.println("Please enter a valid path");
			return;
		}
		try {
			db = new CustomHandler(path);
		} catch (Exception e) {
			db = new CustomHandler(path, 100);
		}
		MainFrame mf = MainFrame.getInstance(db);
		startFrame.dispose();
		mf.setVisible(true);
	}

	public static boolean isValidPath(String path) {
		File file = new File(path);
		if (!file.isDirectory())
			return false;
		file.delete();
		return true;
	}

}
