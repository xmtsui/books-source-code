/**
 * PriorityQueueNode represents a node in a priority queue containing a
 * comparable object, order, and a priority value.
 * 
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 8/19/08
 */

public class PriorityQueueNode<T> implements Comparable<PriorityQueueNode>
{
   private static int nextorder = 0;
   private int priority;
   private int order;
   private T element;

   /**
    * Creates a new PriorityQueueNode with the specified data.
    *
    * @param obj   the element of the new priority queue node
    * @param prio  the integer priority of the new queue node
    */
   public PriorityQueueNode (T obj, int prio) 
   {
      element = obj;
      priority = prio;
      order = nextorder;
      nextorder++;
   }
   
   /**
    * Returns the element in this node.
    *
    * @return  the element contained within this node
    */
   public T getElement() 
   {
      return element;
   }
   
   /**
    * Returns the priority value for this node.
    *
    * @return  the integer priority for this node
    */
   public int getPriority() 
   {
      return priority;
   }

   /**
    * Returns the order for this node.
    *
    * @return  the integer order for this node
    */
   public int getOrder() 
   {
      return order;
   }
   
   /**
    * Returns a string representation for this node.
    *
    */
   public String toString() 
   {
      String temp = (element.toString() + priority + order);
      return temp;
   }
   
   /**
    * Returns the 1 if the current node has higher priority than 
    * the given node and -1 otherwise.
    *
    * @param obj  the node to compare to this node
    * @return     the integer result of the comparison of the obj node and this
    *             this one
    */
   public int compareTo(PriorityQueueNode obj) 
   {
     int result;
     PriorityQueueNode<T> temp = obj;
     if (priority > temp.getPriority())
        result = 1;
     else if (priority < temp.getPriority())
        result = -1;
     else if (order > temp.getOrder())
        result = 1;
     else
        result = -1;
     
     return result;
   }
}


