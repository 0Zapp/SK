package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class NewEntityDialog extends JDialog implements ActionListener {

	public NewEntityDialog(Frame parent, String title, boolean modal) {

		super(parent, title, modal);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		setSize((screenWidth / 15) * 2, (screenHeight / 15) * 2);
		setLocationRelativeTo(null);

		try {

			JLabel lbl1 = new JLabel("Number of values", SwingConstants.CENTER);
			JTextField txtField = new JTextField("1", SwingConstants.CENTER);

			JCheckBox check = new JCheckBox("Custom ID");
			JLabel customID = new JLabel("", SwingConstants.CENTER);

			JButton ConfirmButton = new JButton("Confirm");
			JButton CancelButton = new JButton("Cancel");

			setLayout(new GridLayout(3, 2));
			add(lbl1);
			add(txtField);
			add(check);
			add(customID);
			add(ConfirmButton);
			add(CancelButton);

			ConfirmButton.addActionListener(this);
			CancelButton.addActionListener(this);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Cancel")) {
			this.dispose();
		} else {
			NewEntityCreatorDialog dialog = new NewEntityCreatorDialog(MainFrame.getInstance(), "New Entity Creator", true);
			dialog.setVisible(true);
			this.dispose();
		}

	}

}
