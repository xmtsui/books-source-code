//*******************************************************************
//  HeapADT.java        Authors:  Lewis/Chase
//
//  Defines the interface to a Heap.
//*******************************************************************
package jss2;

public interface HeapADT<T> extends BinaryTreeADT<T> 
{
   /** Adds the specified object to this heap. */   
   public void addElement (T obj);
   
   /** Removes element with the lowest value from this heap. */
   public T removeMin();
   
   /** Returns a reference to the element with the lowest value in 
       this heap. */
   public T findMin();
}
