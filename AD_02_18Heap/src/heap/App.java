package heap;

public class App {
	public static void main(String[] args) {
		PriorityQueue<Integer, String> heap = new PriorityQueue<Integer, String>();
		heap.insert("Anna", 7);
		heap.insert("Bob", 10);
		heap.insert("Cecilie", 6);
		heap.insert("David", 11);
		heap.insert("Elena", 8);
		heap.insert("Filip", 2);
		heap.printHeap();

		System.out.println();
		System.out.println(heap.remove());
		heap.printHeap();
		System.out.println(heap.remove());
		heap.printHeap();
	}
}
