package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import gui.StartFrame;

public class StartBrowseAction implements ActionListener {
	StartFrame startFrame;

	public StartBrowseAction(StartFrame startFrame) {
		this.startFrame = startFrame;
	}

	public void actionPerformed(ActionEvent arg0) {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Select folder:");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		//
		// disable the "All files" option.
		//
		chooser.setAcceptAllFileFilterUsed(false);
		//
		if (chooser.showOpenDialog(startFrame) == JFileChooser.APPROVE_OPTION) {
			// System.out.println("getCurrentDirectory(): " +
			// chooser.getCurrentDirectory());
			// System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
			startFrame.setSelectedFile(chooser.getSelectedFile().toString());
		} else {
			// System.out.println("No Selection ");
		}
	}

}
