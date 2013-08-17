
//********************************************************************
//  DoubleList.java       Authors: Lewis/Chase
//                        Mods   : JCD
//  Represents an doubly linked implementation of a list. The front of
//  the list is kept by front. The rear by rear.This class will be
//  extended to create a specific kind of list.
//********************************************************************
package jss2;
import jss2.exceptions.*;
import java.util.Iterator;

public class DoubleList implements ListADT
{
   protected DoubleNode front,rear;
   protected int count;

   //-----------------------------------------------------------------
   //  Creates an empty list using the default capacity.
   //-----------------------------------------------------------------
   public DoubleList()
   {


   }


   //-----------------------------------------------------------------
   //  Removes and returns the last element in the list.
   //-----------------------------------------------------------------
   public Object removeLast () throws EmptyCollectionException
   {


   }

   //-----------------------------------------------------------------
   //  Removes and returns the first element in the list.
   //-----------------------------------------------------------------
   public Object removeFirst() throws EmptyCollectionException
   {


   }

   //-----------------------------------------------------------------
   //  Removes and returns the specified element.
   //-----------------------------------------------------------------
   public Object remove (Object element)
   {
      Object result;
      DoubleNode nodeptr = find (element);


      if (nodeptr == null)
         throw new ElementNotFoundException ("list");

      result = nodeptr.getElement();

      // check to see if front or rear
      if (nodeptr == front)
          result = this.removeFirst();
          
      else if (nodeptr == rear)
           result = this.removeLast();

           else
			{
             		nodeptr.getNext().setPrevious(nodeptr.getPrevious());
             		nodeptr.getPrevious().setNext(nodeptr.getNext());
				count--;
			}

      return result;
   }

   
   //-----------------------------------------------------------------
   //  Returns a reference to the element at the front of the list.
   //  The element is not removed from the list.  Throws an
   //  EmptyCollectionException if the list is empty.  
   //-----------------------------------------------------------------
   public Object first() throws EmptyCollectionException
   {


   }

   //-----------------------------------------------------------------
   //  Returns a reference to the element at the rear of the list.
   //  The element is not removed from the list.  Throws an
   //  EmptyCollectionException if the list is empty.  
   //-----------------------------------------------------------------
   public Object last() throws EmptyCollectionException
   {


   }

   //-----------------------------------------------------------------
   //  Returns true if this list contains the specified element.
   //-----------------------------------------------------------------
   public boolean contains (Object target)
   {


   }

   //-----------------------------------------------------------------
   //  Returns a reference to the specified element, or
   //  null if it is not found.
   //-----------------------------------------------------------------
   private DoubleNode find (Object target)
   {


   }

   //-----------------------------------------------------------------
   //  Returns true if this list is empty and false otherwise. 
   //-----------------------------------------------------------------
   public boolean isEmpty()
   {


   }
 
   //-----------------------------------------------------------------
   //  Returns the number of elements currently in this list.
   //-----------------------------------------------------------------
   public int size()
   {


   }

   //-----------------------------------------------------------------
   //  Returns an iterator for the elements currently in this list.
   //-----------------------------------------------------------------
   public Iterator iterator()
   {


   }

   //-----------------------------------------------------------------
   //  Returns a string representation of this list. 
   //-----------------------------------------------------------------
   public String toString()
   {


   }

}

