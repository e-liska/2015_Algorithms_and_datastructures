package e3_phone_list;

public class DigitTree {
	private Node root;

	public DigitTree() {
		root = new Node(-1);
	}

	/**
	 * returns true is phonelist is still consistent
	 */
	public boolean add(char[] input) {
		Node currentNode = root;
		for (int i = 0; i < input.length; i++) {
			Node next = currentNode.children[input[i] - 48];
			if (next == null) {
				currentNode.children[input[i] - 48] = new Node(input[i] - 48);
				next = currentNode.children[input[i] - 48];
				if (i == input.length - 1) {
					next.isEnd = true;
				}
			} else {
				if (next.isEnd)
					return false;
				if (i == input.length - 1) {
					next.isEnd = true;
					return false;
				}
			}
			currentNode = next;
		}
		return true;
	}

	private class Node {
		private int value;
		private boolean isEnd;
		private Node[] children;

		private Node(int value) {
			this.value = value;
			children = new Node[10];
		}

		private void addNode(Node newNode) {
			this.children[newNode.value] = newNode;
		}
	}
}
