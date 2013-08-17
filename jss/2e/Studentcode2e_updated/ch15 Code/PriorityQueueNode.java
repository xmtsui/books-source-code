//*******************************************************************
// PriorityQueueNode.java			Authors: Lewis/Chase
//
//  Represents a node in a priority queue containing a comparable
//  object, order, and a priority value.
//*******************************************************************

public class PriorityQueueNode<T> implements Comparable<PriorityQueueNode>
{
   private static int nextorder = 0;
   private int priority;
   private int order;
   private T element;

   /*****************************************************************
     Creates a new PriorityQueueNode with the specified data.
   *****************************************************************/
   public PriorityQueueNode (T obj, int prio) 
   {
      element = obj;
      priority = prio;
      order = nextorder;
      nextorder++;
   }
   
   /*****************************************************************
     Returns the element in this node.
   *****************************************************************/
   public T getElement() 
   {
      return element;
   }
   
   /*****************************************************************
     Returns the priority value for this node.
   *****************************************************************/
   public int getPriority() 
   {
      return priority;
   }

   /*****************************************************************
     Returns the order for this node.
   *****************************************************************/
   public int getOrder() 
   {
      return order;
   }
   
   /*****************************************************************
     Returns the 1 if the current node has higher priority than 
     the given node and -1 otherwise.
   *****************************************************************/
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
