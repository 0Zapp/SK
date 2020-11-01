package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.TreePath;

import DBHandler.DBHandler;
import actions.ActionManager;
import model.Entity;

public class MainFrame extends JFrame implements ClipboardOwner {

	private DBHandler db;
	private ArrayList<Entity> entities;

	private MainFrame(DBHandler db) {
		this.db = db;
	}

	private static MainFrame instance = null;
	private ActionManager actionManager;

	private TreePath pathToParentOfSelectedNode;

	private Toolkit kit;
	private Dimension screenSize;
	private int screenWidth;
	private int screenHeight;
	private MyToolbar toolbar;
	private JTable entityView;
	private JScrollPane sp;

	private Clipboard clipboard = new Clipboard("clipboard");

	private void initialise() {
		actionManager = new ActionManager();
		db.loadData();
		entities = (ArrayList<Entity>) db.getData();
		initialiseGUI();

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void initialiseGUI() {

		toolbar = new MyToolbar();
		add(toolbar, BorderLayout.NORTH);

		kit = Toolkit.getDefaultToolkit();
		screenSize = kit.getScreenSize();
		screenHeight = screenSize.height;
		screenWidth = screenSize.width;

		setSize(screenWidth / 2, screenHeight / 2);

		setTitle("Project");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setLocationRelativeTo(null);

		String data[][] = generateData();
		String column[] = { "ID", "NAME", "DATA" };
		entityView = new JTable(new DefaultTableModel(data, column));
		entityView.getColumnModel().getColumn(0).setPreferredWidth(50);
		entityView.getColumnModel().getColumn(1).setPreferredWidth(50);
		entityView.getColumnModel().getColumn(2).setPreferredWidth(600);
		sp = new JScrollPane(entityView);

		add(sp, BorderLayout.CENTER);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {

				String ObjButtons[] = { "Yes", "No", "Cancel" };
				int PromptResult = JOptionPane.showOptionDialog(null, "Do you want to save the changes you made?",
						"Project", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons,
						ObjButtons[0]);
				if (PromptResult == 0) {// yes
					db.saveData();
					System.exit(0);
				} else if (PromptResult == 1) {// no
					System.exit(0);
				} else if (PromptResult == 2) {// cancel

				}

			}
		});

	}

	private String[][] generateData() {
		String[][] data = new String[entities.size()][3];
		for (int i = 0; i < entities.size(); i++) {
			data[i][0] = entities.get(i).getId();
			data[i][1] = entities.get(i).getName();
			data[i][2] = entities.get(i).getData().toString();
		}
		return data;
	}

	public ActionManager getActionManager() {
		return actionManager;
	}

	public static MainFrame getInstance(DBHandler db) {
		if (instance == null) {
			instance = new MainFrame(db);
			instance.initialise();
		}
		return instance;
	}

	public static MainFrame getInstance() {
		return instance;
	}

	public TreePath getPathToParentOfSelectedNode() {
		return pathToParentOfSelectedNode;
	}

	public Clipboard getClipboard() {
		return clipboard;
	}

	public void lostOwnership(Clipboard clipboard, Transferable contents) {
		// TODO Auto-generated method stub
		System.out.println("lostOwnership");

	}

	public DBHandler getDb() {
		return db;
	}

	public void refresh() {
		entities = (ArrayList<Entity>) db.getData();
		String data[][] = generateData();
		DefaultTableModel dm = (DefaultTableModel) entityView.getModel();
		int rowCount = dm.getRowCount();
		// Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			dm.removeRow(i);
		}
		for (String[] e : data) {
			DefaultTableModel model = (DefaultTableModel) entityView.getModel();
			model.addRow(e);
		}

	}

	public void refresh(List<Entity> searchData) {
		entities = (ArrayList<Entity>) searchData;
		String data[][] = generateData();
		DefaultTableModel dm = (DefaultTableModel) entityView.getModel();
		int rowCount = dm.getRowCount();
		// Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			dm.removeRow(i);
		}
		for (String[] e : data) {
			DefaultTableModel model = (DefaultTableModel) entityView.getModel();
			model.addRow(e);
		}
		
	}
}
