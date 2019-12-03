package e4;

import graphEN.Graph;
import graphEN.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BFS<V, E> {
	private Graph<V, E> graph;
	private Set<Vertex<V>> visited;

	public BFS(Graph<V, E> g) {
		graph = g;
		visited = new HashSet<Vertex<V>>();
	}

	public void printDistance(Vertex<V> v) {
		List<Vertex<V>> thisLevel = new ArrayList<Vertex<V>>();
		visited.add(v);
		thisLevel.add(v);
		int counter = 0;
		while (!thisLevel.isEmpty()) {
			List<Vertex<V>> nextLevel = new ArrayList<Vertex<V>>();
			counter++;

			System.out.print("Distance " + counter + " from vertices: ");

			for (Vertex<V> current : thisLevel) {
				// System.out.print(current.element() + " ");
				Iterator<Vertex<V>> adjacentV = graph.adjacentVertices(current);
				while (adjacentV.hasNext()) {
					Vertex<V> adjacent = adjacentV.next();
					if (!visited.contains(adjacent)) {
						visited.add(adjacent);
						nextLevel.add(adjacent);

						System.out.print(adjacent.element() + " ");
					}
				}
			}
			System.out.println();

			thisLevel = nextLevel;
		}
	}

	public Map<Vertex<V>, Integer> getDistance(Vertex<V> v) {
		Map<Vertex<V>, Integer> distances = new HashMap<Vertex<V>, Integer>();
		List<Vertex<V>> thisLevel = new ArrayList<Vertex<V>>();
		// visited.add(v);
		thisLevel.add(v);
		int counter = 0;
		while (!thisLevel.isEmpty()) {
			List<Vertex<V>> nextLevel = new ArrayList<Vertex<V>>();
			counter++;

			for (Vertex<V> current : thisLevel) {
				Iterator<Vertex<V>> adjacentV = graph.adjacentVertices(current);
				while (adjacentV.hasNext()) {
					Vertex<V> adjacent = adjacentV.next();
					if (!(distances.containsKey(adjacent) || adjacent.equals(v))) {
						distances.put(adjacent, counter);
						nextLevel.add(adjacent);

					}
				}
			}

			thisLevel = nextLevel;
		}
		return distances;
	}

}
