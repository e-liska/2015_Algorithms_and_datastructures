package mst;

import graphEN.Edge;
import graphEN.Graph;
import graphEN.Vertex;

import java.util.List;
import java.util.Set;

public class KruskalApp {
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
		graph.insertEdge(v38, v123, 55);

		graph.insertEdge(v6, v123, 7);
		graph.insertEdge(v6, v66, 8);
		graph.insertEdge(v123, v66, 76);

		Kruskal k = new Kruskal(graph);
		List<Set<Vertex<Integer>>> msts = k.getMST();
		// for (Set<Vertex<Integer>> set : msts) {
		// System.out.print("MST vertexes: ");
		// for (Vertex<Integer> vertex : set) {
		// System.out.print(vertex.element() + ", ");
		// }
		// System.out.println();
		// }

		Set<Edge<Integer>> mstE = k.getEdges();
		System.out.print("Edges: ");
		for (Edge<Integer> edge : mstE) {
			System.out.print(edge.element() + ", ");
		}
	}
}
