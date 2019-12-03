package nonObjectNetwork;


public class App {

	public static void main(String[] args) {
		int vertices = 6;
		int[][] edges = {
				{0, 2, 16},
				{0, 3, 13},
				{3, 2, 4},
				{2, 4, 12},
				{3, 5, 14},
				{4, 3, 9},
				{5, 4, 7},
				{5, 1, 4},
				{4, 1, 20}
		};
		FlowNetwork f = new FlowNetwork(vertices, edges);
		System.out.println(f.maxFlow());
		int[][] edges2 = {
				{0, 2, 5},
				{0, 3, 15},
				{2, 4, 5},
				{2, 5, 5},
				{3, 4, 5},
				{3, 5, 5},
				{4, 1, 15},
				{5, 1, 5}
		};
		f = new FlowNetwork(vertices, edges2);
		System.out.println(f.maxFlow());
		
		
	}

}
