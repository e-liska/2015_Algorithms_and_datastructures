package tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//begin#fragment LinkedBinaryTree
/**
 * An implementation of the BinaryTree interface by means of a linked structure.
 * //end#fragment LinkedBinaryTree This class serves as a superclass for the
 * BinarySearchTree implementation. This design decision was made to emphasize
 * the conceptual relationship that a BinarySearchTree is a specialized
 * LinkedBinaryTree. An unwanted side-effect of this is that the {@link #size()
 * size} method returns the number of total nodes whereas the
 * {@link BinarySearchTree#size() size} method in the {@link BinarySearchTree
 * BinarySearchTree} class returns the number of internal nodes only. For this
 * reason, the the {@link #size size} variable instead of the {@link #size()
 * size} method is used within this class.
 *
 * @author Luca Vismara, Roberto Tamassia, Michael Goodrich, Eric Zamore
 * @see BinaryTree //begin#fragment LinkedBinaryTree
 */
public class LinkedBinaryTree<E> implements BinaryTree<E> {
	protected BTPosition<E> root; // reference to the root
	protected int size; // number of nodes

	/** Creates an empty binary tree. */
	public LinkedBinaryTree() {
		root = null; // start with an empty tree
		size = 0;
	}

	/** Returns the number of nodes in the tree. */
	@Override
	public int size() {
		return size;
	}

