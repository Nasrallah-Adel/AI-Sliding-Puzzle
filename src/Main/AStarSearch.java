package Main;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Defines an A* search to be performed on a qualifying puzzle. Currently
 * supports 8puzzle and FWGC.
 *
 * @author Michael Langston && Gabe Ferrer
 */
public class AStarSearch {

    /**
     * Initialization function for 8puzzle A*Search
     *
     * @param board - The starting state, represented as a linear array of
     * length 9 forming 3 meta-rows.
     */
    public static void search(int[] board, boolean d, char heuristic) {
        SearchNode root = new SearchNode(new EightPuzzleState(board));
        Queue<SearchNode> q = new LinkedList<SearchNode>();
        q.add(root);

        int searchCount = 1; // counter for number of iterations

        while (!q.isEmpty()) // while the queue is not empty
        {
            SearchNode tempNode = (SearchNode) q.poll();

            // if the tempNode is not the goal state
            if (!tempNode.getCurState().isGoal()) {
                // generate tempNode's immediate successors
                ArrayList<State> tempSuccessors = tempNode.getCurState()
                        .genSuccessors();
                ArrayList<SearchNode> nodeSuccessors = new ArrayList<SearchNode>();

                /*
				 * Loop through the successors, wrap them in a SearchNode, check
				 * if they've already been evaluated, and if not, add them to
				 * the queue
                 */
                for (int i = 0; i < tempSuccessors.size(); i++) {
                    SearchNode checkedNode;
                    // make the node
                    if (heuristic == 'o') {
                        /*
						 * Create a new SearchNode, with tempNode as the parent,
						 * tempNode's cost + the new cost (1) for this state,
						 * and the Out of Place h(n) value
                         */
                        checkedNode = new SearchNode(tempNode,
                                tempSuccessors.get(i), tempNode.getCost()
                                + tempSuccessors.get(i).findCost(),
                                ((EightPuzzleState) tempSuccessors.get(i))
                                        .getOutOfPlace());
                    } else {
                        // See previous comment
                        checkedNode = new SearchNode(tempNode,
                                tempSuccessors.get(i), tempNode.getCost()
                                + tempSuccessors.get(i).findCost(),
                                ((EightPuzzleState) tempSuccessors.get(i))
                                        .getManDist());
                    }

                    // Check for repeats before adding the new node
                    if (!checkRepeats(checkedNode)) {
                        nodeSuccessors.add(checkedNode);
                    }
                }

                // Check to see if nodeSuccessors is empty. If it is, continue
                // the loop from the top
                if (nodeSuccessors.size() == 0) {
                    continue;
                }

                SearchNode lowestNode = nodeSuccessors.get(0);

                /*
				 * This loop finds the lowest f(n) in a node, and then sets that
				 * node as the lowest.
                 */
                for (int i = 0; i < nodeSuccessors.size(); i++) {
                    if (lowestNode.getFCost() > nodeSuccessors.get(i)
                            .getFCost()) {
                        lowestNode = nodeSuccessors.get(i);
                    }
                }

                int lowestValue = (int) lowestNode.getFCost();

                // Adds any nodes that have that same lowest value.
                for (int i = 0; i < nodeSuccessors.size(); i++) {
                    if (nodeSuccessors.get(i).getFCost() == lowestValue) {
                        q.add(nodeSuccessors.get(i));
                    }
                }

                searchCount++;
            } else // The goal state has been found. Print the path it took to get to
            // it.
            {
                // Use a stack to track the path from the starting state to the
                // goal state
                Stack<SearchNode> solutionPath = new Stack<SearchNode>();
                solutionPath.push(tempNode);
                tempNode = tempNode.getParent();

                while (tempNode.getParent() != null) {
                    solutionPath.push(tempNode);
                    tempNode = tempNode.getParent();
                }
                solutionPath.push(tempNode);

                // The size of the stack before looping through and emptying it.
                int loopSize = solutionPath.size();

                for (int i = 0; i < loopSize; i++) {
                    tempNode = solutionPath.pop();
                    tempNode.getCurState().printState();
                    System.out.println();
                    System.out.println();
                }
                System.out.println("The cost was: " + tempNode.getCost());
                if (d) {
                    System.out.println("The number of nodes examined: "
                            + searchCount);
                }

                System.exit(0);
            }
        }

        // This should never happen with our current puzzles.
        System.out.println("Error! No solution found!");

    }

