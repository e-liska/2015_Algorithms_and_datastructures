package e3WithGraph;

import graphEN.Edge;
import graphEN.Vertex;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
	private Map<Vertex<Integer>, Integer> visitedVertices = new HashMap<Vertex<Integer>, Integer>();

	// private Set<Vertex<Integer>> visitedVertices = new
	// HashSet<Vertex<Integer>>();

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
		Integer result = this.path(0, vertices[start]);
		if (result.equals(MAX))
			return "Use the stairs";
		return "From floor " + start + " to floor " + goal + " : " + result
				+ " moves.";
	}

	// private Integer path(Integer moves, Vertex<Integer> current) {
	// if (current.element().equals(goal))
	// return moves;
	//
	// System.out.println(current.element() + ". floor, " + moves + " moves");
	// visitedVertices.put(current, moves);
	//
	// Integer min = MAX;
	//
	// Iterator<Vertex<Integer>> adjacent = graph.adjacentVertices(current);
	// while ((adjacent).hasNext()) {
	// Vertex<Integer> next = adjacent.next();
	// if (visitedVertices.get(next) == null
	// || visitedVertices.get(next) > moves) {
	// Integer nextMoves = this.path(moves + 1, next);
	//
	// System.out.println("--- " + next.element() + ". floor, "
	// + nextMoves + " moves");
	// if (nextMoves < min) {
	// min = nextMoves;
	// }
	// }
	// }
	// return min;
	// }

	private Integer path(Integer moves, Vertex<Integer> current) {
		if (current.element().equals(goal))
			return moves;

		System.out.println(current.element() + ". floor, " + moves + " moves");
		visitedVertices.put(current, moves);

		Integer min = MAX;
		Iterator<Edge<Vertex<Integer>>> edges = graph.incidentEdges(current);
		while (edges.hasNext()) {
			Edge<Vertex<Integer>> e = edges.next();
			if (!e.element().equals(current)) {
				Vertex<Integer> next = e.element();
				if (visitedVertices.get(next) == null
						|| visitedVertices.get(next) > (moves + 1)) {
					Integer nextMoves = this.path(moves + 1, next);
					if (nextMoves < min) {
						min = nextMoves;
					}
				}
			}

		}
		return min;
	}

}
