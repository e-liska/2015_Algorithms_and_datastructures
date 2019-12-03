package e2;

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
	 * @param rootData
	 *            the data for the root
	 */
	public BinaryTree(E rootData) {
		root = new Node();
		root.data = rootData;
		root.left = null;
		root.right = null;
	}

	/**
	 * Constructs a binary tree.
	 *
	 * @param rootData
	 *            the data for the root
	 * @param left
	 *            the left subtree
	 * @param right
	 *            the right subtree
	 */
	public BinaryTree(E rootData, BinaryTree<E> left, BinaryTree<E> right) {
		root = new Node();
		root.data = rootData;
		if (left != null) {
			root.left = left.root;
		}
		if (right != null) {
			root.right = right.root;
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
	 * Gets the data at the root of this tree.
	 *
	 * @return the root data
	 */
	public E data() {
		return root.data;
	}

	/**
	 * Gets the left subtree of this tree.
	 *
	 * @return the left child of the root
	 */
	public BinaryTree<E> left() {
		BinaryTree<E> result = new BinaryTree<E>();
		result.root = root.left;
		return result;
	}

	/**
	 * Gets the right subtree of this tree.
	 *
	 * @return the right child of the root
	 */
	public BinaryTree<E> right() {
		BinaryTree<E> result = new BinaryTree<E>();
		result.root = root.right;
		return result;

	}

	/**
	 *
	 * @param rootData
	 *            the new data for the root
	 *
	 * @return the data previous in the root
	 */
	public E replace(E rootData) {
		E temp = root.data;
		root.data = rootData;
		return temp;
	}

	/**
	 *
	 * @param n
	 * @return true in n has no children
	 */
	private boolean isLeaf(Node n) {
		return (n.left == null && n.right == null);
	}

	/**
	 *
	 * @param n
	 * @return true in n has at least one child
	 */
	private boolean isInternal(Node n) {
		return (n.left != null || n.right != null);
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
			return this.size(n.left) + this.size(n.right) + 1;
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
			return Math.max(this.height(n.left), this.height(n.right)) + 1;
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
	// result.append(this.getElementsInorderRec(n.left) + " ");
	// result.append(n.data + " ");
	// result.append(this.getElementsInorderRec(n.right) + " ");
	// return result.toString();
	// }

	public String printInorder() {
		StringBuffer result = new StringBuffer();
		this.getElementsInorderRec(root, result);
		return result.toString();
	}

	private void getElementsInorderRec(Node n, StringBuffer s) {
		if (n != null) {
			this.getElementsInorderRec(n.left, s);
			s.append(n.data + " ");
			this.getElementsInorderRec(n.right, s);
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
			s.append(n.data + " ");
			this.printElementsPreorderRec(n.left, s);
			this.printElementsPreorderRec(n.right, s);
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
			this.printPostOrderRec(n.left, s);
			this.printPostOrderRec(n.right, s);
			s.append(n.data + " ");
		}
	}

	public int sumAll() {
		return this.sumRec(root);
	}

	private int sumRec(Node n) {
		int result = 0;
		if (n != null) {
			result = (int) n.data;
			result += this.sumRec(n.left);
			result += this.sumRec(n.right);
		}
		return result;
	}

	private class Node {
		public E data;
		public Node left;
		public Node right;
	}

	public String printEulerTool() {
		StringBuffer result = new StringBuffer();
		this.eulerToolRec(root, result);
		return result.toString();
	}

	private void eulerToolRec(Node n, StringBuffer s) {
		if (n != null) {
			s.append("(");
			this.eulerToolRec(n.left, s);
			s.append(n.data + " ");
			this.eulerToolRec(n.right, s);
			s.append(")");
		}
	}

}
