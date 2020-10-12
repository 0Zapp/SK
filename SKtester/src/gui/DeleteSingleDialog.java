package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DeleteSingleDialog extends JDialog {

	public DeleteSingleDialog(Frame parent, String title, boolean modal) {

		super(parent, title, modal);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		setSize((screenWidth / 10) * 4, (screenHeight / 10) * 4);
		setLocationRelativeTo(null);

		try {

			JLabel lbl1 = new JLabel("Mihajlo Krsmanovic RN17-2018", SwingConstants.CENTER);

			JLabel lbl2 = new JLabel("Marko Nedeljkovic RN20-2018", SwingConstants.CENTER);

			JPanel leftBorder = new JPanel();
			leftBorder.setLayout(new BorderLayout());

			leftBorder.add(lbl1, BorderLayout.SOUTH);

			JPanel rightBorder = new JPanel();
			rightBorder.setLayout(new BorderLayout());

			rightBorder.add(lbl2, BorderLayout.SOUTH);

			JPanel pane = new JPanel();
			pane.setLayout(new GridLayout(1, 2));
			pane.add(leftBorder);
			pane.add(rightBorder);

			add(pane, BorderLayout.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
