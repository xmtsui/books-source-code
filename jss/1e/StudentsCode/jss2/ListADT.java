//********************************************************************
//  ListADT.java       Authors: Lewis/Chase
//
//  Defines the interface to a general list collection. Specific
//  types of lists will extend this interface to complete the
//  set of necessary operations.
//********************************************************************

package jss2;

import java.util.Iterator;

public interface ListADT
{
   //  Removes and returns the first element from this list
   public Object removeFirst ();

   //  Removes and returns the last element from this list
   public Object removeLast ();

   //  Removes and returns the specified element from this list
   public Object remove (Object element);

   //  Returns a reference to the first element on this list
   public Object first ();

   //  Returns a reference to the last element on this list
   public Object last ();

   //  Returns true if this list contains the specified target element
   public boolean contains (Object target);

   //  Returns true if this list contains no elements
   public boolean isEmpty();

   //  Returns the number of elements in this list
   public int size();

   //  Returns an iterator for the elements in this list
   public Iterator iterator();

   //  Returns a string representation of this list
   public String toString();
}

