package networkFlow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Network<V> {
	private List<Edge<V>> edges;
	private List<Edge<V>> residualEdges;
	private List<Vertex<V>> vertices;
	private Vertex<V> source;
	private Vertex<V> sink;
	private Set<Vertex<V>> visited;

	public Network() {
		edges = new ArrayList<Edge<V>>();
		residualEdges = new ArrayList<Edge<V>>();
		vertices = new ArrayList<Vertex<V>>();
	}

	public void setSource(Vertex<V> v) {
		source = v;
	}

	public void setSink(Vertex<V> v) {
		sink = v;
	}

	public Vertex<V> createVertex(V e) {
		Vertex<V> v = new Vertex<V>(e);
		vertices.add(v);
		return v;
	}

	// Create network

	public Edge<V> createEdge(Vertex<V> start, Vertex<V> end, int capacity) {
		Edge<V> e = new Edge<V>(start, end, capacity);
		start.addOutcommingEdge(e);
		end.addIncommingEdge(e);
		this.edges.add(e);
		return e;
	}

	// Residual network

	private void createResidualGraph() {
		this.deleteResidualEdges();
		for (Edge<V> e : edges) {
			if (e.getFlow() != 0) {
				// Edge<V> residualEdgeOpposite =
				this.createResidualEdge(e.getEnd(), e.getStart(), e.getFlow());
			}

			int capacityLeft = e.getCapacity() - e.getFlow();
			if (capacityLeft > 0) {
				this.createResidualEdge(e.getStart(), e.getEnd(), capacityLeft);
			}
		}
	}

	private Edge<V> createResidualEdge(Vertex<V> start, Vertex<V> end, int flow) {
		Edge<V> e = new Edge<V>(start, end, 0);
		start.addResidualOutcommingEdge(e);
		end.addResidualIncommingEdge(e);
		this.residualEdges.add(e);
		e.setFlow(flow);
		return e;
	}

	private void deleteResidualEdges() {
		for (Edge<V> e : residualEdges) {
			e.getStart().removeResidualOutcommingEdge(e);
			e.getEnd().removeResidualIncommingEdge(e);
		}

		residualEdges = new ArrayList<Edge<V>>();
	}

	// Path

	private List<Edge<V>> getPath(Vertex<V> start, Vertex<V> end) {
		visited = new HashSet<Vertex<V>>();
		List<Edge<V>> currentPath = new ArrayList<Edge<V>>();
		return this.findPath(start, end, currentPath);
	}

	private List<Edge<V>> findPath(Vertex<V> current, Vertex<V> goal,
			List<Edge<V>> currentPath) {
		visited.add(current);
		for (Edge<V> e : current.getResidualOutcommingEdges()) {
			Vertex<V> nextVertex = e.getEnd();
			if (nextVertex.equals(goal)) {
				currentPath.add(e);
				return currentPath;
			}
			if (!visited.contains(nextVertex)) {
				currentPath.add(e);
				List<Edge<V>> trialPath = this.findPath(nextVertex, goal,
						currentPath);
				if (trialPath != null
						&& trialPath.get(trialPath.size() - 1).getEnd()
								.equals(goal))
					return trialPath;
				currentPath.remove(e);
			}

		}
		return null;
	}

	public void setFlowInPath(List<Edge<V>> path, int value) {
		for (Edge<V> edge : path) {
			edge.setFlow(value);
		}
	}

	public int findMinFlowInPath(List<Edge<V>> path) {
		int min = path.get(0).getFlow();
		for (Edge<V> current : path) {
			if (current.getFlow() < min) {
				min = current.getFlow();
			}
		}
		return min;
	}

	// Algorythm

	public void fordFulkerson() {
		this.createResidualGraph();
		List<Edge<V>> path = this.getPath(source, sink);
		while (path != null) {
			int residualCapacity = this.findMinFlowInPath(path);
			for (Edge<V> e : path) {
				Edge<V> originalEdge = this.getOriginalEdge(e);
				if (edges.contains(e)) {
					originalEdge.setFlow(originalEdge.getFlow()
							+ residualCapacity);
				} else {
					originalEdge.setFlow(originalEdge.getFlow()
							- residualCapacity);
				}
			}
			this.createResidualGraph();
			path = this.getPath(source, sink);
		}
	}

	private Edge<V> getOriginalEdge(Edge<V> residualEdge) {
		for (Edge<V> current : edges) {
			if ((current.getStart().equals(residualEdge.getStart()) || current
					.getEnd().equals(residualEdge.getStart()))
					&& (current.getStart().equals(residualEdge.getEnd()) || current
							.getEnd().equals(residualEdge.getEnd())))
				// System.out.println("wow it works, "
				// + current.getStart().getElement() + ", "
				// + current.getEnd().getElement());
				return current;
		}
		return null;
	}

	// Print network

	public void printEdgesOfGraph() {
		for (Edge<V> e : edges) {
			System.out.println("Edge f=" + e.getFlow() + ", c="
					+ e.getCapacity() + ", startV= "
					+ e.getStart().getElement() + ", endV= "
					+ e.getEnd().getElement());

		}
	}

	public void printEdgesOfResidualGraph() {
		for (Edge<V> e : residualEdges) {
			System.out.println("Edge f=" + e.getFlow() + ", c="
					+ e.getCapacity() + ", startV= "
					+ e.getStart().getElement() + ", endV= "
					+ e.getEnd().getElement());

		}
	}

	public int getMaxFlow() {
		int flow = 0;
		for (Edge<V> e : source.getOutcommingEdges()) {
			flow += e.getFlow();
		}
		return flow;
	}

}
