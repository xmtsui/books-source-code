//*******************************************************************
//
// PriorityQueue.java		Authors: Lewis/Chase
//
//*******************************************************************

package jss2;

public class PriorityQueue extends Heap
{
   
   //================================================================
   //  Creates an empty expression tree.
   //================================================================
   public PriorityQueue() 
   {
      super();
   }  // constructor PriorityQueue


   //================================================================
   //  adds the given element to the PriorityQueue
   //================================================================
   public void addElement (Comparable object, int priority) 
   {
      PriorityQueueNode node = new PriorityQueueNode (object, priority);
	 super.addElement(node);
 
   }  // constructor PriorityQueue
   

   //================================================================
   //  Removes the next highest priority element from the priority queue
   //  and returns a reference to it.
   //================================================================
   public Comparable removeNext() 
   {
      PriorityQueueNode temp = (PriorityQueueNode)super.removeMin();
      return temp.getElement();
   }  // method removeNext



}  // class PriorityQueue

