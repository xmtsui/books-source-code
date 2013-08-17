
//********************************************************************
//  DoubleIterator.java       Authors: Lewis/Chase
//                              Mods : Davis
//  Represents an iterator for a doubly linked list of nodes.
//********************************************************************

package jss2;

import java.util.*;

public class DoubleIterator implements Iterator
{
   private int count;  // the number of elements in the collection
   private DoubleNode current;  // the current position

   //-----------------------------------------------------------------
   //  Sets up this iterator using the specified items.
   //-----------------------------------------------------------------
   public DoubleIterator (DoubleNode list, int size)
   {
      current = list;
      count = size;
   }

   //-----------------------------------------------------------------
   //  Returns true if this iterator has at least one more element
   //  to deliver in the iteraion.
   //-----------------------------------------------------------------
   public boolean hasNext()
   {
      return (current != null);
   }

   //-----------------------------------------------------------------
   //  Returns the next element in the iteration. If there are no
   //  more elements in this itertion, a NoSuchElementException is
   //  thrown.
   //-----------------------------------------------------------------
   public Object next()
   {
      if (! hasNext())
         throw new NoSuchElementException();

      Object result = current.getElement();
      current = current.getNext();
      return result;
   }

   //-----------------------------------------------------------------
   //  The remove operation is not supported.
   //-----------------------------------------------------------------
   public void remove() throws UnsupportedOperationException
   {
      throw new UnsupportedOperationException();
   }
}

