package heap;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PriorityQueue<K extends Comparable<K>, V> {
	private Pair[] heap;
	private int last;

	public PriorityQueue() {
		heap = (Pair[]) Array.newInstance(Pair.class, 50);
		last = -1;
	}

	public void insert(V value, K key) {
		if (last == heap.length) {
			this.resize();
		}
		Pair p = new Pair(key, value);
		last++;
		heap[last] = p;
		this.upheap(last);
	}

	private void resize() {
		Pair[] biggerA = (Pair[]) Array
				.newInstance(Pair.class, heap.length * 2);
		for (int i = 0; i < heap.length; i++) {
			biggerA[i] = heap[i];
		}
		heap = biggerA;
	}

	private void upheap(int i) {
		Pair latest = heap[i];
		while (i > 0) {
			int parentPosition = (i - 1) / 2;
			Pair parent = heap[parentPosition];
			if (latest.key.compareTo(parent.key) < 0) {
				Pair temp = heap[parentPosition];
				heap[parentPosition] = heap[i];
				heap[i] = temp;
			}

			i = parentPosition;
		}
	}

	public V remove() {
		if (this.isEmpty())
			return null;
		V temp = heap[0].value;
		heap[0] = heap[last];
		heap[last] = null;
		last--;
		this.downheap();
		return temp;
	}

	// private void downheap() {
	// int pos = 0;
	// Pair latest = heap[pos];
	// while (pos < (last - 1) / 2) {
	// int posChild1 = 2 * pos + 1;
	// int posChild2 = 2 * pos + 2;
	// int smallerChild;
	// if (heap[posChild1].key.compareTo(heap[posChild2].key) < 0) {
	// smallerChild = posChild1;
	// } else {
	// smallerChild = posChild2;
	// }
	// if (heap[pos].key.compareTo(heap[smallerChild].key) > 0) {
	// Pair temp = heap[pos];
	// heap[pos] = heap[smallerChild];
	// heap[smallerChild] = temp;
	// }
	// pos = smallerChild;
	// }
	// }

	// private void downheap() {
	// int pos = 0;
	// Pair latest = heap[pos];
	// while (pos < (last - 1) / 2) {
	// int posChild1 = 2 * pos + 1;
	// int posChild2 = 2 * pos + 2;
	// if (heap[posChild1].key.compareTo(heap[posChild2].key) > 0) {
	// int temp = posChild1;
	// posChild1 = posChild2;
	// posChild2 = temp;
	// }
	// if (heap[pos].key.compareTo(heap[posChild1].key) > 0) {
	// if (heap[pos].key.compareTo(heap[posChild2].key) < 0) {
	// posChild1 = posChild2;
	// }
	// Pair temp = heap[pos];
	// heap[pos] = heap[posChild1];
	// heap[posChild1] = temp;
	// }
	// pos = posChild1;
	// }
	// }

	private void downheap() {
		int pos = 0;
		Pair latest = heap[pos];
		while (pos <= (last - 1) / 2 && last > 0) {

			int posChild1 = 2 * pos + 1;
			try {
				int posChild2 = 2 * pos + 2;
				if (heap[posChild1].key.compareTo(heap[posChild2].key) > 0) {
					int temp = posChild1;
					posChild1 = posChild2;
					posChild2 = temp;
				}
				if (heap[pos].key.compareTo(heap[posChild1].key) > 0) {

					Pair temp = heap[pos];
					heap[pos] = heap[posChild1];
					heap[posChild1] = temp;
				}
			} catch (NullPointerException e) {
				if (heap[pos].key.compareTo(heap[posChild1].key) > 0) {
					Pair temp = heap[pos];
					heap[pos] = heap[posChild1];
					heap[posChild1] = temp;
				}
			}

			pos = posChild1;
		}
	}

	private class Pair {
		public K key;
		public V value;

		public Pair(K k, V v) {
			key = k;
			value = v;
		}

		@Override
		public String toString() {
			return key.toString() + ", " + value.toString();
		}
	}

	public int size() {
		return last + 1;
	}

	public boolean isEmpty() {
		return (last == -1);
	}

	public void printHeap() {
		List<Integer> thisLevel = new ArrayList<Integer>();
		if (last > -1) {
			thisLevel.add(0);
		}
		while (!thisLevel.isEmpty()) {
			List<Integer> nextLevel = new ArrayList<Integer>();
			for (Integer i : thisLevel) {
				System.out.print(heap[i] + "  ");

				if (i * 2 + 1 <= last) {
					nextLevel.add(i * 2 + 1);
				}
				if (i * 2 + 2 <= last) {
					nextLevel.add(i * 2 + 2);
				}
			}
			System.out.println();
			thisLevel = nextLevel;
		}

	}

}
