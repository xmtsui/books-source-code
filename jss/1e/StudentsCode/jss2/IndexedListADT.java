//********************************************************************
//  IndexedListADT.java       Authors: Lewis/Chase
//
//  Defines the interface to an indexed list collection. Elements
//  are referenced by contiguous numeric indexes.
//********************************************************************

package jss2;

public interface IndexedListADT extends ListADT
{
   //  Inserts the specified element at the specified index
   public void add (int index, Object element);

   //  Sets the element at the specified index
   public void set (int index, Object element);

   //  Adds the specified element to the rear of this list
   public void add (Object element);

   //  Returns a reference to the element at the specified index
   public Object get (int index);

   //  Returns the index of the specified element
   public int indexOf (Object element);

   //  Returns and returns the element at the specified index
   public Object remove (int index);
}


