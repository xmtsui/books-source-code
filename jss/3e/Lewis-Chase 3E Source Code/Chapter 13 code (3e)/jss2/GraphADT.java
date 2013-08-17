/**
 * GraphADT defines the interface to a graph data structure.
 *
 * @author Dr. Chase
 * @author Dr. Lewis
 * @version 1.0, 9/17/2008
 */


package jss2;
import java.util.Iterator;

public interface GraphADT<T>
{
   /** 
    * Adds a vertex to this graph, associating object with vertex. 
    * 
    * @param vertex  the vertex to be added to this graph
    */
   public void addVertex (T vertex);

   /** 
    * Removes a single vertex with the given value from this graph. 
    * 
    * @param vertex  the vertex to be removed from this graph
    */
   public void removeVertex (T vertex);

   /** 
    * Inserts an edge between two vertices of this graph. 
    *
    * @param vertex1  the first vertex
    * @param vertex2  the second vertex
    */
   public void addEdge (T vertex1, T vertex2);

   /** 
    * Removes an edge between two vertices of this graph. 
    *
    * @param vertex1  the first vertex
    * @param vertex2  the second vertex
    */
   public void removeEdge (T vertex1, T vertex2);

   /** 
    * Returns a breadth first iterator starting with the given vertex. 
    * 
    * @param startVertex  the starting vertex
    * @return             a breadth first iterator beginning at the given
    *                     vertex
    */
   public Iterator iteratorBFS(T startVertex);

   /** 
    * Returns a depth first iterator starting with the given vertex. 
    *
    * @param startVertex  the starting vertex
    * @return             a depth first iterator starting at the given vertex
    */
   public Iterator iteratorDFS(T startVertex);

   /** 
    * Returns an iterator that contains the shortest path between
    * the two vertices. 
    *
    * @param startVertex   the starting vertex
    * @param targetVertex  the ending vertex
    * @return              an iterator that contains the shortest path
    *                      between the two vertices
    */
   public Iterator iteratorShortestPath(T startVertex, T targetVertex);

   /** 
    * Returns true if this graph is empty, false otherwise. 
    *
    * @return  true if this graph is empty
    */
   public boolean isEmpty();

   /** 
    * Returns true if this graph is connected, false otherwise. 
    *
    * @return  true if this graph is connected
    */
   public boolean isConnected();

   /** 
    * Returns the number of vertices in this graph. 
    *
    * @return  the integer number of vertices in this graph
    */
   public int size();

   /** 
    * Returns a string representation of the adjacency matrix. 
    *
    * @return  a string representation of the adjacency matrix
    */
   public String toString();
}
