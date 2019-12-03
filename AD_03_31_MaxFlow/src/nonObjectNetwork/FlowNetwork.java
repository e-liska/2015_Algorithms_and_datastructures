package nonObjectNetwork;

import java.util.ArrayList;
import java.util.List;

public class FlowNetwork {

	private int[][] capacities;
	private int[][] flows;
	private int[][] residuals;
	
	/**
	 * 
	 * @param vertices = number of vertices
	 * @param edges = array of arrays.
	 *  Inner array must consist of 3 integers: from, to, and capacity
	 *  
	 *  The source is vertex 0. 
	 *  The sink is vertex 1.
	 */
	public FlowNetwork(int vertices, int[][] edges){
		capacities = new int[vertices][vertices];
		flows = new int[vertices][vertices];
		residuals = new int[vertices][vertices];

		for(int i = 0; i < edges.length; i++){
			int from = edges[i][0];
			int to = edges[i][1];
			int capacity = edges[i][2];
			capacities[from][to] = capacity;
		}

		
	}
	
	private void updateResidualNetwork(){
		for(int u = 0; u < capacities.length; u++){
			for(int v = 0; v < capacities.length; v++){
				if(capacities[u][v] != 0){
					residuals[u][v] = capacities[u][v] - flows[u][v];
				}
				if(flows[u][v] != 0){
					residuals[v][u] = flows[u][v];
				}
			}
		}
	}
	
	private int[] findAugmentingPath(){
		int[] visited = new int[capacities.length];
		for(int i = 1; i < visited.length; i++){
			visited[i] = -1;
		}
		dfs(visited, 0);
		if(visited[1] == -1){//didn't reach the sink
			return null;			
		}		
		int[] path = new int[visited.length];
		int pathLength = 0;
		int current = 1;
		while(current != 0){
			if(visited[current] != -1){
				path[pathLength] = current;
				pathLength++;
				current = visited[current];
			}
		}
		return path;
	}
	
	private void dfs(int[] visited, int current){
		for(int i = 0; i < residuals.length; i++){
			if(residuals[current][i] != 0 && visited[i] == -1){
				visited[i] = current;
				dfs(visited, i);
			}
		}
	}
	
	public int maxFlow(){
		updateResidualNetwork();
		int [] augmentingPath = findAugmentingPath();
		while(augmentingPath != null){
			int min = Integer.MAX_VALUE;
			int current = 0;
			while(augmentingPath[current] != 0){
				min = Math.min(min, residuals[augmentingPath[current + 1]][augmentingPath[current]]);
				current++;
			}
			current = 0;
			while(augmentingPath[current] != 0){
				int to = augmentingPath[current];
				int from = augmentingPath[current + 1];
				if(capacities[from][to] != 0){
					flows[from][to] += min;
				} else {
					flows[to][from] -= min;
				}
				current++;
			}
			updateResidualNetwork();
			augmentingPath = findAugmentingPath();

		}
		int maxFlow = 0;
		for(int i = 0; i < flows.length; i++){
			maxFlow += flows[0][i];
		}
		return maxFlow;
	}
	
	public void print(int[][] arr){
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if(arr[i][j] < 10){
					System.out.print(0);
				}
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("*********************");
	}
	
}
