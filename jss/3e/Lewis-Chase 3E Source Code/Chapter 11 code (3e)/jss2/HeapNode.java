/**
 * HeapNode creates a binary tree node with a parent pointer for use 
 * in heaps.
 * 
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 9/9/2008
 */

package jss2;

public class HeapNode<T> extends BinaryTreeNode<T>
{
   protected HeapNode<T> parent;

   /**
    * Creates a new heap node with the specified data.
    * 
    * @param obj  the data to be contained within the new heap nodes
    */
   HeapNode (T obj) 
   {
      super(obj);
      parent = null;
   }
}


