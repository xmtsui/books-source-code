/**
 * LinkedBinaryTree implements the BinaryTreeADT interface
 * 
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 8/19/08
 */

package jss2;
import java.util.Iterator;
import jss2.exceptions.*;

public class LinkedBinaryTree<T> implements BinaryTreeADT<T>
{
   protected int count;
   protected BinaryTreeNode<T> root; 

   /**
    * Creates an empty binary tree.
    */
   public LinkedBinaryTree() 
   {
      count = 0;
      root = null;
   }

   /**
    * Creates a binary tree with the specified element as its root.
    *
    * @param element  the element that will become the root of the new binary
    *                 tree
    */
   public LinkedBinaryTree (T element) 
   {
      count = 1;
      root = new BinaryTreeNode<T> (element);
   }

   /**
    * Returns a reference to the element at the root
    *
    * @return                           a reference to the specified target
    * @throws EmptyCollectionException  if the tree is empty
    */
   public T getRoot() throws EmptyCollectionException
   {
      //left as programming project 
   }

   /**
    * Returns true if this binary tree is empty and false otherwise.
    *
    * @return  true if this binary tree is empty
    */
   public boolean isEmpty() 
   {
      //left as programming project 
   }

   /**
    * Returns the integer size of this tree.
    *
    * @return  the integer size of this tree
    */
   public int size() 
   {
      //left as programming project 
   }
   
   /**
    * Returns true if this tree contains an element that matches the
    * specified target element and false otherwise.
    *
    * @param targetElement              the element being sought in this tree
    * @return                           true if the element in is this tree
    * @throws ElementNotFoundException  if an element not found exception occurs
    */
   public boolean contains (T targetElement) 
   {
      //left as programming project 
   }
   
   /**
    * Returns a reference to the specified target element if it is
    * found in this binary tree.  Throws a NoSuchElementException if
    * the specified target element is not found in the binary tree.
    *
    * @param targetElement              the element being sought in this tree
    * @return                           a reference to the specified target
    * @throws ElementNotFoundException  if an element not found exception occurs
    */
   public T find(T targetElement) throws ElementNotFoundException
   {
      BinaryTreeNode<T> current = findAgain( targetElement, root );
      
      if( current == null )
         throw new ElementNotFoundException("binary tree");
      
      return (current.element);
   }

   /**
    * Returns a reference to the specified target element if it is
    * found in this binary tree.
    *
    * @param targetElement  the element being sought in this tree
    * @param next           the element to begin searching from
    */
   private BinaryTreeNode<T> findAgain(T targetElement, 
                                       BinaryTreeNode<T> next)
   {
      if (next == null)
         return null;
      
      if (next.element.equals(targetElement))
         return next;
      
      BinaryTreeNode<T> temp = findAgain(targetElement, next.left);
      
      if (temp == null)
         temp = findAgain(targetElement, next.right);
      
      return temp;
   }
   
   /**
    * Returns a string representation of this binary tree.
    *
    * @return  a string representation of this binary tree
    */
   public String toString() 
   {
      //left as programming project 
   }

   /**
    * Performs an inorder traversal on this binary tree by calling an
    * overloaded, recursive inorder method that starts with
    * the root.
    *
    * @return  an in order iterator over this binary tree
    */
   public Iterator<T> iteratorInOrder()
   {
      ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
      inorder (root, tempList);
      
      return tempList.iterator();
   }

   /**
    * Performs a recursive inorder traversal.
    *
    * @param node      the node to be used as the root for this traversal
    * @param tempList  the temporary list for use in this traversal
    */
   protected void inorder (BinaryTreeNode<T> node, 
                           ArrayUnorderedList<T> tempList) 
   {
      if (node != null)
      {
         inorder (node.left, tempList);
         tempList.addToRear(node.element);
         inorder (node.right, tempList);
      }
   }

   /**
    * Performs an preorder traversal on this binary tree by calling 
    * an overloaded, recursive preorder method that starts with
    * the root.
    *
    * @return  an pre order iterator over this tree
    */
   public Iterator<T> iteratorPreOrder() 
   {
      //left as programming project 
   }

   /**
    * Performs a recursive preorder traversal.
    *
    * @param node  the node to be used as the root for this traversal
    * @param tempList  the temporary list for use in this traversal
    */
   protected void preorder (BinaryTreeNode<T> node, 
                            ArrayUnorderedList<T> tempList) 
   {
      //left as programming project 
   }

   /**
    * Performs an postorder traversal on this binary tree by calling
    * an overloaded, recursive postorder method that starts
    * with the root.
    *
    * @return  a post order iterator over this tree
    */
   public Iterator<T> iteratorPostOrder() 
   {
      //left as programming project 
   }

   /**
    * Performs a recursive postorder traversal.
    *
    * @param node      the node to be used as the root for this traversal
    * @param tempList  the temporary list for use in this traversal
    */
   protected void postorder (BinaryTreeNode<T> node, 
                             ArrayUnorderedList<T> tempList) 
   {
      //left as programming project 
   }

   /**
    * Performs a levelorder traversal on this binary tree, using a
    * templist.
    *
    * @return  a levelorder iterator over this binary tree
    */
   public Iterator<T> iteratorLevelOrder() 
   {
      //left as programming project 
   }
}

