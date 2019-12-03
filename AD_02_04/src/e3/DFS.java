package e3;

import graphEN.Edge;
import graphEN.Graph;
import graphEN.Vertex;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DFS<V, E> {
	// private Graph<V, E> graph;
	private Map<Vertex<V>, Label> vertices;
	private Map<Edge<E>, Label> edges;

	public DFS() {
		vertices = new HashMap<Vertex<V>, Label>();
		edges = new HashMap<Edge<E>, Label>();
	}

	public void searchDFS(Graph<V, E> graph) {
		this.search(graph);
		System.out.println("Vertices:");
		for (Vertex<V> v : vertices.keySet()) {
			System.out.println(v.element() + ": " + vertices.get(v));
		}
		System.out.println("\nEdges: ");
		for (Edge<E> e : edges.keySet()) {
			System.out.println(e.element() + ": " + edges.get(e));
		}

	}

	// public void findPath(Graph<V, E> g, Vertex<V> v) {
	// this.printPath(g, v, null, true, v);
	// }

	// private void printPath(Graph<V, E> g, Vertex<V> v, Edge<E> oldE,
	// boolean first, Vertex<V> start) {
	// if (!v.equals(start) || first) {
	// System.out.println("Vertex " + v.element());
	// Iterator<Edge<E>> edgIt = g.incidentEdges(v);
	// while (edgIt.hasNext()) {
	// Edge<E> e = edgIt.next();
	// if (!e.equals(oldE) && edges.get(e).equals(Label.DISCOVERY)) {
	// System.out.println("edge: " + e.element());
	// this.printPath(g, g.opposite(v, e), e, false, start);
	// break;
	// }
	// }
	// }
	// }

	private void search(Graph<V, E> g) {
		Iterator<Vertex<V>> verIt = g.vertices();
		while (verIt.hasNext()) {
			vertices.put(verIt.next(), Label.UNEXPLORED);
		}

		Iterator<Edge<E>> edgIt = g.edges();
		while (edgIt.hasNext()) {
			edges.put(edgIt.next(), Label.UNEXPLORED);
		}

		for (Vertex<V> v : vertices.keySet()) {
			if (vertices.get(v).equals(Label.UNEXPLORED)) {
				System.out.println("Starting with " + v.element());
				this.visit(g, v);
			}
		}
	}

	private void visit(Graph<V, E> g, Vertex<V> v) {
		vertices.replace(v, Label.VISITED);
		Iterator<Edge<E>> incEdgesIt = g.incidentEdges(v);
		while (incEdgesIt.hasNext()) {
			Edge<E> e = incEdgesIt.next();
			if (edges.get(e).equals(Label.UNEXPLORED)) {
				Vertex<V> w = g.opposite(v, e);
				if (vertices.get(w).equals(Label.UNEXPLORED)) {
					edges.replace(e, Label.DISCOVERY);
					this.visit(g, w);
				} else {
					edges.replace(e, Label.BACK);
				}
			}
		}
	}

}