    /*
	 * Helper method to check to see if a SearchNode has already been evaluated.
	 * Returns true if it has, false if it hasn't.
     */
    private static boolean checkRepeats(SearchNode n) {
        boolean retValue = false;
        SearchNode checkNode = n;

        // While n's parent isn't null, check to see if it's equal to the node
        // we're looking for.
        while (n.getParent() != null && !retValue) {
            if (n.getParent().getCurState().equals(checkNode.getCurState())) {
                retValue = true;
            }
            n = n.getParent();
        }

        return retValue;
    }

}

class SearchNode {

    private State curState;
    private SearchNode parent;
    private double cost; // cost to get to this state
    private double hCost; // heuristic cost
    private double fCost; // f(n) cost

    /**
     * Constructor for the root SearchNode
     *
     * @param s the state passed in
     */
    public SearchNode(State s) {
        curState = s;
        parent = null;
        cost = 0;
        hCost = 0;
        fCost = 0;
    }

    /**
     * Constructor for all other SearchNodes
     *
     * @param prev the parent node
     * @param s the state
     * @param c the g(n) cost to get to this node
     * @param h the h(n) cost to get to this node
     */
    public SearchNode(SearchNode prev, State s, double c, double h) {
        parent = prev;
        curState = s;
        cost = c;
        hCost = h;
        fCost = cost + hCost;
    }

    /**
     * @return the curState
     */
    public State getCurState() {
        return curState;
    }

    /**
     * @return the parent
     */
    public SearchNode getParent() {
        return parent;
    }

    /**
     * @return the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     *
     * @return the heuristic cost
     */
    public double getHCost() {
        return hCost;
    }

    /**
     *
     * @return the f(n) cost for A*
     */
    public double getFCost() {
        return fCost;
    }
}

interface State {
    // determine if current state is goal

    boolean isGoal();

    // generate successors to the current state
    ArrayList<State> genSuccessors();

    // determine cost from initial state to THIS state
    double findCost();

    // print the current state
    public void printState();

    // compare the actual state data
    public boolean equals(State s);
}

class EightPuzzleState implements State {

    private final int PUZZLE_SIZE = 9;
    private int outOfPlace = 0;

    private int manDist = 0;

