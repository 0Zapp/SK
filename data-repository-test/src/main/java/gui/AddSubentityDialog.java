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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AddSubentityDialog extends JDialog implements ActionListener {

	public AddSubentityDialog(Frame parent, String title, boolean modal) {

		super(parent, title, modal);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		setSize((screenWidth / 15) * 2, (screenHeight / 15) * 2);
		setLocationRelativeTo(null);

		try {

			JLabel parentLbl = new JLabel("Select Parent", SwingConstants.CENTER);
			JComboBox<String> parentCombo = new JComboBox<String>();

			JLabel KeyLbl = new JLabel("Select Key", SwingConstants.CENTER);
			JComboBox<String> KeyCombo = new JComboBox<String>();

			JLabel childLbl = new JLabel("Select Child", SwingConstants.CENTER);
			JComboBox<String> childCombo = new JComboBox<String>();

			JButton ConfirmButton = new JButton("Confirm");
			JButton CancelButton = new JButton("Cancel");

			setLayout(new GridLayout(4, 2));
			add(parentLbl);
			add(parentCombo);

			add(KeyLbl);
			add(KeyCombo);

			add(childLbl);
			add(childCombo);

			add(ConfirmButton);
			add(CancelButton);

			ConfirmButton.addActionListener(this);
			CancelButton.addActionListener(this);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Cancel")) {
			this.dispose();
		} else {
			this.dispose();
		}

	}

}
