package SPMST;

public class Vertex<E1> {
	private E1 element; // the element in the vertex
	private int numIncidentEdges; // number of edges incident to the vertex
	private int position;
	private int distance;

	/**
	 * Constructor
	 */
	public Vertex(E1 e) {
		element = e;
		numIncidentEdges = 0;
		position = -1;
		distance = Integer.MAX_VALUE;
	}

	/**
	 * @return element in the vertex
	 */
	public E1 element() {
		return element;
	}

	/**
	 * @return number of incident edges
	 */
	public int getNumIncidentEdges() {
		return numIncidentEdges;
	}

	/**
	 * increases the number of incident edges by 1.
	 */
	public void incNumIncidentEdges() {
		numIncidentEdges++;
	}

	/**
	 * decreases the number of incident edges by 1.
	 */
	public void decNumIncidentEdges() {
		numIncidentEdges--;
	}

	public int getPosition() {
		return position;
	}

	public int getDistance() {
		return distance;
	}

	public void setPosition(int position) {
		this.position = position;

	}

	public void setDistance(int distance) {
		this.distance = distance;

	}
}