package gui;

import java.awt.BorderLayout;
import java.awt.Component;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import model.Entity;

public class UpdateDialog extends JDialog implements ActionListener {

	JTextField idField;
	JTextField nameField;
	JTextField dataArea;

	public UpdateDialog(Frame parent, String title, boolean modal) {

		super(parent, title, modal);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		setSize(screenWidth / 5, screenHeight / 4);
		setLocationRelativeTo(null);

		try {

			JLabel id = new JLabel("ID", SwingConstants.CENTER);
			idField = new JTextField("", SwingConstants.CENTER);

			JLabel name = new JLabel("Name", SwingConstants.CENTER);
			nameField = new JTextField("", SwingConstants.CENTER);

			JLabel data = new JLabel("Data (naziv1:vrednost1,naziv2:vrednost2...), SwingConstants.CENTER");
			dataArea = new JTextField();

			JButton ConfirmButton = new JButton("Confirm");
			JButton CancelButton = new JButton("Cancel");

			setLayout(new GridLayout(5, 1));

			JPanel IDgrid = new JPanel();
			IDgrid.setLayout(new GridLayout(1, 2));
			IDgrid.add(id);
			IDgrid.add(idField);
			add(IDgrid);

			JPanel nameGrid = new JPanel();
			nameGrid.setLayout(new GridLayout(1, 2));
			nameGrid.add(name);
			nameGrid.add(nameField);
			add(nameGrid);

			add(data);
			add(dataArea);

			JPanel buttonGrid = new JPanel();
			buttonGrid.setLayout(new GridLayout(1, 2));
			buttonGrid.add(ConfirmButton);
			buttonGrid.add(CancelButton);
			add(buttonGrid);

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
			String ID = idField.getText();
			String name = nameField.getText();
			Map<String, Object> data = new HashMap<String, Object>();
			String area = dataArea.getText();
			try {
				if (!area.equals("")) {
					String tokens[] = area.split(",");
					for (String token : tokens) {
						String pairs[] = token.split(":");
						data.put(pairs[0], pairs[1]);
					}
				}
			} catch (Exception exception) {
				System.out.println("Pogresan format u Data polju");
				return;
			}

			Entity entity = new Entity(ID, name, data);
			if (MainFrame.getInstance().getDb().editEntity(entity)) {
				MainFrame.getInstance().refresh();
				this.dispose();
			} else {
				System.out.println("Id is not valid");
			}

		}
	}

}
