package e2;

import heap.PriorityQueue;

public class Stack<V> {
	private PriorityQueue<Integer, V> pq;
	private int counter;

	public Stack() {
		pq = new PriorityQueue<Integer, V>();
		counter = 0;
	}

	public void insert(V element) {
		pq.insert(element, counter);
		counter--;
	}

	public V remove() {
		return pq.remove();
	}

	public int size() {
		return pq.size();
	}

	public boolean isEmpty() {
		return pq.isEmpty();
	}

	public void printStack() {
		pq.printHeap();
	}
}
