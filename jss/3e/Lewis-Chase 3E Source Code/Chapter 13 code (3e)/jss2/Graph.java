/**
 * Graph represents an adjacency matrix implementation of a graph.
 *
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 9/16/2008
 */


package jss2;
import jss2.exceptions.*;
import java.util.*;

public class Graph<T> implements GraphADT<T>
{
   protected final int DEFAULT_CAPACITY = 10;
   protected int numVertices;   // number of vertices in the graph
   protected boolean[][] adjMatrix;   // adjacency matrix
   protected T[] vertices;   // values of vertices

   /**
    * Creates an empty graph.
    */
   public Graph()
   {
      numVertices = 0;
      this.adjMatrix = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
      this.vertices = (T[])(new Object[DEFAULT_CAPACITY]);
   }

   /**
    * Returns a string representation of the adjacency matrix. 
    *
    * @return  a string representation of the adjacency matrix
    */
   public String toString()
   {
      //left as programming project
   }

   /**
    * Inserts an edge between two vertices of the graph.
    *
    * @param index1  the first index
    * @param index2  the second index
    */
   public void addEdge (int index1, int index2)
   {
      if (indexIsValid(index1) && indexIsValid(index2))
      {
         adjMatrix[index1][index2] = true;
         adjMatrix[index2][index1] = true;
      }
   }

   /**
    * Removes an edge between two vertices of the graph.
    *
    * @param index1  the first index
    * @param index2  the second index
    */
   public void removeEdge (int index1, int index2)
   {
            //left as programming project
   }

   /**
    * Inserts an edge between two vertices of the graph.
    *
    * @param vertex1  the first vertex
    * @param vertex2  the second vertex
    */
   public void addEdge (T vertex1, T vertex2)
   {
      addEdge (getIndex(vertex1), getIndex(vertex2));
   }

   /**
    * Removes an edge between two vertices of the graph.
    *
    * @param vertex1  the first vertex
    * @param vertex2  the second vertex
    */
   public void removeEdge (T vertex1, T vertex2)
   {
            //left as programming project

   }

   /**
    * Adds a vertex to the graph, expanding the capacity of the graph
    * if necessary.
    */
   public void addVertex ()
   {
      if (numVertices == vertices.length)
         expandCapacity();

      vertices[numVertices] = null;
      for (int i = 0; i <= numVertices; i++)
      {
         adjMatrix[numVertices][i] = false;
         adjMatrix[i][numVertices] = false;
      }      
      numVertices++;
   }

   /**
    * Adds a vertex to the graph, expanding the capacity of the graph
    * if necessary.  It also associates an object with the vertex.
    *
    * @param vertex  the vertex to add to the graph
    */
   public void addVertex (T vertex)
   {
      if (numVertices == vertices.length)
         expandCapacity();

      vertices[numVertices] = vertex;
      for (int i = 0; i <= numVertices; i++)
      {
         adjMatrix[numVertices][i] = false;
         adjMatrix[i][numVertices] = false;
      }      
      numVertices++;
   }

   /**
    * Removes a vertex at the given index from the graph.  Note that 
    * this may affect the index values of other vertices.
    *
    * @param index  the index at which the vertex is to be removed from
    */
   public void removeVertex (int index)
   {
            //left as programming project

   }

   /**
    * Removes a single vertex with the given value from the graph.  
    *
    * @param vertex  the vertex to be removed from the graph
    */
   public void removeVertex (T vertex)
   {
            //left as programming project

   }

   /**
    * Returns an iterator that performs a depth first search 
    * traversal starting at the given index.
    *
    * @param startIndex  the index to begin the search traversal from
    * @return            an iterator that performs a depth first traversal
    */
   public Iterator<T> iteratorDFS(int startIndex)
   {
      Integer x;
      boolean found;
      LinkedStack<Integer> traversalStack = new LinkedStack<Integer>();
      ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();
      boolean[] visited = new boolean[numVertices];

      if (!indexIsValid(startIndex))
         return resultList.iterator();

      for (int i = 0; i < numVertices; i++)
         visited[i] = false;
      
      traversalStack.push(new Integer(startIndex));
      resultList.addToRear(vertices[startIndex]);
      visited[startIndex] = true;
      
      while (!traversalStack.isEmpty())
      {
         x = traversalStack.peek();
         found = false;

         /** Find a vertex adjacent to x that has not been visited
             and push it on the stack */
         for (int i = 0; (i < numVertices) && !found; i++)
         {
            if (adjMatrix[x.intValue()][i] && !visited[i])
            {
               traversalStack.push(new Integer(i));
               resultList.addToRear(vertices[i]);
               visited[i] = true;
               found = true;
            }
         }
         if (!found && !traversalStack.isEmpty())
            traversalStack.pop();
      }
      return resultList.iterator();
   }

   /**
    * Returns an iterator that performs a depth first search 
    * traversal starting at the given vertex.
    *
    * @param startVertex  the vertex to begin the search from
    * @return             an iterator that performs a depth first traversal
    */
   public Iterator<T> iteratorDFS(T startVertex)
   {      
      return iteratorDFS(getIndex(startVertex));
   }

