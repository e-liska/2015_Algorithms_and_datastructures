package e1;

import graphEN.Graph;
import graphEN.Vertex;

import java.util.ArrayList;
import java.util.Iterator;

public class DFSIterator<E1, E2> {
	private Graph<E1, E2> graph;
	private ArrayList<Vertex<E1>> visited;

	private void dfsVisit(Vertex<E1> v) {
		visited.add(v);
		Iterator<Vertex<E1>> adjacentV = graph.adjacentVertices(v);
		while (adjacentV.hasNext()) {
			Vertex<E1> next = adjacentV.next();
			if (!visited.contains(next)) {
				this.dfsVisit(next);
			}
		}
	}

	public Iterator<Vertex<E1>> iteratorDFS(Graph<E1, E2> g, Vertex<E1> start) {
		graph = g;
		visited = new ArrayList<Vertex<E1>>();
		this.dfsVisit(start);
		return visited.iterator();
	}

}
