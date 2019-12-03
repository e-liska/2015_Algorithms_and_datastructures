package e26_1_siblings;

import networkFlow.Network;
import networkFlow.Vertex;

public class SiblingsApp {
	public static void main(String[] args) {
		Network<String> city = new Network<String>();
		Vertex<String> vHome = city.createVertex("Home");
		Vertex<String> v1 = city.createVertex("Corner 1");
		Vertex<String> v2 = city.createVertex("Corner 2");
		Vertex<String> v3 = city.createVertex("Corner 3");
		Vertex<String> v4 = city.createVertex("Corner 4");
		Vertex<String> vSchool = city.createVertex("School");

		city.setSource(vHome);
		city.setSink(vSchool);

		city.createEdge(vHome, v1, 1);
		city.createEdge(v1, v2, 1);
		city.createEdge(v2, vSchool, 1);
		city.createEdge(vHome, v3, 1);
		city.createEdge(v3, v2, 1);
		city.createEdge(v4, vSchool, 1);
		city.createEdge(v2, v4, 1);

		Siblings<String> problem = new Siblings<String>(city);
		System.out.println("They can go to the same school (true expected): "
				+ problem.pathExists());
		// city.printEdgesOfGraph();

		Network<String> city2 = new Network<String>();
		Vertex<String> cHome = city2.createVertex("Home");
		Vertex<String> c1 = city2.createVertex("Corner 1");
		Vertex<String> c2 = city2.createVertex("Corner 2");
		Vertex<String> c3 = city2.createVertex("Corner 3");
		Vertex<String> c4 = city2.createVertex("Corner 4");
		Vertex<String> cSchool = city2.createVertex("School");

		city2.setSource(cHome);
		city2.setSink(cSchool);

		city2.createEdge(cHome, c1, 1);
		city2.createEdge(c1, c2, 1);
		city2.createEdge(c2, cSchool, 1);
		city2.createEdge(cHome, c3, 1);
		city2.createEdge(c3, c1, 1);
		city2.createEdge(c4, cSchool, 1);
		city2.createEdge(c2, c4, 1);

		Siblings<String> problem2 = new Siblings<String>(city2);
		System.out
				.println("Case 2, (false expected): " + problem2.pathExists());
	}
}
