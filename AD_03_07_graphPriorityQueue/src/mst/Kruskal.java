package mst;

import graphEN.Edge;
import graphEN.Graph;
import graphEN.Vertex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Kruskal {
	private Graph<Integer, Integer> graph;
	private PriorityQueue<Integer, Edge<Integer>> queueEdges;
	private List<Set<Vertex<Integer>>> clusters;
	private Set<Edge<Integer>> mstEdges;

	public Kruskal(Graph<Integer, Integer> g) {
		queueEdges = new PriorityQueue<Integer, Edge<Integer>>();
		graph = g;
		clusters = new ArrayList<Set<Vertex<Integer>>>();
		mstEdges = new HashSet<Edge<Integer>>();
	}

	public List<Set<Vertex<Integer>>> getMST() {
		this.mst();
		return clusters;
	}

	public Set<Edge<Integer>> getEdges() {
		return mstEdges;
	}

	private void createGraph() {
		Vertex<Integer> v15 = graph.insertVertex(15);
		Vertex<Integer> v6 = graph.insertVertex(6);
		Vertex<Integer> v66 = graph.insertVertex(66);
		Vertex<Integer> v123 = graph.insertVertex(123);
		Vertex<Integer> v38 = graph.insertVertex(38);

		graph.insertEdge(v15, v6, 23);
		graph.insertEdge(v15, v66, 90);
		graph.insertEdge(v15, v38, 10);

		graph.insertEdge(v38, v66, 2);
		graph.insertEdge(v38, v123, 55);

		graph.insertEdge(v6, v123, 7);
		graph.insertEdge(v6, v66, 8);
		graph.insertEdge(v123, v66, 76);

	}

	private void mst() {
		Iterator<Vertex<Integer>> iteratorV = graph.vertices();
		while (iteratorV.hasNext()) {
			Set<Vertex<Integer>> s = new HashSet<Vertex<Integer>>();
			s.add(iteratorV.next());
			clusters.add(s);
		}
		Iterator<Edge<Integer>> itEdges = graph.edges();
		while (itEdges.hasNext()) {
			Edge<Integer> current = itEdges.next();
			queueEdges.insert(current, current.element());
		}

		while (mstEdges.size() < graph.numVertices() - 1) {
			Edge<Integer> e = queueEdges.remove();
			List<Vertex<Integer>> vertexes = graph.endVertices(e);
			Vertex<Integer> v1 = vertexes.get(0);
			Vertex<Integer> v2 = vertexes.get(1);
			Set<Vertex<Integer>> cluster1 = this.getCluster(v1);
			Set<Vertex<Integer>> cluster2 = this.getCluster(v2);
			if (!cluster1.equals(cluster2)) {
				mstEdges.add(e);
				this.union(cluster1, cluster2);
			}
		}
	}

	public void printGraph() {
		// Iterator<Vertex<Integer>> iteratorV = graph.vertices();
		// while (iteratorV.hasNext()) {
		// Vertex<Integer> current = iteratorV.next();
		// System.out.println("Vertex " + current.element() + ", distance:  "
		// + current.getDistance());
		// }
	}

	private Set<Vertex<Integer>> union(Set<Vertex<Integer>> c1,
			Set<Vertex<Integer>> c2) {
		if (c1.size() > c2.size()) {
			c1.addAll(c2);
			clusters.remove(c2);
			return c1;
		} else {
			c2.addAll(c1);
			clusters.remove(c1);
			return c2;
		}
	}

	private Set<Vertex<Integer>> getCluster(Vertex<Integer> v) {
		for (Set<Vertex<Integer>> c : clusters) {
			if (c.contains(v))
				return c;
		}
		return null;
	}
}