   /**
    * Returns an iterator that performs a breadth first search 
    * traversal starting at the given index.
    *
    * @param startIndex  the index to begin the search from
    * @return            an iterator that performs a breadth first traversal
    */
   public Iterator<T> iteratorBFS(int startIndex)
   {
      Integer x;
      LinkedQueue<Integer> traversalQueue = new LinkedQueue<Integer>();
      ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();

      if (!indexIsValid(startIndex))
         return resultList.iterator();

      boolean[] visited = new boolean[numVertices];
      for (int i = 0; i < numVertices; i++)
         visited[i] = false;
      
      traversalQueue.enqueue(new Integer(startIndex));
      visited[startIndex] = true;
      
      while (!traversalQueue.isEmpty())
      {
         x = traversalQueue.dequeue();
         resultList.addToRear(vertices[x.intValue()]);

         /** Find all vertices adjacent to x that have not been visited
             and queue them up */
         for (int i = 0; i < numVertices; i++)
         {
            if (adjMatrix[x.intValue()][i] && !visited[i])
            {
               traversalQueue.enqueue(new Integer(i));
               visited[i] = true;
            }
         }
      }
      return resultList.iterator();
   }

   /**
    * Returns an iterator that performs a breadth first search 
    * traversal starting at the given vertex.
    *
    * @param startVertex  the vertex to begin the search from
    * @return             an iterator that performs a breadth first traversal
    */
   public Iterator<T> iteratorBFS(T startVertex)
   {      
      return iteratorBFS(getIndex(startVertex));
   }

   /**
    * Returns an iterator that contains the indices of the vertices 
    * that are in the shortest path between the two given vertices.
    *
    * @param startIndex   the starting index
    * @param targetIndex  the the target index
    * @return             the an iterator containing the indices of the 
    * 			  of the vertices making the shortest path between
    * 			  the given indices
    */
   protected Iterator<Integer> iteratorShortestPathIndices
                              (int startIndex, int targetIndex)
   {
            //left as programming project

   }

   /**
    * Returns an iterator that contains the shortest path between
    * the two vertices.
    *
    * @param startIndex   the starting index
    * @param targetIndex  the target index
    * @return             an iterator that contains the shortest path
    * 		          between the given vertices
    */
   public Iterator<T> iteratorShortestPath(int startIndex, 
                                           int targetIndex)
   {
            //left as programming project

   }

   /**
    * Returns an iterator that contains the shortest path between
    * the two vertices.
    *
    * @param startVertex   the starting vertex
    * @param targetVertex  the target vertex
    * @return              an iterator that contains the shortest path between
    *                      the given vertices
    */
   public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex)
   {
            //left as programming project

   }

   /**
    * Returns the weight of the least weight path in the network.  
    * Returns positive infinity if no path is found.
    *
    * @param startIndex   the starting index
    * @param targetIndex  the target index
    * @return             the integer weight of the least weight path
    * 		 	  in the network
    */
   public int shortestPathLength(int startIndex, int targetIndex)
   {
            //left as programming project

   }

   /**
    * Returns the weight of the least weight path in the network.  
    * Returns positive infinity if no path is found.
    *
    * @param startVertex   the starting vertex
    * @param targetVertex  the target vertex
    * @return              the integer weight of teh least weight path
    * 			   in the network
    */
   public int shortestPathLength(T startVertex, T targetVertex)
   {
            //left as programming project

   }

   /**
    * Returns a minimum spanning tree of the graph.
    *
    * @return  a minum spanning tree of the graph
    */
   public Graph getMST()
   {
            //left as programming project
   }

   /**
    * Creates new arrays to store the contents of the graph with
    * twice the capacity.
    */
   protected void expandCapacity()
   {
      T[] largerVertices = (T[])(new Object[vertices.length*2]);
      boolean[][] largerAdjMatrix = 
            new boolean[vertices.length*2][vertices.length*2];

      for (int i = 0; i < numVertices; i++)
      {
         for (int j = 0; j < numVertices; j++)
         {
            largerAdjMatrix[i][j] = adjMatrix[i][j];
         }
         largerVertices[i] = vertices[i];
      }

      vertices = largerVertices;
      adjMatrix = largerAdjMatrix;
   }

   /**
    * Returns the number of vertices in the graph.
    *
    * @return  the integer number of vertices in the graph
    */
   public int size()
   {
            //left as programming project
   }

   /**
    * Returns true if the graph is empty and false otherwise. 
    *
    * @return  true if the graph is empty
    */
   public boolean isEmpty()
   {
            //left as programming project
   }

   /**
    * Returns true if the graph is connected and false otherwise. 
    *
    * @return  true if the graph is connected
    */
   public boolean isConnected()
   {
            //left as programming project

   }

   /**
    * Returns the index value of the first occurrence of the vertex.
    * Returns -1 if the key is not found.
    *
    * @param vertex  the vertex whose index value is being sought
    * @return        the index value of the given vertex
    */
   public int getIndex(T vertex)
   {
            //left as programming project

   }

   /**
    * Returns true if the given index is valid. 
    *
    * @param index  the index whose validity is being queried
    * @return       true if the given index is valid
    */
   protected boolean indexIsValid(int index)
   {
            //left as programming project
   }

   /**
    * Returns a copy of the vertices array.
    *
    * @return  a copy of the vertices array
    */
   public Object[] getVertices()
   {
            //left as programming project

   }
}

