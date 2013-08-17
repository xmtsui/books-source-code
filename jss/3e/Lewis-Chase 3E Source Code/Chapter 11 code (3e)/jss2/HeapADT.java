/**
 * HeapADT defines the interface to a Heap.
 *
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 9/9/2008
 */

package jss2;

public interface HeapADT<T> extends BinaryTreeADT<T> 
{
   /** 
    * Adds the specified object to this heap. 
    *
    * @param obj  the element to added to this head
    */   
   public void addElement (T obj);
   
   /** 
    * Removes element with the lowest value from this heap. 
    *
    * @return  the element with the lowest value from this heap
    */
   public T removeMin();
   
   /** 
    * Returns a reference to the element with the lowest value in 
    * this heap. 
    *
    * @return  a reference to the element with the lowest value in this heap
    */
   public T findMin();
}


