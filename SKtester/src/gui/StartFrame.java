package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import actions.ActionManager;

public class StartFrame extends JFrame {
	private JTextField textField;
	private ActionManager manager;
	JButton launchBtn;
	JButton cancelButton;
	JButton browseButton;
	JButton newButton;
	JLabel label;
	JPanel pan1;
	JPanel pan2;

	public StartFrame() {

		manager = new ActionManager(this);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		setSize((screenWidth / 10) * 3, (screenHeight / 10));
		setLocationRelativeTo(null);

		setTitle("Select:");
		setResizable(false);

		launchBtn = new JButton("Launch");
		launchBtn.addActionListener(manager.getStartLaunchAction());
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(manager.getStartCancelAction());
		browseButton = new JButton("Browse");
		browseButton.addActionListener(manager.getStartBrowseAction());
		newButton = new JButton("New");
		newButton.addActionListener(manager.getStartNewAction());
		label = new JLabel("Selected:");
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(300, 20));

		pan1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pan1.add(label);
		pan1.add(textField);
		pan1.add(browseButton);
		pan1.add(newButton);

		pan2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pan2.add(launchBtn);
		pan2.add(cancelButton);

		add(pan1, BorderLayout.CENTER);
		add(pan2, BorderLayout.SOUTH);
	}

	public void setSelectedFile(String selectedFile) {
		textField.setText(selectedFile);

	}

}
