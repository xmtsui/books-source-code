//********************************************************************
//  LinkedQueue.java       Authors: Lewis/Chase
//
//  Represents a linked implementation of a queue.
//********************************************************************

package jss2;

import jss2.exceptions.*;

public class LinkedQueue implements QueueADT
{
   private int count;
   private LinearNode front, rear;

   //-----------------------------------------------------------------
   //  Creates an empty queue.
   //-----------------------------------------------------------------
   public LinkedQueue()
   {
      count = 0;
      front = rear = null;
   }

   //-----------------------------------------------------------------
   //  Adds the specified element to the rear of the queue.
   //-----------------------------------------------------------------
   public void enqueue (Object element)
   {
      LinearNode node = new LinearNode(element);

      if (isEmpty())
         front = node;
      else
         rear.setNext (node);

      rear = node;
      count++;
   }

   //-----------------------------------------------------------------
   //  Removes the element at the front of the queue and returns a
   //  reference to it. Throws an EmptyCollectionException if the
   //  queue is empty.
   //-----------------------------------------------------------------
   public Object dequeue() throws EmptyCollectionException
   {
      if (isEmpty())
         throw new EmptyCollectionException ("queue");

      Object result = front.getElement();
      front = front.getNext();
      count--;

      if (isEmpty())
         rear = null;

      return result;
   }
   
   //-----------------------------------------------------------------
   //  Returns a reference to the element at the front of the queue.
   //  The element is not removed from the queue.  Throws an
   //  EmptyCollectionException if the queue is empty.  
   //-----------------------------------------------------------------
   public Object first() throws EmptyCollectionException
   {


   }

   //-----------------------------------------------------------------
   //  Returns true if this queue is empty and false otherwise. 
   //-----------------------------------------------------------------
   public boolean isEmpty()
   {


   }
 
   //-----------------------------------------------------------------
   //  Returns the number of elements currently in this queue.
   //-----------------------------------------------------------------
   public int size()
   {


   }

   //-----------------------------------------------------------------
   //  Returns a string representation of this queue. 
   //-----------------------------------------------------------------
   public String toString()
   {


   }
}

