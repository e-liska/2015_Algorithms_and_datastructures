package adaptableProrityQueue;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AdaptablePriorityQueue<K extends Comparable<K>, V> {
	private Entry[] heap;
	private int last;

	public AdaptablePriorityQueue() {
		heap = (Entry[]) Array.newInstance(Entry.class, 50);
		last = -1;
	}

	public void insert(V value, K key) {
		if (last == heap.length) {
			this.resize();
		}
		last++;
		Entry e = new Entry(key, value, last);
		heap[last] = e;
		this.upheap(last);
	}

	private void resize() {
		Entry[] biggerA = (Entry[]) Array.newInstance(Entry.class,
				heap.length * 2);
		for (int i = 0; i < heap.length; i++) {
			biggerA[i] = heap[i];
		}
		heap = biggerA;
	}

	private void upheap(int i) {
		Entry latest = heap[i];
		while (i > 0) {
			int parentPosition = (i - 1) / 2;
			Entry parent = heap[parentPosition];
			if (latest.key.compareTo(parent.key) < 0) {
				this.swapEntries(parentPosition, i);
			}

			i = parentPosition;
		}
	}

	public V removeMin() {
		if (this.isEmpty())
			return null;
		V temp = heap[0].value;
		heap[0] = heap[last];
		heap[last] = null;
		heap[0].position = 0;
		last--;
		this.downheap(0);
		return temp;
	}

	private void downheap(int i) {
		int pos = i;
		Entry latest = heap[pos];
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
					this.swapEntries(pos, posChild1);
				}
			} catch (NullPointerException e) {
				if (heap[pos].key.compareTo(heap[posChild1].key) > 0) {
					this.swapEntries(pos, posChild1);
				}
			}

			pos = posChild1;
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

	public Entry remove(Entry e) {
		this.swapEntries(e.position, last);
		heap[last] = null;
		last--;
		return e;
	}

	public K replaceKey(Entry e, K k) {
		K temp = e.key;
		e.key = k;
		this.upheap(e.position);
		this.downheap(e.position);
		return temp;
	}

	public V replaceValue(Entry e, V v) {
		V temp = e.value;
		e.value = v;
		return temp;
	}

	private void swapEntries(int pos1, int pos2) {
		Entry temp = heap[pos1];
		heap[pos1] = heap[pos2];
		heap[pos2] = temp;
		heap[pos1].position = pos1;
		heap[pos2].position = pos2;
	}

	private class Entry {
		public K key;
		public V value;
		public int position;

		public Entry(K k, V v) {
			key = k;
			value = v;
		}

		public Entry(K k, V v, int p) {
			key = k;
			value = v;
			position = p;
		}

		@Override
		public String toString() {
			return key.toString() + ", " + value.toString();
		}
	}

}
