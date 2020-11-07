package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SearchDialog extends JDialog implements ActionListener {

	JTextField txtField;

	public SearchDialog(Frame parent, String title, boolean modal) {

		super(parent, title, modal);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		setSize((screenWidth / 3), (screenHeight / 15) * 2);
		setLocationRelativeTo(null);

		try {

			JLabel lbl1 = new JLabel("Search (key:value,key:value...):", SwingConstants.CENTER);
			txtField = new JTextField("", SwingConstants.CENTER);

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

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Cancel")) {
			this.dispose();
		} else {
			String[] tokens = txtField.getText().split(":");
			if (tokens.length == 2) {
				if (tokens[0].toLowerCase().equals("id")) {
					MainFrame.getInstance().refresh(MainFrame.getInstance().getDb().searchById(tokens[1]));
					this.dispose();
				} else if (tokens[0].toLowerCase().equals("name")) {
					MainFrame.getInstance().refresh(MainFrame.getInstance().getDb().searchByName(tokens[1]));
					this.dispose();
				} else {
					MainFrame.getInstance().refresh(MainFrame.getInstance().getDb().searchData(tokens[0], tokens[1]));
					this.dispose();
				}
			} else if (txtField.getText().isEmpty()) {
				MainFrame.getInstance().refresh();
				this.dispose();
			} else {
				Map<String, Object> data = new HashMap<String, Object>();
				try {

					String tok[] = txtField.getText().split(",");
					for (String token : tok) {
						String pairs[] = token.split(":");
						data.put(pairs[0], pairs[1]);
					}

				} catch (Exception exception) {
					System.out.println("Pogresan format");
					return;
				}
				MainFrame.getInstance().refresh(MainFrame.getInstance().getDb().searchData(data));
				this.dispose();
			}

		}

	}

}
