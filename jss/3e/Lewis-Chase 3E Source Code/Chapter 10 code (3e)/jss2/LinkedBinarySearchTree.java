/**
 * LinkedBinarySearchTree implements the BinarySearchTreeADT interface 
 * with links.
 * 
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 8/19/08
 */

package jss2;
import jss2.exceptions.*;
import jss2.*;

public class LinkedBinarySearchTree<T>  extends LinkedBinaryTree<T>
                                  implements BinarySearchTreeADT<T>
{
   /**
    * Creates an empty binary search tree.
    */
   public LinkedBinarySearchTree() 
   {
      super();
   }
   
   /**
    * Creates a binary search with the specified element as its root.
    *
    * @param element  the element that will be the root of the new binary
    *                 search tree
    */
   public LinkedBinarySearchTree (T element) 
   {
      super (element);
   }

   /**
    * Adds the specified object to the binary search tree in the
    * appropriate position according to its key value.  Note that
    * equal elements are added to the right.
    *
    * @param element  the element to be added to the binary search tree
    */
   public void addElement (T element) 
   {
      BinaryTreeNode<T> temp = new BinaryTreeNode<T> (element);
      Comparable<T> comparableElement = (Comparable<T>)element;

      if (isEmpty())
         root = temp;
      else 
      {
         BinaryTreeNode<T> current = root;
         boolean added = false;

         while (!added) 
         {
            if (comparableElement.compareTo(current.element) < 0)
            {
               if (current.left == null) 
               {
                  current.left = temp;
                  added = true;
               } 
               else
                  current = current.left;
            }
            else
            {
               if (current.right == null) 
               {
                  current.right = temp;
                  added = true;
               } 
               else
                  current = current.right;
            }
         }
      }
      
      count++;
   }
   
   /**
    * Removes the first element that matches the specified target
    * element from the binary search tree and returns a reference to
    * it.  Throws a ElementNotFoundException if the specified target
    * element is not found in the binary search tree.
    *
    * @param targetElement              the element being sought in the binary 
    *                                   search tree
    * @throws ElementNotFoundException  if an element not found exception occurs
    */
   public T removeElement (T targetElement)
                          throws ElementNotFoundException 
   {
      T result = null;

      if (!isEmpty())
      {
         if (((Comparable)targetElement).equals(root.element)) 
         {
            result =  root.element;
            root = replacement (root);
            count--;
         }
         else 
         {
            BinaryTreeNode<T> current, parent = root;
            boolean found = false;

            if (((Comparable)targetElement).compareTo(root.element) < 0)
               current = root.left;
            else
               current = root.right;

            while (current != null && !found) 
            {
               if (targetElement.equals(current.element)) 
               {
                  found = true;
                  count--;
                  result =  current.element;
          
                  if (current == parent.left)
                  {
                     parent.left = replacement (current);
                  }
                  else
                  {
                     parent.right = replacement (current);
                  }
               }
               else 
               {
                  parent = current;
         
                  if (((Comparable)targetElement).compareTo(current.element) < 0)
                     current = current.left;
                  else
                     current = current.right;
               }
            } //while
            
            if (!found)
               throw new ElementNotFoundException("binary search tree");
         }
      } // end outer if

      return result;
   }

   /**
    * Removes elements that match the specified target element from 
    * the binary search tree. Throws a ElementNotFoundException if 
    * the sepcified target element is not found in this tree.
    *
    * @param targetElement              the element being sought in the binary \
    *                                   search tree
    * @throws ElementNotFoundException  if an element not found exception occurs
    */
   public void removeAllOccurrences (T targetElement)
                                    throws ElementNotFoundException 
   {
      removeElement(targetElement);
      
      try
      {
         while (contains( (T) targetElement))
            removeElement(targetElement);
      }
      
      catch (Exception ElementNotFoundException)
      {
      }
   }

   /**
    * Removes the node with the least value from the binary search
    * tree and returns a reference to its element.  Throws an
    * EmptyBinarySearchTreeException if this tree is empty. 
    *
    * @return                           a reference to the node with the least 
    *                                   value
    * @throws EmptyCollectionException  if an empty collection exception occurs
    */
   public T removeMin() throws EmptyCollectionException 
   {
      T result = null;

      if (isEmpty())
         throw new EmptyCollectionException ("binary search tree");
      else 
      {
         if (root.left == null) 
         {
            result = root.element;
            root = root.right;
         }
         else 
         {
            BinaryTreeNode<T> parent = root;
            BinaryTreeNode<T> current = root.left;
            while (current.left != null) 
            {
               parent = current;
               current = current.left;
            }
            result =  current.element;
            parent.left = current.right;
         }

         count--;
      }
 
      return result;
   }

   /**
    * Removes the node with the highest value from the binary
    * search tree and returns a reference to its element.  Throws an
    * EmptyBinarySearchTreeException if this tree is empty. 
    *
    * @return  a reference to the node with the highest value
    * @throws EmptyCollectionException  if an empty collection exception occurs
    */
   public T removeMax() throws EmptyCollectionException 
   {
      //left as programming project
   }


   /**
    * Returns the element with the least value in the binary search
    * tree. It does not remove the node from the binary search tree. 
    * Throws an EmptyBinarySearchTreeException if this tree is empty.
    *
    * @return  the element with the least value
    * @throws EmptyCollectionException  if an empty collection exception occurs
    */
   public T findMin() throws EmptyCollectionException 
   {
      //left as programming project 
   }

   /**
    * Returns the element with the highest value in the binary
    * search tree.  It does not remove the node from the binary
    * search tree.  Throws an EmptyBinarySearchTreeException if this 
    * tree is empty.
    *
    * @return  the element with the highest value
    * @throws EmptyCollectionException  if an empty collection exception occurs
    */
   public T findMax() throws EmptyCollectionException 
   {
      //left as programming project 
   }

   /**
    * Returns a reference to the specified target element if it is
    * found in the binary tree.  Throws a NoSuchElementException if
    * the specified target element is not found in this tree.
    *
    * @param targetElement  the element being sough in the binary tree
    * @throws ElementNotFoundException  if an element not found exception occurs
    */
   public T find (T targetElement) throws ElementNotFoundException 
   {
      //left as programming project 
   }

   /**
    * Returns a reference to the specified target element if it is
    * found in this tree.  
    *
    * @param targetElement  the element being sought in the tree
    * @param next           the tree node to being searching on
    */
   private BinaryTreeNode<T> findAgain (T targetElement, BinaryTreeNode<T> next) 
   {
      //left as programming project 
   }

   /**
    * Returns a reference to a node that will replace the one
    * specified for removal.  In the case where the removed node has 

    * two children, the inorder successor is used as its replacement.
    *
    * @param node  the node to be removeed
    * @return      a reference to the replacing node
    */
   protected BinaryTreeNode<T> replacement (BinaryTreeNode<T> node) 
   {
      BinaryTreeNode<T> result = null;

      if ((node.left == null)&&(node.right==null))
         result = null;
      
      else if ((node.left != null)&&(node.right==null))
         result = node.left;
      
      else if ((node.left == null)&&(node.right != null))
         result = node.right;
      
      else
      {
         BinaryTreeNode<T> current = node.right;
         BinaryTreeNode<T> parent = node;
         
         while (current.left != null)
         {
            parent = current;
            current = current.left;
         }
         
         if (node.right == current)
            current.left = node.left;
         
         else
         {
            parent.left = current.right;
            current.right = node.right;
            current.left = node.left;
         }
         
         result = current;
      }
      
      return result;
   }
}

