package zz;

/**
 * The Class LinkList.
 * @author Manoj Shrestha
 */
public class LinkList {

	/** The first node. */
	private ListNode firstNode;

	/** The last node. */
	private ListNode lastNode;

	// default constructor creates an empty list
	/**
	 * Instantiates a new link list.
	 */
	public LinkList() {
		firstNode = lastNode = null;
	}

	// insert node as front of list
	/**
	 * Insert at back.
	 * 
	 * @param node
	 *            the node
	 */
	public void insertAtBack(Node node) {
		if (isEmpty()) // firstNode and lastNode refer to same node
			firstNode = lastNode = new ListNode(node);
		else // lastNode's nextNode refers to new node
		{
			ListNode newNode = new ListNode(node);
			lastNode.nextNode = newNode;
			newNode.previousNode = lastNode;
			lastNode = newNode;
		}
	}

	/**
	 * Insert at front.
	 * 
	 * @param node
	 *            the node
	 */
	public void insertAtFront(Node node) {
		if (isEmpty())
			firstNode = lastNode = new ListNode(node);
		else {
			ListNode newNode = new ListNode(node);
			newNode.nextNode = firstNode;
			firstNode.previousNode = newNode;
			firstNode = newNode;
		}
	}

	/**
	 * Gets the smallest node.
	 * 
	 * @return the smallest node
	 */
	public Node getSmallestNode() {

		Node minimumNode = firstNode.getNode();
		ListNode current = firstNode.getNext();

		while (current != null) {
			if (current.getNode().getF() < minimumNode.getF())
				minimumNode = current.getNode();

			current = current.getNext();
		}

		return minimumNode;
	}

	/**
	 * Removes the node.
	 * 
	 * @param node
	 *            the node
	 */
	public void removeNode(Node node) {
		if (isEmpty()) //
			return;
		else if (isSameState(node, firstNode.getNode())
				&& firstNode == lastNode) // target node is the only node in the
											// list
		{
			firstNode = lastNode = null;
			return;
		}

		else if (isSameState(node, firstNode.getNode())) // the target node is
															// first node
		{
			firstNode = firstNode.getNext();
			firstNode.previousNode = null;
			return;
		} else if (isSameState(node, lastNode.getNode())) {
			lastNode = lastNode.getPrevious();
			lastNode.nextNode = null;

		} else {
			ListNode current = firstNode;

			while (current.getNext() != null) {
				if (isSameState(current.getNext().getNode(), node)) {
					// if (current.nextNode.nextNode != null)
					current.nextNode.nextNode.previousNode = current;

					current.nextNode = current.getNext().getNext();
					return; // break
				} else
					current = current.nextNode;
			}
		}
	}

	/**
	 * Checks if is empty.
	 * 
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return firstNode == null; // returns true if list is empty
	}

	/**
	 * Already exists.
	 * 
	 * @param node
	 *            the node
	 * @return true, if successful
	 */
	public boolean alreadyExists(Node node) {
		if (!isEmpty()) {
			ListNode current = firstNode;

			while (current != null) {
				if (isSameState(current.getNode(), node))
					return true;
				else
					current = current.getNext();
			}
			return false; // IMPORTANT! return false here
		}
		return false; // SERIOUS MISTAKE!
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
	public boolean isSameState(Node A, Node B) {
		int[][] stateA = A.getState();
		int[][] stateB = B.getState();

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (stateA[i][j] != stateB[i][j])
					return false;
		return true;
	}

	/**
	 * Gets the last node.
	 * 
	 * @return the last node
	 */
	public ListNode getLastNode() {
		return lastNode;
	}

	/**
	 * Gets the first node.
	 * 
	 * @return the first node
	 */
	public ListNode getFirstNode() {
		return firstNode;
	}

	/**
	 * Sets the empty list.
	 */
	public void setEmptyList() {
		firstNode = lastNode = null;
	}

}
