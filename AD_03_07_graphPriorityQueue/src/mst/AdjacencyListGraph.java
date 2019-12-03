package mst;

import graphEN.Edge;
import graphEN.Graph;
import graphEN.Vertex;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdjacencyListGraph<V, E> implements Graph<V, E> {
	private ArrayList<Vertex<V>> vertices; // vertices in the graph
	private ArrayList<Edge<E>> edges; // edges in a graph

	/**
	 * inner class implementing the Vertex in an E2.
	 */
	private class AdjacencyListVertex implements Vertex<V> {
		private V element; // the element in the vertex
		private List<Edge<E>> edgesOfV;

		/**
		 * Constructor
		 */
		public AdjacencyListVertex(V e) {
			element = e;
			edgesOfV = new ArrayList<Edge<E>>();
		}

		/**
		 * @return element in the vertex
		 */
		@Override
		public V element() {
			return element;
		}

		public List<Edge<E>> getEdges() {
			return edgesOfV;
		}

		public void addEdge(Edge<E> e) {
			edgesOfV.add(e);
		}

		public boolean removeEdge(Edge<E> e) {
			return edgesOfV.remove(e);
		}

		public Iterator<Edge<E>> edgesOfVertexI() {
			return edgesOfV.iterator();
		}
	}

	/**
	 * Inner class, implementing the Egde in an E2
	 */
	private class AdjacencyListEdge implements Edge<E> {
		private E element; // elementet indeholdt i kanten
		private Vertex<V> endPoint1; // knuden i kantens ene ende
		private Vertex<V> endPoint2; // knuden i kantens anden ende

		/**
		 * creates an edge between vertices v1 and v2 containing element e
		 *
		 * @param e
		 *            : the object in the edge
		 * @param v1
		 *            : the edge's first end point
		 * @param v2
		 *            : the edge's first end point
		 */
		public AdjacencyListEdge(E e, Vertex<V> v1, Vertex<V> v2) {
			element = e;
			endPoint1 = v1;
			endPoint2 = v2;
		}

		/**
		 * @return the element in the edge
		 */
		@Override
		public E element() {
			return element;
		}

		/**
		 * Returns an ArrayList containing the edge's two end points
		 *
		 * @return the vertices for the edge
		 */
		public List<Vertex<V>> endPoints() {
			// Vertex<V>[] ep = (Vertex<V>[]) new Object[2];
			List<Vertex<V>> ep = new ArrayList<Vertex<V>>(2);
			ep.add(endPoint1);
			ep.add(endPoint2);
			return ep;
		}
	}

	/**
	 * Constructor.
	 */
	public AdjacencyListGraph() {
		edges = new ArrayList<Edge<E>>();
		vertices = new ArrayList<Vertex<V>>();
	}

	/**
	 * Returns an iterator with v's adjacent vertices.
	 *
	 * @return an iterator with v's adjacent vertices.
	 * @param v
	 *            : the vertex for which you need the adjacent vertices
	 */
	@Override
	public Iterator<Vertex<V>> adjacentVertices(Vertex<V> v) {
		AdjacencyListVertex vertex = (AdjacencyListVertex) v;
		ArrayList<Vertex<V>> naboVertices = new ArrayList<Vertex<V>>();
		Iterator<Edge<E>> i = vertex.edgesOfVertexI();
		while (i.hasNext()) {
			AdjacencyListEdge e = (AdjacencyListEdge) i.next();
			Vertex<V> v1 = e.endPoints().get(0);
			Vertex<V> v2 = e.endPoints().get(1);
			if (v == v1) {
				naboVertices.add(v2);
			}
			if (v == v2) {
				naboVertices.add(v1);
			}
		}
		return naboVertices.iterator();
	}

	/**
	 * Checks whether two vertices are adjacent.
	 *
	 * @return true if v and w are adjacent, false otherwise
	 * @param v
	 *            : vertex that could be adjacent to w
	 * @param w
	 *            : vertex that could be adjacent to v
	 */
	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) {
		AdjacencyListVertex av = (AdjacencyListVertex) v;
		AdjacencyListVertex aw = (AdjacencyListVertex) w;
		if (av.getEdges().size() > aw.getEdges().size()) {
			AdjacencyListVertex temp = av;
			av = aw;
			aw = temp;
		}
		Iterator<Edge<E>> i = av.edgesOfVertexI();
		boolean found = false;
		while (!found && i.hasNext()) {
			AdjacencyListEdge e = (AdjacencyListEdge) i.next();
			Vertex<V> v1 = e.endPoints().get(0);
			Vertex<V> v2 = e.endPoints().get(1);
			if ((v1 == v && v2 == w) || (v1 == w && v2 == v)) {
				found = true;
			}
		}
		return found;
	}

	/**
	 * A vertex' degree.
	 *
	 * @return v's degree
	 * @param v
	 *            : The vertex to be checked
	 */
	@Override
	public int degree(Vertex<V> v) {
		return ((AdjacencyListVertex) v).getEdges().size();
	}

	/**
	 * Returns an iterator with the graph's edges.
	 *
	 * @return en an iterator with the graph's edges
	 */
	@Override
	public Iterator<Edge<E>> edges() {
		return edges.iterator();
	}

	/**
	 * Returns an array with e's end points.
	 *
	 * @return an array with e's end points.
	 * @param e
	 *            : an edge, for which to find the end points.
	 */
	@Override
	public List<Vertex<V>> endVertices(Edge<E> e) {
		return ((AdjacencyListEdge) e).endPoints();
	}

	/**
	 * Inserts and returns a new edge between vertices v and w.
	 *
	 * @return the new edge
	 * @param v
	 *            : the edge's first end point
	 * @param w
	 *            : the edge's first end point
	 * @param o
	 *            : the element contained in the edge
	 */
	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E o) {
		Edge<E> e = new AdjacencyListEdge(o, v, w);
		edges.add(e);
		((AdjacencyListVertex) v).addEdge(e);
		if (!v.equals(w)) {
			((AdjacencyListVertex) w).addEdge(e);
		}
		return e;
	}

	/**
	 * inserts and returns a new vertex.
	 *
	 * @return the new vertex
	 * @param o
	 *            : the element contained in the vertex
	 */
	@Override
	public Vertex<V> insertVertex(V o) {
		Vertex<V> v = new AdjacencyListVertex(o);
		vertices.add(v);
		return v;
	}

	/**
	 * @return the number of edges in the graph
	 */
	@Override
	public int numEdges() {
		return edges.size();
	}

	/**
	 * @return the number of vertices in the graph
	 */
	@Override
	public int numVertices() {
		return vertices.size();
	}

	/**
	 * Given a vertex and an edge indcident to that vertex, returns the edge's
	 * other end point
	 *
	 * @return the other end pint.
	 * @param v
	 *            : vertex that is an end point of e
	 * @param e
	 *            : an edge
	 */
	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) {
		Vertex<V> v1 = ((AdjacencyListEdge) e).endPoints().get(0);
		Vertex<V> v2 = ((AdjacencyListEdge) e).endPoints().get(1);
		if (v == v1)
			return v2;
		else
			return v1;
	}

	/**
	 * removes edge e
	 *
	 * @param e
	 *            : the edge to be removed
	 */
	@Override
	public void removeEdge(Edge<E> e) {
		Vertex<V> v1 = ((AdjacencyListEdge) e).endPoints().get(0);
		Vertex<V> v2 = ((AdjacencyListEdge) e).endPoints().get(1);
		((AdjacencyListVertex) v1).removeEdge(e);
		((AdjacencyListVertex) v2).removeEdge(e);
		edges.remove(e);
	}

	/**
	 * removes vertex v and all its incident edges.
	 *
	 * @param v
	 *            : the vertex to be removed
	 */
	@Override
	public void removeVertex(Vertex<V> v) {
		AdjacencyListVertex av = (AdjacencyListVertex) v;
		Iterator<Edge<E>> i = av.edgesOfVertexI();
		while (i.hasNext()) {
			AdjacencyListEdge e = (AdjacencyListEdge) i.next();
			Vertex<V> v1 = e.endPoints().get(0);
			Vertex<V> v2 = e.endPoints().get(1);
			if (v1 == v || v2 == v) {
				((AdjacencyListVertex) v1).removeEdge(e);
				((AdjacencyListVertex) v2).removeEdge(e);
				i.remove();
			}
		}
		vertices.remove(v);
	}

	/**
	 * Returns an iterator containing the vertices in the graph
	 *
	 * @return an iterator containing the vertices in the graph
	 */
	@Override
	public Iterator<Vertex<V>> vertices() {
		return vertices.iterator();
	}

	/**
	 * Returns an iterator with v's incident edges.
	 *
	 * @return an iterator with v's incident edges.
	 * @param v
	 *            : The vertex
	 */
	@Override
	public Iterator<Edge<E>> incidentEdges(Vertex<V> v) {
		AdjacencyListVertex av = (AdjacencyListVertex) v;
		return av.edgesOfVertexI();
	}
}
