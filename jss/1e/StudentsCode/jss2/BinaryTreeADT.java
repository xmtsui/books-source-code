//*******************************************************************
//
//      BinaryTreeADT.java		Authors:  Lewis/Chase
//
//	   Defines the interface to a binary tree data structure
//*******************************************************************

package jss2;

import java.util.Iterator;

public interface BinaryTreeADT {

   //  Should be implemented to remove the left subtree of the root
   //  of the binary tree.
   public void removeLeftSubtree();

   //  Should be implemented to remove the right subtree of the root
   //  of the binary tree.
   public void removeRightSubtree();

   //  Should be implemented to remove all elements from the binary
   //  tree.
   public void removeAllElements();

   //  Should be implemented to return true if the binary tree is
   //  empty and false otherwise.
   public boolean isEmpty();

   //  Should be implemented to return the number of elements in the
   //  binary tree.
   public int size();

   //  Should be implemented to return true if the binary tree
   //  contains an element that matches the specified element and
   //  false otherwise.
   public boolean contains (Object targetElement);

   //  Should be implemented to return a reference to the specified
   //  element if it is found in the binary tree.  Throws an
   //  exception if the specified element is not found in the tree.
   public Object find (Object targetElement);

   //  Should be implemented to return the string representation of
   //  the binary tree.
   public String toString();

   //  Should be implemented to perform an inorder traversal on the
   //  binary tree by calling an overloaded, recursive inorder method
   //  that starts with the root.
   public Iterator iteratorInOrder();

   //  Should be implemented to perform a preorder traversal on the
   //  binary tree by calling an overloaded, recursive preorder
   //  method that starts with the root.
   public Iterator iteratorPreOrder();

   //  Should be implemented to perform a postorder traversal on the
   //  binary tree by calling an overloaded, recursive postorder
   //  method that starts with the root.
   public Iterator iteratorPostOrder();

   //  Should be implemented to perform a levelorder traversal on
   //  the binary tree, using a queue.
   public Iterator iteratorLevelOrder();

}  // interface BinaryTreeADT



