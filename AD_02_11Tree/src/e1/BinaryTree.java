package e1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import tree.EmptyTreeException;
import tree.InvalidPositionException;

//import tree.Position;

public class BinaryTree<E> {
	private E[] tree;
	private int size;

	public BinaryTree() {
		tree = (E[]) new Integer[30];
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Iterator<E> iterator() {
		return Arrays.asList(tree).iterator();
	}

	public List<E> elements() {
		return Arrays.asList(tree);
	}

	public E replace(E o, E n) {
		for (int i = 0; i < tree.length; i++) {
			if (tree[i].equals(o)) {
				E temp = o;
				tree[i] = n;
				return temp;
			}
		}
		return null;
	}

	public E root() throws EmptyTreeException {
		if (size != 0)
			return tree[0];
		throw new EmptyTreeException("Empty tree");
	}

	public E parent(E v) {
		for (int i = 0; i < tree.length; i++) {
			if (tree[i].equals(v)) {
				if (i == 0)
					return null;
				return tree[(i - 1) / 2];
			}
		}
		return null;
	}

	public List<E> children(E v) {
		for (int i = 0; i < tree.length; i++) {
			if (tree[i].equals(v)) {
				List<E> children = new ArrayList<E>();
				try {
					children.add(tree[2 * i + 1]);
					children.add(tree[2 * i + 2]);
				} catch (ArrayIndexOutOfBoundsException e) {
					// TODO: handle exception
				}
				return children;
			}

		}
		return null;
	}

	public boolean isInternal(E v) throws InvalidPositionException {
		for (int j = 0; j < tree.length; j++) {
			if (tree[j].equals(v)) {
				try {
					return (tree[2 * j + 1] == null && tree[2 * j + 2] == null);
				} catch (ArrayIndexOutOfBoundsException e) {
					return false;
				}
			}
		}
		throw new InvalidPositionException();
	}

	public boolean isExternal(E v) throws InvalidPositionException {
		try {
			return !this.isInternal(v);
		} catch (Exception e) {
			throw new InvalidPositionException();
		}
	}

	public boolean isRoot(E v) {
		try {
			return tree[0].equals(v);
		} catch (Exception e) {
			return false;
		}
	}

	public void insert(E e) {
		List<Integer> thisLevel = new ArrayList<Integer>();
		thisLevel.add(0);
		while (!thisLevel.isEmpty()) {
			List<Integer> nextLevel = new ArrayList<Integer>();
			for (Integer i : thisLevel) {
				if (i > tree.length) {
					this.resizeTree();
				}
				if (tree[i] == null) {
					tree[i] = e;
					nextLevel.clear();
					break;
				}
				nextLevel.add(i * 2 + 1);
				nextLevel.add(i * 2 + 2);
			}
			thisLevel = nextLevel;
		}
		size++;
	}

	private void resizeTree() {
		E[] temp = (E[]) new Integer[tree.length * 2];
		for (int i = 0; i < tree.length; i++) {
			temp[i] = tree[i];
		}
		tree = temp;
	}

	public void printTree() {
		List<Integer> thisLevel = new ArrayList<Integer>();
		if (size > 0) {
			thisLevel.add(0);
		}
		while (!thisLevel.isEmpty()) {
			List<Integer> nextLevel = new ArrayList<Integer>();
			for (Integer i : thisLevel) {

				if (tree[i] != null) {
					System.out.print(tree[i] + " ");
				}
				if (i * 2 + 1 < tree.length) {
					nextLevel.add(i * 2 + 1);
				}
				if (i * 2 + 2 < tree.length) {
					nextLevel.add(i * 2 + 2);
				}
			}
			System.out.println();
			thisLevel = nextLevel;
		}

	}

	public void remove(E e) {
		for (int i = 0; i < tree.length; i++) {
			if (tree[i] != null && tree[i].equals(e)) {
				List<Integer> thisLevel = new ArrayList<Integer>();
				thisLevel.add(i);
				while (!thisLevel.isEmpty()) {
					List<Integer> nextLevel = new ArrayList<Integer>();
					for (Integer current : thisLevel) {

						if (tree[current] != null) {
							tree[current] = null;
							size--;
							if (current * 2 + 1 < tree.length) {
								nextLevel.add(current * 2 + 1);
							}
							if (current * 2 + 2 < tree.length) {
								nextLevel.add(current * 2 + 2);
							}
						}

					}
					thisLevel = nextLevel;
				}
			}
		}
	}
}
