/**
 * Network represents an adjacency matrix implementation of a network.
 *
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 9/17/2008
 */


package jss2;
import jss2.exceptions.*;
import java.util.*;

public class Network<T>  extends Graph<T> implements NetworkADT<T>
{
   private double[][] adjMatrix;    // adjacency matrix

   /**
    * Creates an empty network.
    */
   public Network()
   {
      numVertices = 0;
      this.adjMatrix = new double[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
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
    * @param weight  the weight of the edge
    */
   public void addEdge (int index1, int index2, double weight)
   {
           //left as programming project

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
    * Inserts an edge with the given weight between two vertices of 
    * the graph.
    *
    * @param vertex1  the first vertex
    * @param vertex2  the second vertex
    * @param weight   the weight of the edge
    */
   public void addEdge (T vertex1, T vertex2, double weight)
   {
            //left as programming project
   }

   /**
    * Inserts an edge between two vertices of the graph. Assumes a
    * weight of 0.
    *
    * @param vertex1  the first vertex
    * @param vertex2  the second vertex
    */
   public void addEdge (T vertex1, T vertex2)
   {
            //left as programming project
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
            //left as programming project

   }

   /**
    * Adds a vertex to the graph, expanding the capacity of the graph
    * if necessary. It also associates an object with the vertex.
    *
    * @param vertex  the vertex to be added
    */
   public void addVertex (T vertex)
   {
            //left as programming project

   }

   /**
    * Removes a vertex at the given index from the graph. Note that 
    * this may affect the index values of other vertices.
    *
    * @param index  the index at which the vertex should be removed
    *               from
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
    * @param startIndex  the index to begin the traversal from
    * @return            a depth first iterator from the given index
    */
   public Iterator<T> iteratorDFS(int startIndex)
   {
            //left as programming project

   }

   /**
    * Returns an iterator that performs a depth first search 
    * traversal starting at the given vertex.
    *
    * @param startVertex  the starting vertex
    * @return             a depth first iterator starting at the given
    *                     vertex
    */
   public Iterator<T> iteratorDFS(T startVertex)
   {      
            //left as programming project
   }

   /**
    * Returns an iterator that performs a breadth first search 
    * traversal starting at the given index.
    *
    * @param startIndex  the starting index
    * @return            a breadth first iterator starting at the given
    *                    index
    */
   public Iterator<T> iteratorBFS(int startIndex)
   {
            //left as programming project
   }

   /**
    * Returns an iterator that performs a breadth first search 
    * traversal starting at the given vertex.
    *
    * @param startVertex  the starting vertex
    * @return             a breadth first iterator starting at the given
    *                     vertex
    */
   public Iterator<T> iteratorBFS(T startVertex)
   {      
            //left as programming project
   }

   /**
    * Returns an iterator that contains the indices of the vertices 
    * that are in the shortest path between the two given vertices.
    *
    * @param startIndex   the starting index
    * @param targetIndex  the target index
    * @return             an iterator that contains the indices of the
    *                     vertices that are in the shortest path between
    *                     the two given vertices
    */
   protected Iterator<Integer> iteratorShortestPathIndices
                              (int startIndex, int targetIndex)
   {
            //left as programming project
   }

   /**
    * Returns the index of the the vertex that that is adjacent to
    * the vertex with the given index and also has a pathWeight equal
    * to weight.
    *
    * @param visited     an array of booleans to represent visited
    *                    pathways within the network
    * @param pathWeight  the path weight
    * @param weight      the weight
    * @return            the integer of the index that is adjacent to the
    *                    the given index and has a path weight equal to
    *                    weight
    */
   protected int getIndexOfAdjVertexWithWeightOf(boolean[] visited, 
                 double[] pathWeight, double weight)
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
    *                     between the two vertices
    */
   public Iterator<T> iteratorShortestPath(int startIndex, int targetIndex)
   {
            //left as programming project
   }

   /**
    * Returns an iterator that contains the shortest path between
    * the two vertices.
    *
    * @param startVertex   the starting index
    * @param targetVertex  the target index
    * @return              an iterator that contains the shortest path
    *                      between the two vertices
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
    * @return             the weight of the least weight path in the 
    *                     network
    */
   public double shortestPathWeight(int startIndex, int targetIndex)
   {
            //left as programming project
   }

   /**
    * Returns the weight of the least weight path in the network.  
    * Returns positive infinity if no path is found.
    *
    * @param startVertex   the starting vertex
    * @param targetVertex  the target vertex
    * @return              the weight of the least weight path in the
    *                      network
    */
   public double shortestPathWeight(T startVertex, T targetVertex)
   {
            //left as programming project
   }

   /**
    * Returns a minimum spanning tree of the network.
    *
    * @return  a minimum spanning tree of the network
    */
   public Network mstNetwork()
   {
      int x, y;
      int index;
      double weight;
      int[] edge = new int[2];
      Heap<Double> minHeap = new Heap<Double>();
      Network<T> resultGraph = new Network<T>();

      if (isEmpty() || !isConnected())
         return resultGraph;

      resultGraph.adjMatrix = new double[numVertices][numVertices];
      for (int i = 0; i < numVertices; i++)
         for (int j = 0; j < numVertices; j++)
            resultGraph.adjMatrix[i][j] = Double.POSITIVE_INFINITY;
      resultGraph.vertices = (T[])(new Object[numVertices]);      
      
      boolean[] visited = new boolean[numVertices];
      for (int i = 0; i < numVertices; i++)
         visited[i] = false;
      
      edge[0] = 0;
      resultGraph.vertices[0] = this.vertices[0];
      resultGraph.numVertices++;
      visited[0] = true;

      /** Add all edges, which are adjacent to the starting vertex,
          to the heap */
      for (int i = 0; i < numVertices; i++)
            minHeap.addElement(new Double(adjMatrix[0][i]));

      while ((resultGraph.size() < this.size()) && !minHeap.isEmpty())
      {
         /** Get the edge with the smallest weight that has exactly
             one vertex already in the resultGraph */
         do
         {
            weight = (minHeap.removeMin()).doubleValue();
            edge = getEdgeWithWeightOf(weight, visited);
         } while (!indexIsValid(edge[0]) || !indexIsValid(edge[1]));

         x = edge[0];
         y = edge[1];
         if (!visited[x])
            index = x;
         else 
            index = y;

         /** Add the new edge and vertex to the resultGraph */
         resultGraph.vertices[index] = this.vertices[index];
         visited[index] = true;
         resultGraph.numVertices++;

         resultGraph.adjMatrix[x][y] = this.adjMatrix[x][y];
         resultGraph.adjMatrix[y][x] = this.adjMatrix[y][x];

         /** Add all edges, that are adjacent to the newly added vertex,
             to the heap */
         for (int i = 0; i < numVertices; i++)
         {
            if (!visited[i] && (this.adjMatrix[i][index] < 
                                Double.POSITIVE_INFINITY))
            {
               edge[0] = index;
               edge[1] = i;
               minHeap.addElement(new Double(adjMatrix[index][i]));
            }
         }
      }
      return resultGraph;
   }

   /**
    * Returns the edge with the given weight. Exactly one vertex of 
    * the edge must have been visited.
    *
    * @param weight   the weight of the edge that is being sought
    * @param visited  an array of booleans representing visited edges
    *                 within the network
    * @return         the edge with the given weight
    */
   protected int[] getEdgeWithWeightOf(double weight, boolean[] visited)
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
      double[][] largerAdjMatrix = 
         new double[vertices.length*2][vertices.length*2];

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
}
