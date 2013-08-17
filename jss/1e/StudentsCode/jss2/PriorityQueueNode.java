//*******************************************************************
//
// PriorityQueueNode.java			Authors: Lewis/Chase
//
//*******************************************************************
package jss2;

public class PriorityQueueNode implements Comparable
{

   private static int nextorder = 0;
   private int priority;
   private int order;
   private Comparable element;

   //================================================================
   //  Creates a new PriorityQueueNode with the specified data.
   //================================================================
   public PriorityQueueNode (Comparable obj, int prio) 
   {
      element = obj;
      priority = prio;
      order = nextorder;
      nextorder++;
   }  // constructor PriorityQueueNode


   //================================================================
   // method getElement() returns the element
   //================================================================
   public Comparable getElement() 
   {

 	return element;

   }  // method getElement

   //================================================================
   // method getPriority() returns the priority
   //================================================================
   public int getPriority() 
   {

 	return priority;

   }  // method getPriority

   //================================================================
   // method getOrder() returns the order
   //================================================================
   public int getOrder() 
   {

 	return order;

   }  // method getOrder


   //================================================================
   // method compareTo() returns the 1 if the current node has higher
   // priority than the given node, -1 otherwise
   //================================================================
   public int compareTo(Object obj) 
   {
     int result;
     PriorityQueueNode temp = (PriorityQueueNode)obj;
     if (priority > temp.getPriority())
        result = 1;
     else if (priority < temp.getPriority())
        result = -1;
     else if (order > temp.getOrder())
        result = 1;
     else
        result = -1;
	return result;

   }  // method compareTo
   

}  // class PriorityQueueNode