    private final int[] GOAL = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0};
    private int[] curBoard;

    /**
     * Constructor for EightPuzzleState
     *
     * @param board - the board representation for the new state to be
     * constructed
     */
    public EightPuzzleState(int[] board) {
        curBoard = board;
        setOutOfPlace();
        setManDist();
    }

    /**
     * How much it costs to come to this state
     */
    @Override
    public double findCost() {
        return 1;
    }

    /*
	 * Set the 'tiles out of place' distance for the current board
     */
    private void setOutOfPlace() {
        for (int i = 0; i < curBoard.length; i++) {
            if (curBoard[i] != GOAL[i]) {
                outOfPlace++;
            }
        }
    }

    /*
	 * Set the Manhattan Distance for the current board
     */
    private void setManDist() {
        // linearly search the array independent of the nested for's below
        int index = -1;

        // just keeps track of where we are on the board (relatively, can't use
        // 0 so these
        // values need to be shifted to the right one place)
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                index++;

                // sub 1 from the val to get the index of where that value
                // should be
                int val = (curBoard[index] - 1);

                /*
				 * If we're not looking at the hole. The hole will be at
				 * location -1 since we subtracted 1 before to turn val into the
				 * index
                 */
                if (val != -1) {
                    // Horizontal offset, mod the tile value by the horizontal
                    // dimension
                    int horiz = val % 3;
                    // Vertical offset, divide the tile value by the vertical
                    // dimension
                    int vert = val / 3;

                    manDist += Math.abs(vert - (y)) + Math.abs(horiz - (x));
                }
                // If we are looking at the hole, skip it
            }
        }
    }

    /*
	 * Attempt to locate the "0" spot on the current board
	 * 
	 * @return the index of the "hole" (or 0 spot)
     */
    private int getHole() {
        // If returning -1, an error has occured. The "hole" should always exist
        // on the board and should always be found by the below loop.
        int holeIndex = -1;

        for (int i = 0; i < PUZZLE_SIZE; i++) {
            if (curBoard[i] == 0) {
                holeIndex = i;
            }
        }
        return holeIndex;
    }

    /**
     * Getter for the outOfPlace value
     *
     * @return the outOfPlace h(n) value
     */
    public int getOutOfPlace() {
        return outOfPlace;
    }

    /**
     * Getter for the Manhattan Distance value
     *
     * @return the Manhattan Distance h(n) value
     */
    public int getManDist() {
        return manDist;
    }

    /*
	 * Makes a copy of the array passed to it
     */
    private int[] copyBoard(int[] state) {
        int[] ret = new int[PUZZLE_SIZE];
        for (int i = 0; i < PUZZLE_SIZE; i++) {
            ret[i] = state[i];
        }
        return ret;
    }

    /**
     * Is thought about in terms of NO MORE THAN 4 operations. Can slide tiles
     * from 4 directions if hole is in middle Two directions if hole is at a
     * corner three directions if hole is in middle of a row
     *
     * @return an ArrayList containing all of the successors for that state
     */
    @Override
    public ArrayList<State> genSuccessors() {
        ArrayList<State> successors = new ArrayList<State>();
        int hole = getHole();

        // try to generate a state by sliding a tile leftwise into the hole
        // if we CAN slide into the hole
        if (hole != 0 && hole != 3 && hole != 6) {
            /*
			 * we can slide leftwise into the hole, so generate a new state for
			 * this condition and throw it into successors
             */
            swapAndStore(hole - 1, hole, successors);
        }

        // try to generate a state by sliding a tile topwise into the hole
        if (hole != 6 && hole != 7 && hole != 8) {
            swapAndStore(hole + 3, hole, successors);
        }

        // try to generate a state by sliding a tile bottomwise into the hole
        if (hole != 0 && hole != 1 && hole != 2) {
            swapAndStore(hole - 3, hole, successors);
        }
        // try to generate a state by sliding a tile rightwise into the hole
        if (hole != 2 && hole != 5 && hole != 8) {
            swapAndStore(hole + 1, hole, successors);
        }

        return successors;
    }

    /*
	 * Switches the data at indices d1 and d2, in a copy of the current board
	 * creates a new state based on this new board and pushes into s.
     */
    private void swapAndStore(int d1, int d2, ArrayList<State> s) {
        int[] cpy = copyBoard(curBoard);
        int temp = cpy[d1];
        cpy[d1] = curBoard[d2];
        cpy[d2] = temp;
        s.add((new EightPuzzleState(cpy)));
    }

    /**
     * Check to see if the current state is the goal state.
     *
     * @return - true or false, depending on whether the current state matches
     * the goal
     */
    @Override
    public boolean isGoal() {
        if (Arrays.equals(curBoard, GOAL)) {
            return true;
        }
        return false;
    }

    /**
     * Method to print out the current state. Prints the puzzle board.
     */
    @Override
    public void printState() {
        System.out.println(curBoard[0] + " | " + curBoard[1] + " | "
                + curBoard[2]);
        System.out.println("---------");
        System.out.println(curBoard[3] + " | " + curBoard[4] + " | "
                + curBoard[5]);
        System.out.println("---------");
        System.out.println(curBoard[6] + " | " + curBoard[7] + " | "
                + curBoard[8]);

    }

    /**
     * Overloaded equals method to compare two states.
     *
     * @return true or false, depending on whether the states are equal
     */
    @Override
    public boolean equals(State s) {
        if (Arrays.equals(curBoard, ((EightPuzzleState) s).getCurBoard())) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Getter to return the current board array
     *
     * @return the curState
     */
    public int[] getCurBoard() {
        return curBoard;
    }

}
