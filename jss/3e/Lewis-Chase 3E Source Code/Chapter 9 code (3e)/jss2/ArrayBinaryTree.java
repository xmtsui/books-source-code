/**
 * ArrayBinaryTree implements the BinaryTreeADT interface using an array
 *
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 8/19/08
 */
package jss2;
import java.util.Iterator;
import jss2.exceptions.*;

public class ArrayBinaryTree<T> implements BinaryTreeADT<T>
{
   protected int count;
   protected T[] tree; 
   private final int capacity = 50;

   /**
    * Creates an empty binary tree.
    */
   public ArrayBinaryTree() 
   {
      count = 0;
      tree = (T[]) new Object[capacity];
   }

   /**
    * Creates a binary tree with the specified element as its root.
    *
    * @param element  the element which will become the root of the new tree
    */
   public ArrayBinaryTree (T element) 
   {
      count = 1;
      tree = (T[]) new Object[capacity];
      tree[0] = element;
   }

   /**
    * Private method to expand capacity if full.
    */   
   protected void expandCapacity()
   {
      T[] temp = (T[]) new Object[tree.length * 2];

      for (int ct=0; ct < tree.length; ct++)
         temp[ct] = tree[ct];
      
      tree = temp;
   }
   
   /**
    * Returns the root element of the tree
    *
    * @return        element stored at the root
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
    * Returns the integer size of this binary tree.
    *
    * @return  the integer size of this binary tree
    */
   public int size() 
   {
      //left as programming project 
   }
   
   /**
    * Returns true if this tree contains an element that matches the
    * specified target element and false otherwise.
    *
    * @param targetElement  the element being sought in the tree
    * @return               true if the element is in this tree
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
    * @param targetElement              the element being sought in the tree
    * @return                           true if the element is in the tree
    * @throws ElementNotFoundException  if an element not found exception occurs
    */
   public T find (T targetElement) throws ElementNotFoundException 
   {
      T temp=null;
      boolean found = false;
      
      for (int ct=0; ct<count && !found; ct++)
         if (targetElement.equals(tree[ct]))
         {
	      found = true;
            temp = tree[ct];
         }

      if (!found)
         throw new ElementNotFoundException("binary tree");

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
    * @return  an iterator over the binary tree
    */
   public Iterator<T> iteratorInOrder() 
   {
      ArrayUnorderedList<T> templist = new ArrayUnorderedList<T>();
      inorder (0, templist);

      return templist.iterator();
   }

   /**
    * Performs a recursive inorder traversal.
    *
    * @param node      the node used in the traversal
    * @param templist  the temporary list used in the traversal
    */
   protected void inorder (int node, ArrayUnorderedList<T> templist) 
   {
      if (node < tree.length)
         if (tree[node] != null)
         {
            inorder (node*2+1, templist);
            templist.addToRear(tree[node]);
            inorder ((node+1)*2, templist);
         }
   }

   /**
    * Performs an preorder traversal on this binary tree by calling an
    * overloaded, recursive preorder method that starts with
    * the root.
    * 
    * @return  an iterator over the binary tree
    */
   public Iterator<T> iteratorPreOrder() 
   {
      //left as programming project 
   }

   /**
    * Performs a recursive preorder traversal.
    *
    * @param node      the node used in the traversal
    * @param templist  the temporary list used in the traversal
    */
   protected void preorder (int node, ArrayUnorderedList<T> templist) 
   {
      //left as programming project 
   }

   /**
    * Performs an postorder traversal on the binary tree by calling
    * an overloaded, recursive postorder method that starts
    * with the root.
    * 
    * @return  an iterator over the binary tree
    */
   public Iterator<T> iteratorPostOrder() 
   {
      //left as programming project 
   }

   /**
    * Performs a recursive postorder traversal.
    * 
    * @param node      the node used in the traversal
    * @param templist  the temporary list used in the traversal
    */
   protected void postorder (int node, ArrayUnorderedList<T> templist) 
   {
      //left as programming project 
   }

   /**
    * Performs a levelorder traversal on this binary tree, using a
    * tempList.
    *
    * @return  an iterator over the binary tree
    */
   public Iterator<T> iteratorLevelOrder() 
   {
      //left as programming project 
   }
}

