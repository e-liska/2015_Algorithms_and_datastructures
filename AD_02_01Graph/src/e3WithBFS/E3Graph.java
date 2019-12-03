package e3WithBFS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import e2.AdjacencyListGraph;
import graphEN.Edge;
import graphEN.Vertex;

public class E3Graph {

	private static final Integer MAX = 1000002;
	// private static final String DOWN="Down";
	// private static final String UP="Up";

	private AdjacencyListGraph<Integer, Vertex<Integer>> graph = new AdjacencyListGraph<Integer, Vertex<Integer>>();
	private Integer floor;
	private Integer start;
	private Integer goal;
	private Integer up;
	private Integer down;
	Vertex<Integer>[] vertices;

	public E3Graph(Integer f, Integer s, Integer g, Integer u, Integer d) {
		floor = f;
		start = s;
		goal = g;
		up = u;
		down = d;
		vertices = new Vertex[floor + 1];
		this.buildGraph();
	}

	private void buildGraph() {
		for (Integer i = 0; i <= floor; i++) {
			vertices[i] = graph.insertVertex(i);
		}
		for (Integer i = 0; i <= floor; i++) {
			if ((i - down) >= 0) {
				graph.insertEdge(vertices[i - down], vertices[i], vertices[i
				                                                           - down]);
			}
			if ((i + up) <= floor) {
				graph.insertEdge(vertices[i + up], vertices[i],
						vertices[i + up]);
			}
		}

	}

	public String countMoves() {
		Integer result = this.pathWithBFS(vertices[start]);
		if (result.equals(MAX))
			return "From floor " + start + " to floor " + goal
					+ " : Use the stairs";
		return "From floor " + start + " to floor " + goal + " : " + result
				+ " moves.";
	}

	private Integer pathWithBFS(Vertex<Integer> current) {
		Set<Vertex<Integer>> visited = new HashSet<Vertex<Integer>>();
		List<Vertex<Integer>> thisLevel = new ArrayList<Vertex<Integer>>();

		visited.add(current);
		thisLevel.add(current);
		int moves = 0;
		while (!thisLevel.isEmpty()) {
			List<Vertex<Integer>> nextLevel = new ArrayList<Vertex<Integer>>();
			for (Vertex<Integer> v : thisLevel) {
				// System.out.print(moves + " moves, floor: " + v.element()
				// + ", next: ");
				if (v.element().equals(goal)) {
					System.out.println();
					return moves;
				}
				Iterator<Edge<Vertex<Integer>>> incident = graph
						.incidentEdges(v);
				while (incident.hasNext()) {
					Edge<Vertex<Integer>> e = incident.next();
					if (!e.element().equals(v)) {
						Vertex<Integer> temp = e.element();
						if (!visited.contains(temp)) {
							visited.add(temp);
							nextLevel.add(temp);

						}
					}
				}
				// for (Vertex<Integer> vertex : nextLevel) {
				// System.out.print(vertex.element() + ", ");
				// }
				// System.out.println();
			}
			moves++;
			thisLevel = nextLevel;
		}
		return MAX;
	}
}
