/**
 * BinarySearchTreeADT defines the interface to a binary search tree.
 * 
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 8/19/08
 */

package jss2;

public interface BinarySearchTreeADT<T> extends BinaryTreeADT<T> 
{
/** 
    * Adds the specified element to the proper location in this tree. 
    *
    * @param element  the element to be added to this tree
    */

   public void addElement (T element);

   /** 
    * Removes and returns the specified element from this tree. 
    *
    * @param targetElement  the element to be removed from this tree
    * @return               the element removed from this tree
    */ 

   public T removeElement (T targetElement);
 
   /** 
    * Removes all occurences of the specified element from this tree. 
    *
    * @param targetElement   the element that the list will have all instances
    *                        of it removed
    */

   public void removeAllOccurrences (T targetElement);
 
   /** 
    * Removes and returns the smallest element from this tree. 
    *
    * @return  the smallest element from this tree.
    */

   public T removeMin();

   /** 
    * Removes and returns the largest element from this tree. 
    *
    * @return  the largest element from this tree
    */ 


   public T removeMax();
 
   /** 
    * Returns a reference to the smallest element in this tree. 
    * 
    * @return  a reference to the smallest element in this tree
    */ 

   public T findMin();

   /** 
    * Returns a reference to the largest element in this tree. 
    * 
    * @return  a reference to the largest element in this tree
    */

   public T findMax();

}


