package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Entity;

public class AddSubentityDialog extends JDialog implements ActionListener {

	JTextField childTxt;
	JTextField parentTxt;

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
			parentTxt = new JTextField();

			JLabel childLbl = new JLabel("Select Child", SwingConstants.CENTER);
			childTxt = new JTextField();

			JButton ConfirmButton = new JButton("Confirm");
			JButton CancelButton = new JButton("Cancel");

			setLayout(new GridLayout(3, 2));
			add(parentLbl);
			add(parentTxt);

			add(childLbl);
			add(childTxt);

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
			ArrayList<String> p = new ArrayList<String>();
			p.add(parentTxt.getText());
			ArrayList<Entity> parent = (ArrayList<Entity>) MainFrame.getInstance().getDb().getData(p);

			ArrayList<String> c = new ArrayList<String>();
			c.add(childTxt.getText());
			ArrayList<Entity> child = (ArrayList<Entity>) MainFrame.getInstance().getDb().getData(c);

			if (!parent.isEmpty() && !child.isEmpty()) {

				MainFrame.getInstance().getDb().deleteEntity(c.get(0));
				parent.get(0).getData().put(child.get(0).getId(), child);
				MainFrame.getInstance().refresh();

			}

			this.dispose();
		}

	}

}
