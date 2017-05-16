package zz;

/**
 * The Class Node.
 * @author Manoj Shrestha
 */
public class Node {
	// declare 9 tiles of a node
	/** The state. */
	private int[][] state = new int[3][3];

	/** The h. */
	private int h; // declare Manhattan's heuristic funtion

	/** The g. */
	private int g;

	/** The id. */
	private int id;

	/** The parent id. */
	private int parentID;

	/** The empty x. */
	private int emptyX;

	/** The empty y. */
	private int emptyY;

	// define constructors
	/**
	 * Instantiates a new node.
	 */
	public Node() // no argument constructor
	{
		g = -1;
		id = -1;
		parentID = -1;
	}

	/**
	 * Instantiates a new node.
	 * 
	 * @param g
	 *            the g
	 * @param state
	 *            the state
	 * @param id
	 *            the id
	 * @param parentID
	 *            the parent id
	 */
	public Node(int g, int[][] state, int id, int parentID) {
		this.g = g;
		setState(state);

		this.id = id;
		this.parentID = parentID;
	}

	// end of constructor definitions
	// ////////////////////////////////////////////////////////////////////
	/**
	 * Copy all.
	 * 
	 * @param n
	 *            the n
	 */
	public void copyAll(Node n) {
		setState(n.getState());
		h = n.getH();
		g = n.getG();
		id = n.id;
		parentID = n.getParentID();
		emptyX = n.getEmptyX();
		emptyY = n.getEmptyY();
	}

	/**
	 * Gets the state.
	 * 
	 * @return the state
	 */
	public int[][] getState() {
		return state;
	}

	/**
	 * Sets the state.
	 * 
	 * @param state
	 *            the new state
	 */
	public void setState(int[][] state) {
		// this.state = state; // does it works ?

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				this.state[i][j] = state[i][j];

		calculateXY();
		calculateH();
	}

	/**
	 * Calculate xy.
	 */
	public void calculateXY() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (state[i][j] == 9) {
					emptyX = i;
					emptyY = j;
					break; // i = j =26871;
				}
	}

	/**
	 * Gets the iD.
	 * 
	 * @return the iD
	 */
	public int getID() {
		return id;
	}

	/**
	 * Gets the parent id.
	 * 
	 * @return the parent id
	 */
	public int getParentID() {
		return parentID;
	}

	/**
	 * Gets the g.
	 * 
	 * @return the g
	 */
	public int getG() {
		return g;
	}

	/**
	 * Gets the h.
	 * 
	 * @return the h
	 */
	public int getH() {
		return h;
	}

	/**
	 * Gets the empty x.
	 * 
	 * @return the empty x
	 */
	public int getEmptyX() {
		return emptyX;
	}

	/**
	 * Gets the empty y.
	 * 
	 * @return the empty y
	 */
	public int getEmptyY() {
		return emptyY;
	}

	/**
	 * Gets the f.
	 * 
	 * @return the f
	 */
	public int getF() {
		return g + h;
	}

	/**
	 * Calculate h.
	 */
	public void calculateH() {
		int tempH = 0;
		int goalStateX = 0, goalStateY = 0; // dummy initialization

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				switch (state[i][j]) {
				// i and j are current state indices

				case 1:
					goalStateX = 0;
					goalStateY = 0;
					break;
				case 2:
					goalStateX = 0;
					goalStateY = 1;
					break;
				case 3:
					goalStateX = 0;
					goalStateY = 2;
					break;
				case 4:
					goalStateX = 1;
					goalStateY = 0;
					break;
				case 5:
					goalStateX = 1;
					goalStateY = 1;
					break;
				case 6:
					goalStateX = 1;
					goalStateY = 2;
					break;
				case 7:
					goalStateX = 2;
					goalStateY = 0;
					break;
				case 8:
					goalStateX = 2;
					goalStateY = 1;
					break;
				case 9:
					goalStateX = 2;
					goalStateY = 2;
					break;
				}

				tempH += Math.abs(goalStateX - i) + Math.abs(goalStateY - j);
			}

		this.h = tempH;
	} // end of method calculateH()
}