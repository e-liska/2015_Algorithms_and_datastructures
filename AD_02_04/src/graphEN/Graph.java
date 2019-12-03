package graphEN;

import java.util.Iterator;
import java.util.List;

/**
 * Abstract data type: Graph
 */
public interface Graph<E1,E2> {
	/**
	 * Returns an iterator with v's adjacent vertices.
	 *
	 * @return an iterator with v's adjacent vertices. 
	 * @param  v: the vertex for which you need the adjacent vertices
	 */
	public Iterator<Vertex<E1>> adjacentVertices(Vertex<E1> v);
	
	/**
	 * Checks whether two vertices are adjacent. 
	 * 
	 * @return true if v and w are adjacent, false otherwise 
	 * @param v: vertex that could be adjacent to w
	 * @param w: vertex that could be adjacent to v
	 */
	public boolean areAdjacent(Vertex<E1> v, Vertex<E1> w);
	
	/**
	 * A vertex' degree.
	 * 
	 * @return v's degree
	 * @param v: The vertex to be checked
	 */     
	public int degree(Vertex<E1> v);
	
	/**
	 * Returns an iterator with the graph's edges.
	 * 
	 * @return en an iterator with the graph's edges
	 */    
	public Iterator<Edge<E2>> edges();
	
	/**
	 * Returns an array with e's end points.
	 * 
	 * @return an array with e's end points.
	 * @param e: an edge, for which to find the end points.
	 */    
	public List<Vertex<E1>> endVertices(Edge<E2> e);
	
	/**
	 * Returns an iterator with v's incident edges.
	 * 
	 * @return an iterator with v's incident edges.
	 * @param v: The vertex
	 */     
	public Iterator<Edge<E2>> incidentEdges(Vertex<E1> v);
	
	/**
	 * Inserts and returns a new edge between vertices v and w.
	 *
	 * @return the new edge
	 * @param v: the edge's first end point
	 * @param w: the edge's first end point
	 * @param o: the element contained in the edge
	 */
	public Edge<E2> insertEdge(Vertex<E1> v, Vertex<E1> w, E2 o);
	
	/**
	 * inserts and returns a new vertex.
	 * 
	 * @return the new vertex
	 * @param o: the element contained in the vertex
	 */
	public Vertex<E1> insertVertex(E1 o);
	
	/**
	 * @return the number of edges in the graph
	 */     
	public int numEdges();
	
	/**
	 * @return the number of vertices in the graph
	 */    
	public int numVertices();
	
	/**
	 * Given a vertex and an edge indcident to that vertex, returns the edge's other end point
	 * 
	 * @return the other end pint.
	 * @param v: vertex that is an end point of e
	 * @param e: an edge
	 */    
	public Vertex<E1> opposite(Vertex<E1> v, Edge<E2> e);
	
	/**
	 * removes edge e
	 * 
	 * @param e: the edge to be removed
	 */
	public void removeEdge(Edge<E2> e);
	
	/**
	 * removes vertex v and all its incident edges.
	 * 
	 * @param v: the vertex to be removed
	 */
	public void removeVertex(Vertex<E1> v);
	
	/**
	 * Returns an iterator containing the vertices in the graph
	 * 
	 * @return an iterator containing the vertices in the graph
	 */    
	public Iterator<Vertex<E1>> vertices();
}
