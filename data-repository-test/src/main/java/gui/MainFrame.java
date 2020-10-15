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

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.tree.TreePath;

import actions.ActionManager;

public class MainFrame extends JFrame implements ClipboardOwner {

	private MainFrame() {

	}

	private static MainFrame instance = null;
	private ActionManager actionManager;

	private TreePath pathToParentOfSelectedNode;

	private JScrollPane scroll;

	private Toolkit kit;
	private Dimension screenSize;
	private int screenWidth;
	private int screenHeight;
	private JSplitPane split;
	private MyToolbar toolbar;
	private JPanel entityView;

	private Clipboard clipboard = new Clipboard("clipboard");

	private void initialise() {
		actionManager = new ActionManager();
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

		scroll = new JScrollPane();
		scroll.setPreferredSize(new Dimension(250, 250));
		scroll.setMinimumSize((new Dimension(200, 200)));
		scroll.setMaximumSize(new Dimension(300, 300));

		entityView = new JPanel();

		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, entityView);
		add(split, BorderLayout.CENTER);
		split.setDividerLocation(250);
		split.setEnabled(false);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {

				String ObjButtons[] = { "Yes", "No", "Cancel" };
				int PromptResult = JOptionPane.showOptionDialog(null, "Do you want to save the changes you made?",
						"Project", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons,
						ObjButtons[0]);
				if (PromptResult == 0) {// yes

					System.exit(0);
				} else if (PromptResult == 1) {// no
					System.exit(0);
				} else if (PromptResult == 2) {// cancel

				}

			}
		});

	}

	public ActionManager getActionManager() {
		return actionManager;
	}

	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
			instance.initialise();

		}
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
}
