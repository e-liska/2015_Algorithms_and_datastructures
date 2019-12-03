package e3;

import heap.PriorityQueue;

public class Queue<V> {
	private PriorityQueue<Integer, V> pq;
	private int counter;

	public Queue() {
		pq = new PriorityQueue<Integer, V>();
		counter = 0;
	}

	public void insert(V element) {
		pq.insert(element, counter);
		counter++;
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

	public void printQueue() {
		pq.printHeap();
	}
}
