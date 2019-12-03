package SPMST;

import java.util.Iterator;

public class Dijkstra {
	private static AdaptablePriorityQueueForGraph<Integer> queue = new AdaptablePriorityQueueForGraph<Integer>();

	public static void main(String[] args) {

		Graph<Integer> graph = new ShortestPathEdgeListGraph<Integer>();
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

		Dijkstra.shortestPath(graph, v6);

		Iterator<Vertex<Integer>> iteratorV = graph.vertices();
		while (iteratorV.hasNext()) {
			Vertex<Integer> current = iteratorV.next();
			System.out.println("Vertex " + current.element() + ", distance:  "
					+ current.getDistance());
		}
	}

	private static void shortestPath(Graph<Integer> graph, Vertex<Integer> v) {
		Iterator<Vertex<Integer>> iteratorV = graph.vertices();
		while (iteratorV.hasNext()) {
			Vertex<Integer> current = iteratorV.next();
			if (current.equals(v)) {
				current.setDistance(0);
			}
			queue.insert(current);
		}
		while (!queue.isEmpty()) {
			Vertex<Integer> current = queue.removeMin();
			Iterator<Edge> IteratorE = graph.incidentEdges(current);
			while (IteratorE.hasNext()) {
				Edge e = IteratorE.next();
				Vertex<Integer> opposite = graph.opposite(current, e);
				int currentDistance = current.getDistance() + e.element();
				if (currentDistance < opposite.getDistance()) {
					opposite.setDistance(currentDistance);
					queue.keyReplaced(opposite);
				}
			}
		}
	}
}
