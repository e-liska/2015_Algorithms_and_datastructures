package e2;

public class App {
	public static void main(String[] args) {
		BinaryTree<Integer> tr = new BinaryTree<Integer>(30,
				new BinaryTree<Integer>(25), null);
		BinaryTree<Integer> tl = new BinaryTree<Integer>(11, null,
				new BinaryTree<Integer>(15));
		tl = new BinaryTree<Integer>(22, tl, tr);

		tr = new BinaryTree<Integer>(90, new BinaryTree<Integer>(88), null);
		tr = new BinaryTree<Integer>(77, null, tr);
		BinaryTree<Integer> tree = new BinaryTree<Integer>(45, tl, tr);

		System.out.println("Size: " + tree.size());
		System.out.println("Height: " + tree.height());
		System.out.println("Inorder: " + tree.printInorder());

		System.out.println("Preorder: " + tree.printPreorder());

		System.out.println("Postorder: " + tree.printPostorder());

		System.out.println("Sum all: " + tree.sumAll());

		System.out.println("\nEuler tool: " + tree.printEulerTool());

	}
}
