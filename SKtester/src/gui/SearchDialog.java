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

public class SearchDialog extends JDialog implements ActionListener {

	public SearchDialog(Frame parent, String title, boolean modal) {

		super(parent, title, modal);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		setSize((screenWidth / 3), (screenHeight / 15) * 2);
		setLocationRelativeTo(null);

		try {

			JLabel lbl1 = new JLabel("Search:", SwingConstants.CENTER);
			JTextField txtField = new JTextField("", SwingConstants.CENTER);

			JButton ConfirmButton = new JButton("Confirm");
			JButton CancelButton = new JButton("Cancel");

			setLayout(new GridLayout(3, 1));
			add(lbl1);
			add(txtField);

			JPanel grid = new JPanel();
			grid.setLayout(new GridLayout(1, 2));
			grid.add(ConfirmButton);
			grid.add(CancelButton);

			add(grid);

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
			this.dispose();
		}

	}

}
