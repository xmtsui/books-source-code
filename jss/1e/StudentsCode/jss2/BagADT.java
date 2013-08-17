
//********************************************************************
//  BagADT.java       Authors: Lewis/Chase
//
//  Defines the interface to a bag data structure.
//********************************************************************

package jss2;

import java.util.Iterator;

public interface BagADT
{
   //  Adds one element to this bag
   public void add (Object element);

   //  Removes and returns a random element from this bag
   public Object removeRandom ();

   //  Removes and returns the specified element from this bag
   public Object remove (Object element);

   //  Returns the union of this bag and the parameter
   public BagADT union (BagADT set);

   //  Returns true if this bag contains the parameter
   public boolean contains (Object target);

   //  Returns true if this bag and the parameter contain exactly
   //  the same elements
   public boolean equals (BagADT bag);

   //  Returns true if this set contains no elements
   public boolean isEmpty();

   //  Returns the number of elements in this set
   public int size();

   //  Returns an iterator for the elements in this bag
   public Iterator iterator();

   //  Returns a string representation of this bag
   public String toString();
}

