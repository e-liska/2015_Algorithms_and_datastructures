package networkFlow;

public class Edge<V> {
	private Vertex<V> start;
	private Vertex<V> end;
	private int capacity;
	private int flow;

	public Edge(Vertex<V> s, Vertex<V> e, int capacity) {
		this.start = s;
		this.end = e;
		this.capacity = capacity;
		this.flow = 0;
	}

	public Vertex<V> getStart() {
		return this.start;
	}

	public void setStart(Vertex<V> start) {
		this.start = start;
	}

	public Vertex<V> getEnd() {
		return this.end;
	}

	public void setEnd(Vertex<V> end) {
		this.end = end;
	}

	public int getCapacity() {
		return this.capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getFlow() {
		return this.flow;
	}

	public void setFlow(int flow) {
		this.flow = flow;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Edge) {
			Edge<V> other = (Edge<V>) obj;
			if (this.start.equals(other.start) && this.end.equals(other.end))
				return true;
		}
		return false;
	}
}
