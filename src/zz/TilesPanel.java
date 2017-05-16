package zz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

/**
 * The Class TilesPanel.
 * @author Manoj Shrestha
 */
public class TilesPanel extends JPanel implements ActionListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4002405786699279293L;

	/** The tile. */
	private JLabel[][] tile = new JLabel[3][3];

	/** The branch node. */
	private Node startNode, smallestNode, branchNode;

	/** The open list. */
	private LinkList openList = new LinkList();

	/** The close list. */
	private LinkList closeList = new LinkList();

	/** The final path. */
	private LinkList finalPath = new LinkList();

	/** The current list node. */
	private ListNode currentListNode;

	// initial matrix to randomize the tiles
	/** The tile matrix. */
	private int[][] tileMatrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

	// matrix to check the end of game
	/** The goal state. */
	private int[][] goalState = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

	/** The temp icon. */
	private Icon tempIcon = new ImageIcon();

	/** The id count. */
	private int idCount; // holds id for each newly created node

	/** The temp int. */
	private int tempInt;
	// create an instance of random no. generator
	/** The rand. */
	private Random rand = new Random();

	/** The count scramble. */
	private int countScramble;

	/** The timer1. */
	private Timer timer, timer1;

	/** The final path timer. */
	private FinalPathTimer finalPathTimer = new FinalPathTimer();

	/** The j empty. */
	private int iEmpty = 2, jEmpty = 2;

	/**
	 * Instantiates a new tiles panel.
	 */
	TilesPanel() {
		setLayout(new GridLayout(3, 3, 5, 5));
		setBorder(new EtchedBorder());

		timer = new Timer(0, this);
		timer1 = new Timer(400, finalPathTimer);
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				tile[i][j] = createJLabel(String.format("Images/%d.jpg",
						tileMatrix[i][j]));

		addTiles();

		// scrambles the tile positions
		randomize();
	}

	/**
	 * Creates the j label.
	 * 
	 * @param path
	 *            the path
	 * @return the j label
	 */
	public JLabel createJLabel(String path) {
		JLabel newLabel = new JLabel();
		newLabel.setIcon(new ImageIcon(getClass().getResource(path)));
		newLabel.setBorder(new BevelBorder(BevelBorder.RAISED));

		return newLabel;
	}

	/**
	 * Check move.
	 * 
	 * @param keyCode
	 *            the key code
	 */
	public void checkMove(int keyCode) {

		switch (keyCode) {
		case KeyEvent.VK_UP:
			if (iEmpty == 2) {
				break;
			} else {
				moveEmptyTileDown();
				iEmpty += 1;
			}
			break;
		case KeyEvent.VK_DOWN:
			if (iEmpty == 0) {
				break;
			} else {
				moveEmptyTileUp();
				iEmpty -= 1;
			}

			break;

		case KeyEvent.VK_LEFT:
			if (jEmpty == 2) {
				break;
			} else {
				moveEmptyTileRight();
				jEmpty += 1;
			}
			break;

		case KeyEvent.VK_RIGHT:

			if (jEmpty == 0) {
				break;
			} else {
				moveEmptyTileLeft();
				jEmpty -= 1;
			}
			break;
		}

	}

	/**
	 * Move empty tile left.
	 */
	private void moveEmptyTileLeft() {
		tempIcon = tile[iEmpty][jEmpty].getIcon();
		tempInt = tileMatrix[iEmpty][jEmpty];

		tile[iEmpty][jEmpty].setIcon(tile[iEmpty][jEmpty - 1].getIcon());
		tileMatrix[iEmpty][jEmpty] = tileMatrix[iEmpty][jEmpty - 1];

		tile[iEmpty][jEmpty - 1].setIcon(tempIcon);
		tileMatrix[iEmpty][jEmpty - 1] = tempInt;
	}

	/**
	 * Move empty tile right.
	 */
	private void moveEmptyTileRight() {
		tempIcon = tile[iEmpty][jEmpty].getIcon();
		tempInt = tileMatrix[iEmpty][jEmpty];

		tile[iEmpty][jEmpty].setIcon(tile[iEmpty][jEmpty + 1].getIcon());
		tileMatrix[iEmpty][jEmpty] = tileMatrix[iEmpty][jEmpty + 1];

		tile[iEmpty][jEmpty + 1].setIcon(tempIcon);
		tileMatrix[iEmpty][jEmpty + 1] = tempInt;
	}

	/**
	 * Move empty tile up.
	 */
	private void moveEmptyTileUp() {
		tempIcon = tile[iEmpty][jEmpty].getIcon();
		tempInt = tileMatrix[iEmpty][jEmpty];

		tile[iEmpty][jEmpty].setIcon(tile[iEmpty - 1][jEmpty].getIcon());
		tileMatrix[iEmpty][jEmpty] = tileMatrix[iEmpty - 1][jEmpty];

		tile[iEmpty - 1][jEmpty].setIcon(tempIcon);
		tileMatrix[iEmpty - 1][jEmpty] = tempInt;
	}

	/**
	 * Move empty tile down.
	 */
	private void moveEmptyTileDown() {
		tempIcon = tile[iEmpty][jEmpty].getIcon();
		tempInt = tileMatrix[iEmpty][jEmpty];

		// move image
		tile[iEmpty][jEmpty].setIcon(tile[iEmpty + 1][jEmpty].getIcon());
		// update matrix
		tileMatrix[iEmpty][jEmpty] = tileMatrix[iEmpty + 1][jEmpty];

		tile[iEmpty + 1][jEmpty].setIcon(tempIcon);
		tileMatrix[iEmpty + 1][jEmpty] = tempInt;
	}

	/**
	 * Solve8puzzle.
	 */
	public void solve8puzzle() {
		System.gc();
		openList.setEmptyList();
		finalPath.setEmptyList();
		closeList.setEmptyList();
		idCount = 0;
		startNode = new Node(0, tileMatrix, idCount, -1);

		openList.insertAtBack(startNode);

		while (!openList.isEmpty()) {
			smallestNode = openList.getSmallestNode();
			if (isSameState(smallestNode.getState(), goalState)) { // goal state
																	// reached
				closeList.insertAtBack(smallestNode);
				break;
			} else // goal state not reached yet and needs further search
			{
				openList.removeNode(smallestNode);
				closeList.insertAtBack(smallestNode);
				// case 00
				// /////////////////////////////////////////////////////////////////
				if (smallestNode.getEmptyX() == 0
						&& smallestNode.getEmptyY() == 0) {
					/* 1st branch */branchNode = moveEmptyRight(smallestNode);
					checkBranchNodeInLists();
					/* 2nd branch */branchNode = moveEmptyDown(smallestNode);
					// System.out.println(" I'm branch 2 of case 00");
					checkBranchNodeInLists();
				}
				// end of case 00
				// //////////////////////////////////////////////////////////////

				// case 01
				// //////////////////////////////////////////////////////////////////

				else if (smallestNode.getEmptyX() == 0
						&& smallestNode.getEmptyY() == 1) {
					/* 1st branch */branchNode = moveEmptyLeft(smallestNode);
					checkBranchNodeInLists();

					/* 2nd branch */branchNode = moveEmptyDown(smallestNode);
					// // System.out.println(" I'm branch 2 of case 01");
					checkBranchNodeInLists();

					/* 3rd branch */branchNode = moveEmptyRight(smallestNode);
					checkBranchNodeInLists();
				}

				// case 01 finishes
				// //////////////////////////////////////////////////////////
				// case 02 starts
				// //////////////////////////////////////////////////////////////

				else if (smallestNode.getEmptyX() == 0
						&& smallestNode.getEmptyY() == 2) {
					/* 1st branch */branchNode = moveEmptyLeft(smallestNode);
					// System.out.println(" I'm branch 1 of case 02");
					checkBranchNodeInLists();

					/* 2nd branch */branchNode = moveEmptyDown(smallestNode);
					// // System.out.println(" I'm branch 2 of case 02");
					checkBranchNodeInLists();

				}/* case 02 */
				// case 02 finishes
				// //////////////////////////////////////////////////////////
				// case 10 starts
				// ////////////////////////////////////////////////////////////

				else if (smallestNode.getEmptyX() == 1
						&& smallestNode.getEmptyY() == 0) {
					/* 1st branch */branchNode = moveEmptyUp(smallestNode);
					checkBranchNodeInLists();

					/* 2nd branch */branchNode = moveEmptyRight(smallestNode);
					checkBranchNodeInLists();

					/* 3rd branch */branchNode = moveEmptyDown(smallestNode);
					checkBranchNodeInLists();
				}
				// case 10 ends
				// ////////////////////////////////////////////////////////////////

				// case 11 starts
				// /////////////////////////////////////////////////////////////
				else if (smallestNode.getEmptyX() == 1
						&& smallestNode.getEmptyY() == 1) {
					/* 1st branch */branchNode = moveEmptyUp(smallestNode);
					checkBranchNodeInLists();

					/* 2nd branch */branchNode = moveEmptyRight(smallestNode);
					checkBranchNodeInLists();

					/* 3rd branch */branchNode = moveEmptyDown(smallestNode);
					// System.out.println(" I'm branch 3 of case 11");
					checkBranchNodeInLists();

					/* 4th branch */branchNode = moveEmptyLeft(smallestNode);
					checkBranchNodeInLists();
				}
				// case 11 ends
				// //////////////////////////////////////////////////////////////
				// case 12 starts
				// //////////////////////////////////////////////////////////////

				else if (smallestNode.getEmptyX() == 1
						&& smallestNode.getEmptyY() == 2) {
					/* 1st branch */branchNode = moveEmptyUp(smallestNode);
					// System.out.println(" I'm branch 1 of case 12");
					checkBranchNodeInLists();

					/* 2nd branch */branchNode = moveEmptyLeft(smallestNode);
					checkBranchNodeInLists();

					/* 3rd branch */branchNode = moveEmptyDown(smallestNode);
					checkBranchNodeInLists();
				}
				// case 12 ends
				// ///////////////////////////////////////////////////////////////

				// case 20 starts
				// ////////////////////////////////////////////////////////////
				// e
				else if (smallestNode.getEmptyX() == 2
						&& smallestNode.getEmptyY() == 0) {
					/* 1st branch */branchNode = moveEmptyUp(smallestNode);
					checkBranchNodeInLists();

					/* 2nd branch */branchNode = moveEmptyRight(smallestNode);
					checkBranchNodeInLists();
				}
				// case 20 ends
				// //////////////////////////////////////////////////////////////

				// case 21 starts
				// ////////////////////////////////////////////////////////////
				else if (smallestNode.getEmptyX() == 2
						&& smallestNode.getEmptyY() == 1) {
					/* 1st branch */branchNode = moveEmptyLeft(smallestNode);
					checkBranchNodeInLists();

					/* 2nd branch */branchNode = moveEmptyUp(smallestNode);
					checkBranchNodeInLists();

					/* 3rd branch */branchNode = moveEmptyRight(smallestNode);
					// System.out.println(" I'm branch 3 of case 21");
					checkBranchNodeInLists();
				}
				// case 21 ends
				// //////////////////////////////////////////////////////////////

				// case 22 starts
				// /////////////////////////////////////////////////////////////
				else if (smallestNode.getEmptyX() == 2
						&& smallestNode.getEmptyY() == 2) {
					/* 1st branch */branchNode = moveEmptyUp(smallestNode);

					checkBranchNodeInLists();
					/* 2nd branch */branchNode = moveEmptyLeft(smallestNode);
					checkBranchNodeInLists();

				}
				// case 22 ends
				// /////////////////////////////////////////////////////////////
			}// end else

		} // end of while( !openList.isEmpty())

		backTrackNodes();
		currentListNode = finalPath.getFirstNode();
		timer1.start();
		resetTilesMatrix();
	} // end of method solve8puzzle()

	private void resetTilesMatrix() {
		iEmpty = jEmpty = 2;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				tileMatrix[i][j] = goalState[i][j];
			}
		}
	}

	/**
	 * Check branch node in lists.
	 */
	public void checkBranchNodeInLists() {
		if (closeList.alreadyExists(branchNode))
			; // ignores the node...
		else // check in open List
		{
			if (!openList.alreadyExists(branchNode)) {
				openList.insertAtBack(branchNode);
			}
		}
	}

	/**
	 * Back track nodes.
	 */
	public void backTrackNodes() {

		Node insertNode = closeList.getLastNode().getNode();

		finalPath.insertAtFront(insertNode);

		ListNode current = closeList.getLastNode().previousNode;

		// backtrack the closelist until we reach the startNode
		while (current != null) {
			if (insertNode.getParentID() == current.getNode().getID()) {
				insertNode = current.getNode();
				finalPath.insertAtFront(insertNode);
			}

			current = current.previousNode;
		}

	}

	/**
	 * Checks if is same state.
	 * 
	 * @param A
	 *            the a
	 * @param B
	 *            the b
	 * @return true, if is same state
	 */
	public boolean isSameState(int[][] A, int[][] B) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (A[i][j] != B[i][j])
					return false;
		return true;
	}

	/**
	 * Adds the tiles.
	 */
	public void addTiles() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				add(tile[i][j]);
	}

	/**
	 * Move empty left.
	 * 
	 * @param currentNode
	 *            the current node
	 * @return the node
	 */
	public Node moveEmptyLeft(Node currentNode) {
		int[][] tmpState = new int[3][3];
		int i, j;
		for (i = 0; i < 3; i++)
			for (j = 0; j < 3; j++)
				tmpState[i][j] = currentNode.getState()[i][j];
		int x = currentNode.getEmptyX();
		int y = currentNode.getEmptyY();

		int tmp = tmpState[x][y];
		tmpState[x][y] = tmpState[x][y - 1];
		tmpState[x][y - 1] = tmp;

		return new Node(currentNode.getG() + 1, tmpState, ++idCount,
				currentNode.getID());
	}

	/**
	 * Move empty right.
	 * 
	 * @param currentNode
	 *            the current node
	 * @return the node
	 */
	public Node moveEmptyRight(Node currentNode) {
		int[][] tmpState = new int[3][3];
		int i, j;
		for (i = 0; i < 3; i++)
			for (j = 0; j < 3; j++)
				tmpState[i][j] = currentNode.getState()[i][j];

		int x = currentNode.getEmptyX();
		int y = currentNode.getEmptyY();

		int tmp = tmpState[x][y];
		tmpState[x][y] = tmpState[x][y + 1];
		tmpState[x][y + 1] = tmp;

		return new Node(currentNode.getG() + 1, tmpState, ++idCount,
				currentNode.getID());
	}

	/**
	 * Move empty up.
	 * 
	 * @param currentNode
	 *            the current node
	 * @return the node
	 */
	public Node moveEmptyUp(Node currentNode) {
		int[][] tmpState = new int[3][3];
		int i, j;
		for (i = 0; i < 3; i++)
			for (j = 0; j < 3; j++)
				tmpState[i][j] = currentNode.getState()[i][j];

		int x = currentNode.getEmptyX();
		int y = currentNode.getEmptyY();

		int tmp = tmpState[x][y];
		tmpState[x][y] = tmpState[x - 1][y];
		tmpState[x - 1][y] = tmp;

		return new Node(currentNode.getG() + 1, tmpState, ++idCount,
				currentNode.getID());
	}

	/**
	 * Move empty down.
	 * 
	 * @param currentNode
	 *            the current node
	 * @return the node
	 */
	public Node moveEmptyDown(Node currentNode) {
		int[][] tmpState = new int[3][3];
		int i, j;
		for (i = 0; i < 3; i++)
			for (j = 0; j < 3; j++)
				tmpState[i][j] = currentNode.getState()[i][j];

		int x = currentNode.getEmptyX();
		int y = currentNode.getEmptyY();

		int tmp = tmpState[x][y];
		tmpState[x][y] = tmpState[x + 1][y];
		tmpState[x + 1][y] = tmp;

		return new Node(currentNode.getG() + 1, tmpState, ++idCount,
				currentNode.getID());
	}

	/**
	 * Randomize.
	 */
	public void randomize() {
		// code to randomize the tiles...

		countScramble = 0;
		timer.start();

	} // end of randomize()

	/**
	 * Check game finish.
	 * 
	 * @return true, if successful
	 */
	public boolean checkGameFinish() {
		if (tileMatrix[0][0] == 1 && tileMatrix[0][1] == 2
				&& tileMatrix[0][2] == 3 && tileMatrix[1][0] == 4
				&& tileMatrix[1][1] == 5 && tileMatrix[1][2] == 6
				&& tileMatrix[2][0] == 7 && tileMatrix[2][1] == 8
				&& tileMatrix[2][2] == 9) {
			JOptionPane.showMessageDialog(TilesPanel.this,
					"Congratulation ! You won the game !!", "Message",
					JOptionPane.INFORMATION_MESSAGE);
			return true;
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent event) {

		int randNum;
		countScramble++;

		if (countScramble < 200) {

			if (iEmpty == 0 && jEmpty == 0) // swap with tile[0][1] or
											// tile[1][0]
			{
				randNum = rand.nextInt(2); // generate 0 or 1

				if (randNum == 0) // swap with tile[0][1]
				{

					tempInt = tileMatrix[0][0];
					tempIcon = tile[0][0].getIcon();
					tileMatrix[0][0] = tileMatrix[0][1];
					tile[0][0].setIcon(tile[0][1].getIcon());
					tileMatrix[0][1] = tempInt;
					tile[0][1].setIcon(tempIcon);

					jEmpty = 1;
				}

				else // swap with the tile[1][0]
				{
					tempInt = tileMatrix[0][0];
					tempIcon = tile[0][0].getIcon();
					tileMatrix[0][0] = tileMatrix[1][0];
					tile[0][0].setIcon(tile[1][0].getIcon());
					tileMatrix[1][0] = tempInt;
					tile[1][0].setIcon(tempIcon);
					iEmpty = 1;
				}

			}

			else if (iEmpty == 0 && jEmpty == 1) // swap with tile[0][0] of
													// tile[0][2] or tile[1][1]
			{
				randNum = rand.nextInt(3); // generate 0 or 1 or 2

				if (randNum == 0) // swap with tile[0][0]
				{

					tempInt = tileMatrix[0][1];
					tempIcon = tile[0][1].getIcon();
					tileMatrix[0][1] = tileMatrix[0][0];
					tile[0][1].setIcon(tile[0][0].getIcon());
					tileMatrix[0][0] = tempInt;
					tile[0][0].setIcon(tempIcon);

					jEmpty = 0;

				} else if (randNum == 1) // swap with tile[0][2]
				{

					tempInt = tileMatrix[0][1];
					tempIcon = tile[0][1].getIcon();
					tileMatrix[0][1] = tileMatrix[0][2];
					tile[0][1].setIcon(tile[0][2].getIcon());
					tileMatrix[0][2] = tempInt;
					tile[0][2].setIcon(tempIcon);

					jEmpty = 2;
				}

				else // swap with tile[1][1]
				{
					tempInt = tileMatrix[0][1];
					tempIcon = tile[0][1].getIcon();
					tileMatrix[0][1] = tileMatrix[1][1];
					tile[0][1].setIcon(tile[1][1].getIcon());
					tileMatrix[1][1] = tempInt;
					tile[1][1].setIcon(tempIcon);

					iEmpty = 1;
				}
			}

			else if (iEmpty == 0 && jEmpty == 2) // swap with tile[0][1] or
													// tile[1][2]
			{
				randNum = rand.nextInt(2); // generate 0 or 1

				if (randNum == 0) // swap with tile[0][1]
				{

					tempInt = tileMatrix[0][2];
					tempIcon = tile[0][2].getIcon();
					tileMatrix[0][2] = tileMatrix[0][1];
					tile[0][2].setIcon(tile[0][1].getIcon());
					tileMatrix[0][1] = tempInt;
					tile[0][1].setIcon(tempIcon);

					jEmpty = 1;
				}

				else // swap with the tile[1][2]
				{
					tempInt = tileMatrix[0][2];
					tempIcon = tile[0][2].getIcon();
					tileMatrix[0][2] = tileMatrix[1][2];
					tile[0][2].setIcon(tile[1][2].getIcon());
					tileMatrix[1][2] = tempInt;
					tile[1][2].setIcon(tempIcon);

					iEmpty = 1;
				}

			}

			else if (iEmpty == 1 && jEmpty == 0) // swap with tile[0][0] or
													// tile[1][1] or tile[2][0]
			{
				randNum = rand.nextInt(3); // generate 0 or 1 or 2

				if (randNum == 0) // swap with tile[0][0]
				{

					tempInt = tileMatrix[1][0];
					tempIcon = tile[1][0].getIcon();
					tileMatrix[1][0] = tileMatrix[0][0];
					tile[1][0].setIcon(tile[0][0].getIcon());
					tileMatrix[0][0] = tempInt;
					tile[0][0].setIcon(tempIcon);

					iEmpty = 0;
				}

				else if (randNum == 1) // swap with tile[1][1]
				{

					tempInt = tileMatrix[1][0];
					tempIcon = tile[1][0].getIcon();
					tileMatrix[1][0] = tileMatrix[1][1];
					tile[1][0].setIcon(tile[1][1].getIcon());
					tileMatrix[1][1] = tempInt;
					tile[1][1].setIcon(tempIcon);

					jEmpty = 1;
				}

				else // swap with tile[2][0]
				{
					// tempInt = tileMatrix[1][0]; tileMatrix[1][0] =
					// tileMatrix[2][0]; tileMatrix[2][0] = tempInt;

					tempInt = tileMatrix[1][0];
					tempIcon = tile[1][0].getIcon();
					tileMatrix[1][0] = tileMatrix[2][0];
					tile[1][0].setIcon(tile[2][0].getIcon());
					tileMatrix[2][0] = tempInt;
					tile[2][0].setIcon(tempIcon);

					iEmpty = 2;
				}
			}

			else if (iEmpty == 1 && jEmpty == 1) // swap with tile[0][1] or
													// tile[1][0] or tile[1][2]
													// or tile[2][1]
			{
				randNum = rand.nextInt(4); // generate 0 or 1 or 2 or 3

				if (randNum == 0) // swap with tile[0][1]
				{
					// tempInt = tileMatrix[1][1]; tileMatrix[1][1] =
					// tileMatrix[0][1]; tileMatrix[0][1] = tempInt;

					tempInt = tileMatrix[1][1];
					tempIcon = tile[1][1].getIcon();
					tileMatrix[1][1] = tileMatrix[0][1];
					tile[1][1].setIcon(tile[0][1].getIcon());
					tileMatrix[0][1] = tempInt;
					tile[0][1].setIcon(tempIcon);

					iEmpty = 0;
				}

				else if (randNum == 1) // swap with tile[1][0]
				{
					// tempInt = tileMatrix[1][1]; tileMatrix[1][1] =
					// tileMatrix[1][0]; tileMatrix[1][0] = tempInt;

					tempInt = tileMatrix[1][1];
					tempIcon = tile[1][1].getIcon();
					tileMatrix[1][1] = tileMatrix[1][0];
					tile[1][1].setIcon(tile[1][0].getIcon());
					tileMatrix[1][0] = tempInt;
					tile[1][0].setIcon(tempIcon);

					jEmpty = 0;
				}

				else if (randNum == 2) // swap with tile[1][2]
				{
					// tempInt = tileMatrix[1][1]; tileMatrix[1][1] =
					// tileMatrix[1][2]; tileMatrix[1][2] = tempInt;

					tempInt = tileMatrix[1][1];
					tempIcon = tile[1][1].getIcon();
					tileMatrix[1][1] = tileMatrix[1][2];
					tile[1][1].setIcon(tile[1][2].getIcon());
					tileMatrix[1][2] = tempInt;
					tile[1][2].setIcon(tempIcon);

					jEmpty = 2;
				}

				else // swap with tile[2][1]
				{
					// tempInt = tileMatrix[1][1]; tileMatrix[1][1] =
					// tileMatrix[2][1]; tileMatrix[2][1] = tempInt;

					tempInt = tileMatrix[1][1];
					tempIcon = tile[1][1].getIcon();
					tileMatrix[1][1] = tileMatrix[2][1];
					tile[1][1].setIcon(tile[2][1].getIcon());
					tileMatrix[2][1] = tempInt;
					tile[2][1].setIcon(tempIcon);

					iEmpty = 2;
				}

			}

			else if (iEmpty == 1 && jEmpty == 2) // swap with tile[0][2] or
													// tile[1][1] or tile[2][2]
			{
				randNum = rand.nextInt(3); // generate 0 or 1 or 2

				if (randNum == 0) // swap with tile[0][2]
				{
					// tempInt = tileMatrix[1][2]; tileMatrix[1][2] =
					// tileMatrix[0][2]; tileMatrix[0][2] = tempInt;

					tempInt = tileMatrix[1][2];
					tempIcon = tile[1][2].getIcon();
					tileMatrix[1][2] = tileMatrix[0][2];
					tile[1][2].setIcon(tile[0][2].getIcon());
					tileMatrix[0][2] = tempInt;
					tile[0][2].setIcon(tempIcon);

					iEmpty = 0;
				}

				else if (randNum == 1) // swap with tile[1][1]
				{
					// tempInt = tileMatrix[1][2]; tileMatrix[1][2] =
					// tileMatrix[1][1]; tileMatrix[1][1] = tempInt;

					tempInt = tileMatrix[1][2];
					tempIcon = tile[1][2].getIcon();
					tileMatrix[1][2] = tileMatrix[1][1];
					tile[1][2].setIcon(tile[1][1].getIcon());
					tileMatrix[1][1] = tempInt;
					tile[1][1].setIcon(tempIcon);

					jEmpty = 1;
				}

				else // swap with tile[2][2]
				{
					// tempInt = tileMatrix[1][2]; tileMatrix[1][2] =
					// tileMatrix[2][2]; tileMatrix[2][2] = tempInt;

					tempInt = tileMatrix[1][2];
					tempIcon = tile[1][2].getIcon();
					tileMatrix[1][2] = tileMatrix[2][2];
					tile[1][2].setIcon(tile[2][2].getIcon());
					tileMatrix[2][2] = tempInt;
					tile[2][2].setIcon(tempIcon);

					iEmpty = 2;
				}
			}

			else if (iEmpty == 2 && jEmpty == 0) // swap with tile[1][0] or
													// tile[2][1]
			{
				randNum = rand.nextInt(2); // generate 0 or 1

				if (randNum == 0) // swap with tile[1][0]
				{
					// tempInt = tileMatrix[2][0]; tileMatrix[2][0] =
					// tileMatrix[1][0]; tileMatrix[1][0] = tempInt;

					tempInt = tileMatrix[2][0];
					tempIcon = tile[2][0].getIcon();
					tileMatrix[2][0] = tileMatrix[1][0];
					tile[2][0].setIcon(tile[1][0].getIcon());
					tileMatrix[1][0] = tempInt;
					tile[1][0].setIcon(tempIcon);

					iEmpty = 1;
				}

				else // swap with the tile[2][1]
				{
					// tempInt = tileMatrix[2][0]; tileMatrix[2][0] =
					// tileMatrix[2][1]; tileMatrix[2][1] = tempInt;

					tempInt = tileMatrix[2][0];
					tempIcon = tile[2][0].getIcon();
					tileMatrix[2][0] = tileMatrix[2][1];
					tile[2][0].setIcon(tile[2][1].getIcon());
					tileMatrix[2][1] = tempInt;
					tile[2][1].setIcon(tempIcon);

					jEmpty = 1;
				}

			}

			else if (iEmpty == 2 && jEmpty == 1) // swap with tile[1][1] of
													// tile[2][0] or tile[2][2]
			{
				randNum = rand.nextInt(3); // generate 0 or 1 or 2

				if (randNum == 0) // swap with tile[1][1]
				{
					// tempInt = tileMatrix[2][1]; tileMatrix[2][1] =
					// tileMatrix[1][1]; tileMatrix[1][1] = tempInt;

					tempInt = tileMatrix[2][1];
					tempIcon = tile[2][1].getIcon();
					tileMatrix[2][1] = tileMatrix[1][1];
					tile[2][1].setIcon(tile[1][1].getIcon());
					tileMatrix[1][1] = tempInt;
					tile[1][1].setIcon(tempIcon);

					iEmpty = 1;

				} else if (randNum == 1) // swap with tile[2][0]
				{
					// tempInt = tileMatrix[2][1]; tileMatrix[2][1] =
					// tileMatrix[2][0]; tileMatrix[2][0] = tempInt;

					tempInt = tileMatrix[2][1];
					tempIcon = tile[2][1].getIcon();
					tileMatrix[2][1] = tileMatrix[2][0];
					tile[2][1].setIcon(tile[2][0].getIcon());
					tileMatrix[2][0] = tempInt;
					tile[2][0].setIcon(tempIcon);

					jEmpty = 0;
				}

				else // swap with tile[2][2]
				{
					// tempInt = tileMatrix[2][1]; tileMatrix[2][1] =
					// tileMatrix[2][2]; tileMatrix[2][2] = tempInt;

					tempInt = tileMatrix[2][1];
					tempIcon = tile[2][1].getIcon();
					tileMatrix[2][1] = tileMatrix[2][2];
					tile[2][1].setIcon(tile[2][2].getIcon());
					tileMatrix[2][2] = tempInt;
					tile[2][2].setIcon(tempIcon);

					jEmpty = 2;
				}
			}

			else if (iEmpty == 2 && jEmpty == 2) // swap with tile[1][2] or
													// tile[2][1]
			{
				randNum = rand.nextInt(2); // generate 0 or 1

				if (randNum == 0) // swap with tile[1][2]
				{
					// tempInt = tileMatrix[2][2]; tileMatrix[2][2] =
					// tileMatrix[1][2]; tileMatrix[1][2] = tempInt;

					tempInt = tileMatrix[2][2];
					tempIcon = tile[2][2].getIcon();
					tileMatrix[2][2] = tileMatrix[1][2];
					tile[2][2].setIcon(tile[1][2].getIcon());
					tileMatrix[1][2] = tempInt;
					tile[1][2].setIcon(tempIcon);

					iEmpty = 1;
				}

				else // swap with the tile[2][1]
				{
					// tempInt = tileMatrix[2][2]; tileMatrix[2][2] =
					// tileMatrix[2][1]; tileMatrix[2][1] = tempInt;

					tempInt = tileMatrix[2][2];
					tempIcon = tile[2][2].getIcon();
					tileMatrix[2][2] = tileMatrix[2][1];
					tile[2][2].setIcon(tile[2][1].getIcon());
					tileMatrix[2][1] = tempInt;
					tile[2][1].setIcon(tempIcon);

					jEmpty = 1;
				}
			}
		} else // if countScramble >= 200
		{
			stopTimer();
		}

	}

	/**
	 * Stop timer.
	 */
	public void stopTimer() {
		timer.stop();
		// System.out.println("stopTimer called !!");
	}

	/**
	 * The Class FinalPathTimer.
	 * 
	 * @author Manoj Shrestha
	 */
	class FinalPathTimer implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		public void actionPerformed(ActionEvent event) {

			if (currentListNode != null) {
				for (int i = 0; i < 3; i++)
					for (int j = 0; j < 3; j++) {
						tile[i][j].setIcon(new ImageIcon(getClass()
								.getResource(
										String.format("Images/%d.jpg",
												currentListNode.getNode()
														.getState()[i][j]))));
					}
				currentListNode = currentListNode.getNext();
			} else
				stopTimer1();
		} // end method actionPerformed

		/**
		 * Stop timer1.
		 */
		public void stopTimer1() {
			timer1.stop();
		}
	} // end class CurrentMediaTime

}
