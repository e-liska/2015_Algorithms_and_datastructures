package e1;

public class E1App {
	public static void main(String[] args) {

		BinaryTree<Integer> tree = new BinaryTree<Integer>();
		System.out.println("Empty: " + tree.isEmpty());
		tree.insert(3);

		tree.insert(20);
		tree.insert(4);

		tree.insert(311);
		tree.insert(550);
		tree.insert(100);
		tree.insert(234);

		tree.printTree();

		tree.remove(4);
		tree.printTree();
	}
}
