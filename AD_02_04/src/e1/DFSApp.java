package e1;

import graphEN.Graph;
import graphEN.Vertex;

import java.util.Iterator;

public class DFSApp {
	public static void main(String[] args) {
		Graph<Integer, Integer> graph = new AdjacencyListGraph<Integer, Integer>();
		Vertex<Integer> v15 = graph.insertVertex(15);
		Vertex<Integer> v6 = graph.insertVertex(6);
		Vertex<Integer> v66 = graph.insertVertex(66);
		Vertex<Integer> v123 = graph.insertVertex(123);
		Vertex<Integer> v38 = graph.insertVertex(38);

		graph.insertEdge(v15, v6, 23);
		graph.insertEdge(v15, v66, 90);
		graph.insertEdge(v15, v38, 10);

		graph.insertEdge(v38, v66, 2);
		// graph.insertEdge(v38, v123, 55);

		// graph.insertEdge(v6, v123, 7);
		graph.insertEdge(v6, v66, 8);
		// graph.insertEdge(v123, v66, 76);

		DFSIterator<Integer, Integer> dfs = new DFSIterator<Integer, Integer>();
		Iterator<Vertex<Integer>> visited = dfs.iteratorDFS(graph, v38);

		int counter = 0;
		while (visited.hasNext()) {
			counter++;
			System.out.println(visited.next().element());
		}

		System.out.println("\nnr of vertices: " + graph.numVertices()
				+ ", nr of visited: " + counter);

		System.out.println("Is connected: " + DFSApp.isConnected(graph));

		System.out.println("Is 38 and 6 connected: (exp true) :"
				+ DFSApp.isPathBetween(graph, v6, v38));

		System.out.println("Is 38 and 123 connected: (exp false) :"
				+ DFSApp.isPathBetween(graph, v123, v38));

	}

	public static boolean isConnected(Graph<Integer, Integer> g) {
		DFSIterator<Integer, Integer> dfs = new DFSIterator<Integer, Integer>();
		Iterator<Vertex<Integer>> visited = dfs.iteratorDFS(g, g.vertices()
				.next());
		int counter = 0;
		while (visited.hasNext()) {
			counter++;
			visited.next();
		}
		return (counter == g.numVertices());
	}

	public static boolean isPathBetween(Graph<Integer, Integer> g,
			Vertex<Integer> v, Vertex<Integer> w) {
		DFSIterator<Integer, Integer> dfs = new DFSIterator<Integer, Integer>();
		Iterator<Vertex<Integer>> visited = dfs.iteratorDFS(g, v);
		while (visited.hasNext()) {
			if (visited.next().equals(w))
				return true;
		}
		return false;
	}
}
