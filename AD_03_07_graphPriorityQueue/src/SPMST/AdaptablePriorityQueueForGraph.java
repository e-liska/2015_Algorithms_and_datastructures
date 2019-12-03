package SPMST;

import java.util.ArrayList;

public class AdaptablePriorityQueueForGraph<E1> {
	private ArrayList<Vertex<E1>> heap;
	private int size;

	public AdaptablePriorityQueueForGraph() {
		super();
		this.heap = new ArrayList<Vertex<E1>>();
		heap.add(null);
		this.size = 0;
	}

	public void insert(Vertex<E1> v) {
		size++;
		heap.add(size, v);
		v.setPosition(size);
		this.upheap(size);
	}

	private void upheap(int current) {
		int parent = current / 2;
		if (parent > 0
				&& heap.get(parent).getDistance() > heap.get(current)
				.getDistance()) {
			this.swap(current, parent);
			this.upheap(parent);
		}
	}

	private void swap(int a, int b) {
		Vertex<E1> va = heap.get(a);
		Vertex<E1> vb = heap.get(b);
		heap.set(a, vb);
		heap.set(b, va);
		va.setPosition(b);
		vb.setPosition(a);
	}

	public Vertex<E1> removeMin() {
		Vertex<E1> v = heap.get(1);
		v.setPosition(-1);
		Vertex<E1> v2 = heap.get(size);
		heap.set(1, v2);
		v2.setPosition(1);
		size--;
		this.downheap(1);
		return v;
	}

	private void downheap(int current) {
		int left = current * 2;
		int right = left + 1;
		int min = -1;
		boolean goLeft = true;

		if (left <= size) {
			min = heap.get(left).getDistance();
			if (right <= size) {
				int rightDist = heap.get(right).getDistance();
				if (rightDist < min) {

					min = rightDist;
					goLeft = false;
				}
			}
		}
		if (min != -1 && min < heap.get(current).getDistance()) {
			if (goLeft) {
				this.swap(current, left);
				this.downheap(left);
			} else {
				this.swap(current, right);
				this.downheap(right);
			}
		}
	}

	public Vertex<E1> min() {
		return heap.get(1);
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Vertex<E1> remove(Vertex<E1> v) {
		int pos = v.getPosition();
		v.setPosition(-1);
		Vertex<E1> v2 = heap.get(size);
		size--;
		heap.set(pos, v2);
		v2.setPosition(pos);
		int parent = pos / 2;
		this.upheap(pos);
		this.downheap(pos);
		// if(parent > 0 && heap.get(parent).getDistance() > v2.getDistance()){
		// upheap(pos);
		// } else {
		// downheap(pos);
		// }
		return v;
	}

	public void keyReplaced(Vertex<E1> v) {
		int pos = v.getPosition();
		this.upheap(pos);
		this.downheap(pos);
		// int parent = pos / 2;
		// if(parent > 0 && heap.get(parent).getDistance() > v.getDistance()){
		// upheap(pos);
		// } else {
		// downheap(pos);
		// }
	}

}
