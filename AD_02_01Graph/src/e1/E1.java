package e1;

import graphEN.EdgeListGraph;
import graphEN.Graph;
import graphEN.Vertex;

import java.util.Iterator;

public class E1 {
	public static void main(String[] args) {
		Graph<Integer, Integer> graph = new EdgeListGraph<Integer, Integer>();

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

		System.out.println(E1.maxNodeValue(graph));

	}

	private static Integer maxNodeValue(Graph<Integer, Integer> g) {
		Iterator<Vertex<Integer>> i = g.vertices();
		Integer max = null;
		if (i.hasNext()) {
			max = i.next().element();
		}
		while (i.hasNext()) {
			Integer a = i.next().element();
			if (a > max) {
				max = a;
			}
		}
		return max;
	}

	// private static Integer maxNodeValueWithoutVertices(Graph<Integer,
	// Integer> g) {
	// Iterator<Edge<Integer>> i = g.edges();
	// Integer max = null;
	// // if (i.hasNext()) {
	// // max = i.next().element();
	// // }
	// while (i.hasNext()) {
	// List<Vertex<E1>> ep = i.next().e
	// if (a > max) {
	// max = a;
	// }
	// }
	// return max;
	// }
}
