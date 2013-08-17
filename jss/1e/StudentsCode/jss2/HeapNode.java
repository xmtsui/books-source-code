//*******************************************************************
//
//  HeapNode.java			Authors:  Lewis/Chase
//
//  Creates a binary tree node with a parent pointer for use in heaps.
//*******************************************************************
package jss2;

public class HeapNode extends BinaryTreeNode
{


   protected HeapNode parent;

   //================================================================
   //  Creates a new heap node with the specified data.
   //================================================================
   HeapNode (Comparable obj) 
   {
      super(obj);
      parent = null;
   }  // constructor HeapNode


}  // class HeapNode

