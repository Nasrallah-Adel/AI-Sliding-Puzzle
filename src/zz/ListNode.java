package zz;

/**
 * The Class ListNode.
 * @author Manoj Shrestha
 */
public class ListNode {

	/** The node. */
	private Node node;

	/** The next node. */
	public ListNode nextNode; // if private, eg lastNode.nextNode garna
								// paaidaina

	/** The previous node. */
	public ListNode previousNode;

	/**
	 * Instantiates a new list node.
	 */
	public ListNode() {
		node = new Node();
	}

	/**
	 * Instantiates a new list node.
	 * 
	 * @param node
	 *            the node
	 */
	public ListNode(Node node) {
		this(node, null, null);
	}

	/**
	 * Instantiates a new list node.
	 * 
	 * @param node
	 *            the node
	 * @param previous
	 *            the previous
	 * @param next
	 *            the next
	 */
	public ListNode(Node node, ListNode previous, ListNode next) {
		this.node = node;
		// this.node = new Node();
		// this.node.copyAll(node);
		previousNode = previous;
		nextNode = next;
	}

	/**
	 * Gets the node.
	 * 
	 * @return the node
	 */
	public Node getNode() {
		return node;
	}

	/**
	 * Gets the next.
	 * 
	 * @return the next
	 */
	public ListNode getNext() {
		return nextNode;
	}

	/**
	 * Gets the previous.
	 * 
	 * @return the previous
	 */
	public ListNode getPrevious() {
		return previousNode;
	}
}