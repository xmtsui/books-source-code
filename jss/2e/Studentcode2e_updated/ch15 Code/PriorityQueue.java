//*******************************************************************
//  PriorityQueue.java		Authors: Lewis/Chase
//
//  Demonstrates a priority queue using a Heap.
//*******************************************************************
import jss2.*;

public class PriorityQueue<T> extends Heap<PriorityQueueNode<T>>
{
   /*****************************************************************
     Creates an empty expression tree.
   *****************************************************************/
   public PriorityQueue() 
   {
      super();
   }
   
   /*****************************************************************
     Adds the given element to this PriorityQueue.
   *****************************************************************/
   public void addElement (T object, int priority) 
   {
      PriorityQueueNode<T> node = new PriorityQueueNode<T> (object, priority);
	 super.addElement(node);
   }
   
   /*****************************************************************
     Removes the next highest priority element from this priority 
     queue and returns a reference to it.
   *****************************************************************/
   public T removeNext() 
   {
      PriorityQueueNode<T> temp = (PriorityQueueNode<T>)super.removeMin();
      return temp.getElement();
   }
}
