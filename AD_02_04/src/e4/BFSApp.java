package e4;

import e1.AdjacencyListGraph;
import graphEN.Graph;
import graphEN.Vertex;

import java.util.Map;

public class BFSApp {
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

		BFS<Integer, Integer> bfs = new BFS<Integer, Integer>(graph);
		bfs.printDistance(v38);

		System.out.println("\n38");
		Map<Vertex<Integer>, Integer> distances = bfs.getDistance(v38);
		for (Vertex<Integer> v : distances.keySet()) {
			System.out.println("--Distance " + distances.get(v)
					+ " from vertex " + v.element());
		}

	}
}
