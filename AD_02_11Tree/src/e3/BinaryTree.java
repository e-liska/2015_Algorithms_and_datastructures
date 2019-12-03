package e3;

/**
 * A binary tree in which each node has two children.
 */
public class BinaryTree<E> {
	private Node root;

	/**
	 * Constructs an empty tree.
	 */
	public BinaryTree() {
		root = null;
	}

	/**
	 * Constructs a tree with one node and no children.
	 *
	 * @param rootpiecesLeft
	 *            the piecesLeft for the root
	 */
	public BinaryTree(int rootpiecesLeft) {
		root = new Node();
		root.piecesLeft = rootpiecesLeft;
		root.takeOne = null;
		root.takeTwo = null;
	}

	/**
	 * Constructs a binary tree.
	 *
	 * @param rootpiecesLeft
	 *            the piecesLeft for the root
	 * @param takeOne
	 *            the takeOne subtree
	 * @param takeTwo
	 *            the takeTwo subtree
	 */
	public BinaryTree(int rootpiecesLeft, BinaryTree<E> takeOne,
			BinaryTree<E> takeTwo) {
		root = new Node();
		root.piecesLeft = rootpiecesLeft;
		if (takeOne != null) {
			root.takeOne = takeOne.root;
		}
		if (takeTwo != null) {
			root.takeTwo = takeTwo.root;
		}
	}

	/**
	 * Checks whether this tree is empty.
	 *
	 * @return true if this tree is empty
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Gets the piecesLeft at the root of this tree.
	 *
	 * @return the root piecesLeft
	 */
	public int piecesLeft() {
		return root.piecesLeft;
	}

	/**
	 * Gets the takeOne subtree of this tree.
	 *
	 * @return the takeOne child of the root
	 */
	public BinaryTree<E> takeOne() {
		BinaryTree<E> result = new BinaryTree<E>();
		result.root = root.takeOne;
		return result;
	}

	/**
	 * Gets the takeTwo subtree of this tree.
	 *
	 * @return the takeTwo child of the root
	 */
	public BinaryTree<E> takeTwo() {
		BinaryTree<E> result = new BinaryTree<E>();
		result.root = root.takeTwo;
		return result;

	}

	/**
	 *
	 * @param rootpiecesLeft
	 *            the new piecesLeft for the root
	 *
	 * @return the piecesLeft previous in the root
	 */
	public int replace(int rootpiecesLeft) {
		int temp = root.piecesLeft;
		root.piecesLeft = rootpiecesLeft;
		return temp;
	}

	/**
	 *
	 * @param n
	 * @return true in n has no children
	 */
	private boolean isLeaf(Node n) {
		return (n.takeOne == null && n.takeTwo == null);
	}

	/**
	 *
	 * @param n
	 * @return true in n has at least one child
	 */
	private boolean isInternal(Node n) {
		return (n.takeOne != null || n.takeTwo != null);
	}

	/**
	 *
	 * @return the number of nodes in the tree
	 */
	public int size() {
		return this.size(root);
	}

	private int size(Node n) {
		if (n == null)
			return 0;
		else
			return this.size(n.takeOne) + this.size(n.takeTwo) + 1;
	}

	/**
	 *
	 * @return the number of levels of the tree
	 */
	public int height() {
		return this.height(root);
	}

	private int height(Node n) {
		if (n == null)
			return 0;
		else
			return Math.max(this.height(n.takeOne), this.height(n.takeTwo)) + 1;
	}

	// inorder
	// public String printInorder() {
	// return this.getElementsInorderRec(root);
	// }
	//
	// private String getElementsInorderRec(Node n) {
	// if (n == null)
	// return "";
	// StringBuffer result = new StringBuffer();
	// result.append(this.getElementsInorderRec(n.takeOne) + " ");
	// result.append(n.piecesLeft + " ");
	// result.append(this.getElementsInorderRec(n.takeTwo) + " ");
	// return result.toString();
	// }

	public String printInorder() {
		StringBuffer result = new StringBuffer();
		this.getElementsInorderRec(root, result);
		return result.toString();
	}

	private void getElementsInorderRec(Node n, StringBuffer s) {
		if (n != null) {
			this.getElementsInorderRec(n.takeOne, s);
			s.append(n.piecesLeft + " ");
			this.getElementsInorderRec(n.takeTwo, s);
		}
	}

	// preorder

	public String printPreorder() {
		StringBuffer result = new StringBuffer();
		this.printElementsPreorderRec(root, result);
		return result.toString();
	}

	private void printElementsPreorderRec(Node n, StringBuffer s) {
		if (n != null) {
			s.append(n.piecesLeft + " ");
			this.printElementsPreorderRec(n.takeOne, s);
			this.printElementsPreorderRec(n.takeTwo, s);
		}
	}

	// postorder

	public String printPostorder() {
		StringBuffer result = new StringBuffer();
		this.printPostOrderRec(root, result);
		return result.toString();
	}

	private void printPostOrderRec(Node n, StringBuffer s) {
		if (n != null) {
			this.printPostOrderRec(n.takeOne, s);
			this.printPostOrderRec(n.takeTwo, s);
			s.append(n.piecesLeft + " ");
		}
	}

	public int sumAll() {
		return this.sumRec(root);
	}

	private int sumRec(Node n) {
		int result = 0;
		if (n != null) {
			result = n.piecesLeft;
			result += this.sumRec(n.takeOne);
			result += this.sumRec(n.takeTwo);
		}
		return result;
	}

	private class Node {
		public int piecesLeft;
		public String player;
		public Node takeOne;
		public Node takeTwo;
	}

	public String printEulerTool() {
		StringBuffer result = new StringBuffer();
		this.eulerToolRec(root, result);
		return result.toString();
	}

	private void eulerToolRec(Node n, StringBuffer s) {
		if (n != null) {
			s.append("(");
			this.eulerToolRec(n.takeOne, s);
			s.append(n.piecesLeft + " ");
			this.eulerToolRec(n.takeTwo, s);
			s.append(")");
		}
	}

}
