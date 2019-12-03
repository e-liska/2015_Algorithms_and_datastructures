package networkFlow;

public class MaxFlowApp {
	public static void main(String[] args) {
		Network<String> network = new Network<String>();

		Vertex<String> vVertex = network.createVertex("Vancouver");
		Vertex<String> eVertex = network.createVertex("Edmonton");
		Vertex<String> sVertex = network.createVertex("Saskatoon");
		Vertex<String> wVertex = network.createVertex("Winnipeg");
		Vertex<String> cVertex = network.createVertex("Calgary");
		Vertex<String> rVertex = network.createVertex("Regina");

		network.setSource(vVertex);
		network.setSink(wVertex);

		network.createEdge(vVertex, eVertex, 16);
		network.createEdge(eVertex, sVertex, 12);
		network.createEdge(sVertex, wVertex, 20);
		network.createEdge(vVertex, cVertex, 13);
		network.createEdge(cVertex, rVertex, 14);
		network.createEdge(rVertex, wVertex, 4);
		network.createEdge(cVertex, eVertex, 4);
		network.createEdge(sVertex, cVertex, 9);
		network.createEdge(rVertex, sVertex, 7);

		// network.printEdgesOfGraph();
		System.out.println("\n\nmax flow ");
		network.fordFulkerson();
		network.printEdgesOfGraph();
		System.out.println("Max flow is " + network.getMaxFlow());
	}
}
