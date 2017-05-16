package zz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.border.EtchedBorder;

/**
 * The Class MainInterface.
 * @author Manoj Shrestha
 */
class MainInterface extends JFrame implements KeyListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1574712723348553932L;

	/** The all listener. */
	private TestAction allListener = new TestAction();

	// private JToolBar tBar = new JToolBar();

	/** The my panel. */
	private TilesPanel myPanel = new TilesPanel();

	/** The file menu. */
	private JMenu fileMenu;
	// declaring File menu items.......
	/** The new game. */
	private JMenuItem newGame;

	/** The algorithm. */
	private JMenu algorithm;

	/** The a star. */
	private JMenuItem aStar;

	/** The help menu. */
	private JMenu helpMenu;

	/** The about help. */
	private JMenuItem aboutHelp;

	/** The menu bar. */
	private JMenuBar menuBar = new JMenuBar();

	/**
	 * Instantiates a new main interface.
	 */
	MainInterface() {
	};

	/**
	 * Instantiates a new main interface.
	 * 
	 * @param name
	 *            the name
	 */
	public MainInterface(String name) {
		setTitle(name);
		this.setLayout(new BorderLayout());

		// add(optPanel,BorderLayout.CENTER);
		add(myPanel, BorderLayout.EAST);

		// creating menus...
		fileMenu = createJMenu("Game", 'F');
		helpMenu = createJMenu("Help", 'H');
		algorithm = createJMenu("Algorithm", 'G');

		newGame = createJMenuItem("New Game    ", 'N', 'N',
				InputEvent.CTRL_MASK, allListener);
		fileMenu.add(newGame);

		aStar = createJMenuItem("A*   ", 'A', 'A', InputEvent.CTRL_MASK,
				allListener);
		algorithm.add(aStar);

		aboutHelp = createJMenuItem("About...  ", 'A', ' ', 0, allListener);
		helpMenu.add(aboutHelp);

		menuBar.setSelected(fileMenu);

		menuBar.setBorder(new EtchedBorder());
		menuBar.add(fileMenu);
		menuBar.add(algorithm);
		menuBar.add(helpMenu);

		this.setJMenuBar(menuBar);

		addKeylistener();
		this.setFocusable(true);
	} // end of MainInterface(String name) constructor

	/**
	 * Solve.
	 */
	public void solve() {
		myPanel.solve8puzzle();
	}

	// event handlers
	/**
	 * The Class TestAction.
	 */
	class TestAction extends AbstractAction {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1860566217437556532L;

		/**
		 * Instantiates a new test action.
		 */
		public TestAction() {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		public void actionPerformed(ActionEvent event) {

			if (event.getSource() == newGame) {
				removeKeylistener();
				addKeylistener();

				myPanel.randomize();
			}

			else if (event.getSource() == aboutHelp) {
				JFrame tempFrame = new JFrame("About ...");
				tempFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				tempFrame.getContentPane().add(
						new JLabel(new ImageIcon(getClass().getResource(
								"Images/About.jpg"))));
				tempFrame.pack();
				tempFrame.setResizable(false);
				tempFrame.setLocationRelativeTo(null);
				tempFrame.setVisible(true);
			}

			if (event.getSource() == aStar) {
				myPanel.solve8puzzle();
				removeKeylistener();
			}

		} // end of method ActionPerformed()
	} // end of class TestAction

	/**
	 * Adds the button.
	 * 
	 * @param toolBar
	 *            the tool bar
	 * @param button
	 *            the button
	 * @param toolTipText
	 *            the tool tip text
	 */
	void addButton(JToolBar toolBar, JButton button, String toolTipText) {
		toolBar.add(button);
		button.setText(null);
		button.setToolTipText(toolTipText);
	}

	/**
	 * Creates the j menu.
	 * 
	 * @param jMenuName
	 *            the j menu name
	 * @param mnemonicChar
	 *            the mnemonic char
	 * @return the j menu
	 */
	JMenu createJMenu(String jMenuName, char mnemonicChar) {
		JMenu myMenu = new JMenu(jMenuName);
		myMenu.getPopupMenu().setLightWeightPopupEnabled(false);
		myMenu.setMnemonic(mnemonicChar);

		return myMenu;
	}

	/**
	 * Creates the j menu item.
	 * 
	 * @param jMenuName
	 *            the j menu name
	 * @param mnemonicChar
	 *            the mnemonic char
	 * @param keyChar
	 *            the key char
	 * @param modifierInt
	 *            the modifier int
	 * @param action
	 *            the action
	 * @return the j menu item
	 */
	JMenuItem createJMenuItem(String jMenuName, char mnemonicChar, int keyChar,
			int modifierInt, AbstractAction action) {
		JMenuItem menuItem = new JMenuItem(jMenuName);
		menuItem.setMnemonic(mnemonicChar);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(keyChar, modifierInt));
		menuItem.addActionListener(action);

		return menuItem;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	public void keyTyped(KeyEvent event) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	public void keyPressed(KeyEvent event) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	public void keyReleased(KeyEvent event) {

		// calls the key event handlers if any arrow keys are pressed
		if ((event.getKeyCode() == KeyEvent.VK_UP)
				|| (event.getKeyCode() == KeyEvent.VK_LEFT)
				|| (event.getKeyCode() == KeyEvent.VK_RIGHT)
				|| (event.getKeyCode() == KeyEvent.VK_DOWN)) {

			// moves tile if valid
			myPanel.checkMove(event.getKeyCode());

			if (myPanel.checkGameFinish())
				removeKeylistener();
		}

	}

	/**
	 * Removes the keylistener.
	 */
	public void removeKeylistener() {
		this.removeKeyListener(this);
	}

	/**
	 * Adds the keylistener.
	 */
	public void addKeylistener() {
		this.addKeyListener(this);
	}

}