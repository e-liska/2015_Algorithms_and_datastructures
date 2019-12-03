package networkFlow;

import java.util.ArrayList;
import java.util.List;

public class Vertex<V> {
	private V element;
	private List<Edge<V>> incommingEdges;
	private List<Edge<V>> outcommingEdges;
	private List<Edge<V>> residualIncommingEdges;
	private List<Edge<V>> residualOutcommingEdges;

	public Vertex(V element) {
		this.element = element;
		incommingEdges = new ArrayList<Edge<V>>();
		outcommingEdges = new ArrayList<Edge<V>>();
		residualIncommingEdges = new ArrayList<Edge<V>>();
		residualOutcommingEdges = new ArrayList<Edge<V>>();
	}

	public V getElement() {
		return this.element;
	}

	public void setElement(V element) {
		this.element = element;
	}

	public List<Edge<V>> getIncommingEdges() {
		return this.incommingEdges;
	}

	public void setIncommingEdges(List<Edge<V>> incommingEdges) {
		this.incommingEdges = incommingEdges;
	}

	public List<Edge<V>> getOutcommingEdges() {
		return this.outcommingEdges;
	}

	public void setOutcommingEdges(List<Edge<V>> outcommingEdge) {
		this.outcommingEdges = outcommingEdge;
	}

	public void addIncommingEdge(Edge<V> e) {
		this.incommingEdges.add(e);
	}

	public boolean addOutcommingEdge(Edge<V> e) {
		return this.outcommingEdges.add(e);
	}

	public boolean removeOutcommintEdge(Edge<V> e) {
		return this.outcommingEdges.remove(e);
	}

	public boolean removeIncommintEdge(Edge<V> e) {
		return this.incommingEdges.remove(e);
	}

	// residual

	public void addResidualIncommingEdge(Edge<V> e) {
		residualIncommingEdges.add(e);
	}

	public void addResidualOutcommingEdge(Edge<V> e) {
		residualOutcommingEdges.add(e);
	}

	public void removeResidualIncommingEdge(Edge<V> e) {
		residualIncommingEdges.remove(e);
	}

	public void removeResidualOutcommingEdge(Edge<V> e) {
		residualOutcommingEdges.remove(e);
	}

	public List<Edge<V>> getResidualIncommingEdges() {
		return this.residualIncommingEdges;
	}

	public void setResidualIncommingEdges(List<Edge<V>> residualIncommingEdges) {
		this.residualIncommingEdges = residualIncommingEdges;
	}

	public List<Edge<V>> getResidualOutcommingEdges() {
		return this.residualOutcommingEdges;
	}

	public void setResidualOutcommingEdges(List<Edge<V>> residualOutcommingEdges) {
		this.residualOutcommingEdges = residualOutcommingEdges;
	}

}
