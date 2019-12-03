
/**
 *
 */
package SPMST;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.crypto.spec.PSource;

public class ShortestPathEdgeListGraph<E1> implements Graph<E1> {
    private ArrayList<Vertex<E1>> vertices; //vertices in the graph
    private ArrayList<Edge> edges; //edges in a graph

    /** 
     * inner class implementing the Vertex in an EdgeListGraph.
     */


    /**
     * Inner class, implementing the Egde in an EdgeListGraph
     */
    private class EdgeListEdge implements Edge {
        private int element; //elementet indeholdt i kanten
        private Vertex<E1> endPoint1; //knuden i kantens ene ende
        private Vertex<E1> endPoint2; //knuden i kantens anden ende
 
        /**
         * creates an edge between vertices v1 and v2 containing element e
         * @param e: the object in the edge
         * @param v1: the edge's first end point
         * @param v2: the edge's first end point
         */
        public EdgeListEdge(int e, Vertex<E1> v1, Vertex<E1> v2) {
            element = e;
            endPoint1 = v1;
            endPoint2 = v2;
        }
 
        /**
         * @return the element in the edge
         */
        public int element() {
            return element;
        }
 
        /**
         * Returns an ArrayList containing the edge's two end points  
         * @return the vertices for the edge
         */
        public List<Vertex<E1>> endPoints() {
        	//Vertex<E1>[] ep = (Vertex<E1>[]) new Object[2];
        	List<Vertex<E1>> ep = new ArrayList<Vertex<E1>>(2);
            ep.add(endPoint1);
            ep.add(endPoint2);
            return ep;
        }
    }

    /**
     * Constructor.
     */
    public ShortestPathEdgeListGraph() {
        edges = new ArrayList<Edge>();
        vertices = new ArrayList<Vertex<E1>>();
    }

	/**
	 * Returns an iterator with v's adjacent vertices.
	 *
	 * @return an iterator with v's adjacent vertices. 
	 * @param  v: the vertex for which you need the adjacent vertices
	 */
    public Iterator<Vertex<E1>> adjacentVertices(Vertex<E1> v) {
        ArrayList<Vertex<E1>> naboVertices = new ArrayList<Vertex<E1>>();
        Iterator<Edge> i = edges.iterator();
        while (i.hasNext()) {
            EdgeListEdge e = (EdgeListEdge) i.next();
            Vertex<E1> v1 = e.endPoints().get(0);
            Vertex<E1> v2 = e.endPoints().get(1);
            if (v == v1)
                naboVertices.add(v2);
            if (v == v2)
                naboVertices.add(v1);
        }
        return naboVertices.iterator();
    }
    
	/**
	 * Checks whether two vertices are adjacent. 
	 * 
	 * @return true if v and w are adjacent, false otherwise 
	 * @param v: vertex that could be adjacent to w
	 * @param w: vertex that could be adjacent to v
	 */
    public boolean areAdjacent(Vertex<E1> v, Vertex<E1> w) {
        Iterator<Edge> i = edges.iterator();
        boolean found = false;
        while (!found && i.hasNext()) {
            EdgeListEdge e = (EdgeListEdge) i.next();
            Vertex<E1> v1 = e.endPoints().get(0);
            Vertex<E1> v2 = e.endPoints().get(1);
            if ((v1 == v && v2 == w) || (v1 == w && v2 == v))
                found = true;
        }
        return found;
    }

	/**
	 * A vertex' degree.
	 * 
	 * @return v's degree
	 * @param v: The vertex to be checked
	 */     
    public int degree(Vertex<E1> v) {
        return v.getNumIncidentEdges();
    }

	/**
	 * Returns an iterator with the graph's edges.
	 * 
	 * @return en an iterator with the graph's edges
	 */    
    public Iterator<Edge> edges() {
        return edges.iterator();
    }

	/**
	 * Returns an array with e's end points.
	 * 
	 * @return an array with e's end points.
	 * @param e: an edge, for which to find the end points.
	 */    
    public List<Vertex<E1>> endVertices(Edge e) {
        return ((EdgeListEdge) e).endPoints();
    }

	/**
	 * Inserts and returns a new edge between vertices v and w.
	 *
	 * @return the new edge
	 * @param v: the edge's first end point
	 * @param w: the edge's first end point
	 * @param o: the element contained in the edge
	 */
    public Edge insertEdge(Vertex<E1> v, Vertex<E1> w, int o) {
        Edge e = new EdgeListEdge(o, v, w);
        edges.add(e);
        v.incNumIncidentEdges();
        w.incNumIncidentEdges();
        return e;
    }

	/**
	 * inserts and returns a new vertex.
	 * 
	 * @return the new vertex
	 * @param o: the element contained in the vertex
	 */
    public Vertex<E1> insertVertex(E1 o) {
        Vertex<E1> v = new Vertex<E1>(o);
        vertices.add(v);
        return v;
    }

	/**
	 * @return the number of edges in the graph
	 */  
    public int numEdges() {
        return edges.size();
    }

	/**
	 * @return the number of vertices in the graph
	 */ 
    public int numVertices() {
        return vertices.size();
    }

	/**
	 * Given a vertex and an edge indcident to that vertex, returns the edge's other end point
	 * 
	 * @return the other end pint.
	 * @param v: vertex that is an end point of e
	 * @param e: an edge
	 */   
    public Vertex<E1> opposite(Vertex<E1> v, Edge e) {
        Vertex<E1> v1 = ((EdgeListEdge) e).endPoints().get(0);
        Vertex<E1> v2 = ((EdgeListEdge) e).endPoints().get(1);
        if (v == v1)
            return v2;
        else
            return v1;
    }
	/**
	 * removes edge e
	 * 
	 * @param e: the edge to be removed
	 */
    public void removeEdge(Edge e) {
        Vertex<E1> v1 = ((EdgeListEdge) e).endPoints().get(0);
        Vertex<E1> v2 = ((EdgeListEdge) e).endPoints().get(1);
        v1.decNumIncidentEdges();
        v2.decNumIncidentEdges();
        edges.remove(e);
    }

	/**
	 * removes vertex v and all its incident edges.
	 * 
	 * @param v: the vertex to be removed
	 */
    public void removeVertex(Vertex<E1> v) {
        Iterator<Edge> i = edges.iterator();
        while (i.hasNext()) {
            EdgeListEdge e = (EdgeListEdge) i.next();
            Vertex<E1> v1 = e.endPoints().get(0);
            Vertex<E1> v2 = e.endPoints().get(1);
            if (v1 == v || v2 == v) {
                v1.decNumIncidentEdges();
                v2.decNumIncidentEdges();
                i.remove();
            }
        }
        vertices.remove(v);
    }

	/**
	 * Returns an iterator containing the vertices in the graph
	 * 
	 * @return an iterator containing the vertices in the graph
	 */    
    public Iterator<Vertex<E1>> vertices() {
        return vertices.iterator();
    }

	/**
	 * Returns an iterator with v's incident edges.
	 * 
	 * @return an iterator with v's incident edges.
	 * @param v: The vertex
	 */    
    public Iterator<Edge> incidentEdges(Vertex<E1> v) {
        ArrayList<Edge> ie = new ArrayList<Edge>();
        Iterator<Edge> i = edges.iterator();
        while (i.hasNext()) {
            EdgeListEdge e = (EdgeListEdge) i.next();
            Vertex<E1> v1 = e.endPoints().get(0);
            Vertex<E1> v2 = e.endPoints().get(1);
            if (v1 == v || v2 == v)
                ie.add(e);
        }
        return ie.iterator();
    }
}