	// end#fragment LinkedBinaryTree
	/** Returns whether the tree is empty. */
	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	// begin#fragment LinkedBinaryTree
	/** Returns whether a node is internal. */
	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		this.checkPosition(v); // auxiliary method
		return (this.hasLeft(v) || this.hasRight(v));
	}

	// end#fragment LinkedBinaryTree
	/** Returns whether a node is external. */
	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		return !this.isInternal(v);
	}

	// begin#fragment LinkedBinaryTree
	/** Returns whether a node is the root. */
	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		this.checkPosition(v);
		return (v == this.root());
	}

	/** Returns whether a node has a left child. */
	@Override
	public boolean hasLeft(Position<E> v) throws InvalidPositionException {
		BTPosition<E> vv = this.checkPosition(v);
		return (vv.getLeft() != null);
	}

	// end#fragment LinkedBinaryTree
	/** Returns whether a node has a right child. */
	@Override
	public boolean hasRight(Position<E> v) throws InvalidPositionException {
		BTPosition<E> vv = this.checkPosition(v);
		return (vv.getRight() != null);
	}

	// begin#fragment LinkedBinaryTree
	/** Returns the root of the tree. */
	@Override
	public Position<E> root() throws EmptyTreeException {
		if (root == null)
			throw new EmptyTreeException("The tree is empty");
		return root;
	}

	/** Returns the left child of a node. */
	@Override
	public Position<E> left(Position<E> v) throws InvalidPositionException,
	BoundaryViolationException {
		BTPosition<E> vv = this.checkPosition(v);
		Position<E> leftPos = vv.getLeft();
		if (leftPos == null)
			throw new BoundaryViolationException("No left child");
		return leftPos;
	}

	// end#fragment LinkedBinaryTree
	/** Returns the right child of a node. */
	@Override
	public Position<E> right(Position<E> v) throws InvalidPositionException,
	BoundaryViolationException {
		BTPosition<E> vv = this.checkPosition(v);
		Position<E> rightPos = vv.getRight();
		if (rightPos == null)
			throw new BoundaryViolationException("No right child");
		return rightPos;
	}

	// begin#fragment LinkedBinaryTree2
	/** Returns the parent of a node. */
	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException,
	BoundaryViolationException {
		BTPosition<E> vv = this.checkPosition(v);
		Position<E> parentPos = vv.getParent();
		if (parentPos == null)
			throw new BoundaryViolationException("No parent");
		return parentPos;
	}

	/** Returns an iterable collection of the children of a node. */
	@Override
	public Iterable<Position<E>> children(Position<E> v)
			throws InvalidPositionException {
		PositionList<Position<E>> children = new NodePositionList<Position<E>>();
		if (this.hasLeft(v)) {
			children.addLast(this.left(v));
		}
		if (this.hasRight(v)) {
			children.addLast(this.right(v));
		}
		return children;
	}

	/** Returns an iterable collection of the tree nodes. */
	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> positions = new NodePositionList<Position<E>>();
		if (size != 0) {
			this.preorderPositions(this.root(), positions); // assign positions
			// in preorder
		}
		return positions;
	}

	/** Returns an iterator of the elements stored at the nodes */
	@Override
	public Iterator<E> iterator() {
		Iterable<Position<E>> positions = this.positions();
		PositionList<E> elements = new NodePositionList<E>();
		for (Position<E> pos : positions) {
			elements.addLast(pos.element());
		}
		return elements.iterator(); // An iterator of elements
	}

	/** Replaces the element at a node. */
	@Override
	public E replace(Position<E> v, E o) throws InvalidPositionException {
		BTPosition<E> vv = this.checkPosition(v);
		E temp = v.element();
		vv.setElement(o);
		return temp;
	}

	// end#fragment LinkedBinaryTree2
	// begin#fragment LinkedBinaryTree3
	// Additional accessor method
	/** Return the sibling of a node */
	public Position<E> sibling(Position<E> v) throws InvalidPositionException,
	BoundaryViolationException {
		BTPosition<E> vv = this.checkPosition(v);
		BTPosition<E> parentPos = vv.getParent();
		if (parentPos != null) {
			BTPosition<E> sibPos;
			BTPosition<E> leftPos = parentPos.getLeft();
			if (leftPos == vv) {
				sibPos = parentPos.getRight();
			} else {
				sibPos = parentPos.getLeft();
			}
			if (sibPos != null)
				return sibPos;
		}
		throw new BoundaryViolationException("No sibling");
	}

	// Additional update methods
	/** Adds a root node to an empty tree */
	public Position<E> addRoot(E e) throws NonEmptyTreeException {
		if (!this.isEmpty())
			throw new NonEmptyTreeException("Tree already has a root");
		size = 1;
		root = this.createNode(e, null, null, null);
		return root;
	}

	/** Inserts a left child at a given node. */
	public Position<E> insertLeft(Position<E> v, E e)
			throws InvalidPositionException {
		BTPosition<E> vv = this.checkPosition(v);
		Position<E> leftPos = vv.getLeft();
		if (leftPos != null)
			throw new InvalidPositionException("Node already has a left child");
		BTPosition<E> ww = this.createNode(e, vv, null, null);
		vv.setLeft(ww);
		size++;
		return ww;
	}

	// end#fragment LinkedBinaryTree3
	/** Inserts a right child at a given node. */
	public Position<E> insertRight(Position<E> v, E e)
			throws InvalidPositionException {
		BTPosition<E> vv = this.checkPosition(v);
		Position<E> rightPos = vv.getRight();
		if (rightPos != null)
			throw new InvalidPositionException("Node already has a right child");
		BTPosition<E> w = this.createNode(e, vv, null, null);
		vv.setRight(w);
		size++;
		return w;
	}

	// begin#fragment LinkedBinaryTree4
	/** Removes a node with zero or one child. */
	public E remove(Position<E> v) throws InvalidPositionException {
		BTPosition<E> vv = this.checkPosition(v);
		BTPosition<E> leftPos = vv.getLeft();
		BTPosition<E> rightPos = vv.getRight();
		if (leftPos != null && rightPos != null)
			throw new InvalidPositionException(
					"Cannot remove node with two children");
		BTPosition<E> ww; // the only child of v, if any
		if (leftPos != null) {
			ww = leftPos;
		} else if (rightPos != null) {
			ww = rightPos;
		} else {
			ww = null;
		}
		if (vv == root) { // v is the root
			if (ww != null) {
				ww.setParent(null);
			}
			root = ww;
		} else { // v is not the root
			BTPosition<E> uu = vv.getParent();
			if (vv == uu.getLeft()) {
				uu.setLeft(ww);
			} else {
				uu.setRight(ww);
			}
			if (ww != null) {
				ww.setParent(uu);
			}
		}
		size--;
		return v.element();
	}

	// end#fragment LinkedBinaryTree4
	// begin#fragment LinkedBinaryTree5
	/** Attaches two trees to be subtrees of an external node. */
	public void attach(Position<E> v, BinaryTree<E> T1, BinaryTree<E> T2)
			throws InvalidPositionException {
		BTPosition<E> vv = this.checkPosition(v);
		if (this.isInternal(v))
			throw new InvalidPositionException(
					"Cannot attach from internal node");
		int newSize = size + T1.size() + T2.size();
		if (!T1.isEmpty()) {
			BTPosition<E> r1 = this.checkPosition(T1.root());
			vv.setLeft(r1);
			r1.setParent(vv); // T1 should be invalidated
		}
		if (!T2.isEmpty()) {
			BTPosition<E> r2 = this.checkPosition(T2.root());
			vv.setRight(r2);
			r2.setParent(vv); // T2 should be invalidated
		}
		size = newSize;
	}

	// end#fragment LinkedBinaryTree5
	/** Swap the elements at two nodes */
	public void swapElements(Position<E> v, Position<E> w)
			throws InvalidPositionException {
		BTPosition<E> vv = this.checkPosition(v);
		BTPosition<E> ww = this.checkPosition(w);
		E temp = w.element();
		ww.setElement(v.element());
		vv.setElement(temp);
	}

	/**
	 * Expand an external node into an internal node with two external node
	 * children
	 */
	public void expandExternal(Position<E> v, E l, E r)
			throws InvalidPositionException {
		if (!this.isExternal(v))
			throw new InvalidPositionException("Node is not external");
		this.insertLeft(v, l);
		this.insertRight(v, r);
	}

	/**
	 * Remove an external node v and replace its parent with v's sibling
	 */
	public void removeAboveExternal(Position<E> v)
			throws InvalidPositionException {
		if (!this.isExternal(v))
			throw new InvalidPositionException("Node is not external");
		if (this.isRoot(v)) {
			this.remove(v);
		} else {
			Position<E> u = this.parent(v);
			this.remove(v);
			this.remove(u);
		}
	}

	public void pruneSubtree(Position<E> p) throws InvalidPositionException {
		BTPosition<E> subTreeRoot = this.checkPosition(p);
		BTPosition<E> parent = subTreeRoot.getParent();
		if (subTreeRoot.equals(parent.getLeft())) {
			parent.setLeft(null);
		} else {
			parent.setRight(null);
		}
		subTreeRoot.setParent(null);

		List<Position<E>> list = new ArrayList<Position<E>>();
		this.inorderTraversalPositions(subTreeRoot, list);
		size -= list.size();
	}

	protected void inorderTraversalPositions(Position<E> v,
			List<Position<E>> pos) throws InvalidPositionException {
		if (this.hasLeft(v)) {
			this.inorderTraversalPositions(this.left(v), pos); // recurse on
																// left child
		}
		pos.add(v);
		if (this.hasRight(v)) {
			this.inorderTraversalPositions(this.right(v), pos); // recurse on
																// right child
		}
	}

	// Auxiliary methods
	// begin#fragment LinkedBinaryTree5
	/**
	 * If v is a good binary tree node, cast to BTPosition, else throw exception
	 */
	protected BTPosition<E> checkPosition(Position<E> v)
			throws InvalidPositionException {
		if (v == null || !(v instanceof BTPosition))
			throw new InvalidPositionException("The position is invalid");
		return (BTPosition<E>) v;
	}

	/** Creates a new binary tree node */
	protected BTPosition<E> createNode(E element, BTPosition<E> parent,
			BTPosition<E> left, BTPosition<E> right) {
		return new BTNode<E>(element, parent, left, right);
	}

	/**
	 * Creates a list storing the the nodes in the subtree of a node, ordered
	 * according to the preorder traversal of the subtree.
	 */
	protected void preorderPositions(Position<E> v,
			PositionList<Position<E>> pos) throws InvalidPositionException {
		pos.addLast(v);
		if (this.hasLeft(v)) {
			this.preorderPositions(this.left(v), pos); // recurse on left child
		}
		if (this.hasRight(v)) {
			this.preorderPositions(this.right(v), pos); // recurse on right
			// child
		}
	}

	// end#fragment LinkedBinaryTree5
	/**
	 * Creates a list storing the the nodes in the subtree of a node, ordered
	 * according to the inorder traversal of the subtree.
	 */
	protected void inorderPositions(Position<E> v, PositionList<Position<E>> pos)
			throws InvalidPositionException {
		if (this.hasLeft(v)) {
			this.inorderPositions(this.left(v), pos); // recurse on left child
		}
		pos.addLast(v);
		if (this.hasRight(v)) {
			this.inorderPositions(this.right(v), pos); // recurse on right child
		}
	}

}
