/**
 * ArrayBinarySearchTree implements a binary search tree using an array.
 * 
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 8/19/08
 */

package jss2;
import java.util.Iterator;
import jss2.exceptions.*;
import jss2.*;

public class ArrayBinarySearchTree<T>  extends ArrayBinaryTree<T> 
                                implements BinarySearchTreeADT<T>
{
   protected int height;
   protected int maxIndex;

   /**
    * Creates an empty binary search tree.
    */
   public ArrayBinarySearchTree() 
   {
      super();
      height = 0;
      maxIndex = -1;
   }

   /**
    * Creates a binary search with the specified element as its
    * root.
    *
    * @param element  the element that will become the root of the new tree
    */
   public ArrayBinarySearchTree (T element) 
   {
      super(element);
      height = 1;
      maxIndex = 0;
   }

   /**
    * Adds the specified object to this binary search tree in the
    * appropriate position according to its key value.  Note that
    * equal elements are added to the right.  Also note that the
    * index of the left child of the current index can be found by
    * doubling the current index and adding 1.  Finding the index
    * of the right child can be calculated by doubling the current
    * index and adding 2.
    *
    * @param element  the element to be added to the search tree
    */
   public void addElement (T element) 
   {
      if (tree.length < maxIndex*2+3)
         expandCapacity();

      Comparable<T> tempelement = (Comparable<T>)element;

      if (isEmpty())
      {
         tree[0] = element;
         maxIndex = 0;
      }
      else 
      {
         boolean added = false;
         int currentIndex = 0;

         while (!added) 
         {
            if (tempelement.compareTo((tree[currentIndex]) ) < 0) 
            {
               /** go left */
               if (tree[currentIndex*2+1] == null)
               {
                  tree[currentIndex*2+1] = element;
                  added = true;
                  if (currentIndex*2+1 > maxIndex)
                     maxIndex = currentIndex*2+1;
               }
               else
                  currentIndex = currentIndex*2+1;
            }


            else
            {
               /** go right */
               if (tree[currentIndex*2+2] == null) 
               {
                  tree[currentIndex*2+2] = element;
                  added = true;
                  if (currentIndex*2+2 > maxIndex)
                     maxIndex = currentIndex*2+2;
               }
               else
                  currentIndex = currentIndex*2+2;
            }
         }
      }

      height = (int)(Math.log(maxIndex + 1) / Math.log(2)) + 1;
      count++;
   }

   /**
    * Removes the first element that matches the specified target
    * element from this binary search tree and returns a reference to
    * it.  Throws an ElementNotFoundException if the specified target
    * element is not found in the binary search tree.
    *
    * @param targetElement              the element to be removed from the tree
    * @return                           a reference to the removed element
    * @throws ElementNotFoundException  if an element not found exception occurs
    */
   public T removeElement (T targetElement) 
                          throws ElementNotFoundException 
   {
      T result = null;
      boolean found = false;

      if (isEmpty())
         throw new ElementNotFoundException("binary search tree");

      Comparable<T> tempElement = (Comparable<T>)targetElement;

      int targetIndex = findIndex (tempElement, 0);

      result = tree[targetIndex] ;               
      replace(targetIndex);
      count--;

      int temp = maxIndex;
      maxIndex = -1;
      for (int i = 0; i <= temp; i++)
      {
         if (tree[i] != null)
            maxIndex = i;
      }
      
      height = (int)(Math.log(maxIndex + 1) / Math.log(2)) + 1;
      
      return result;
   }




   /**
    * Returns the maximum index value in this tree.
    *
    * @return  the integer representation of the maximum value in this tree
    */
   public int getMaxIndex()
   {
      //left as programming project
   }

   /**
    * Returns the height of this tree.
    *
    * @return the integer height of the tree
    */
   public int getHeight()
   {
      //left as programming project 
   }


   /**
    * Removes elements that match the specified target
    * element from this binary search tree. 
    * Throws an ElementNotFoundException if the sepcified target
    * element is not found in the binary search tree.
    *
    * @param targetElement              the element to be removed
    * @throws ElementNotFoundException  if an element not found exception occurs
    */
   public void removeAllOccurrences (T targetElement) throws

          ElementNotFoundException 
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
    * Removes the node with the least value from this binary search
    * tree and returns a reference to its element.  Throws an
    * EmptyBinarySearchTreeException if the binary search tree is
    * empty. 
    *
    * @return  a reference to the node with the least value in this tree
    * @throws EmptyCollectionException  if an empty collection exception occurs
    */
   public T removeMin() throws EmptyCollectionException 
   {
      T result = null;

      if (isEmpty())
           throw new EmptyCollectionException ("binary search tree");
      else 
      {
         int currentIndex = 1;
         int previousIndex = 0;
         while (tree[currentIndex] != null && currentIndex <= tree.length) 

         {
            previousIndex = currentIndex;
            currentIndex = currentIndex * 2 + 1;
         }
         result = tree[previousIndex] ;
         replace(previousIndex);
      }

      count--;

      return result;
   }

   /**
    * Removes the node with the highest value from this binary
    * search tree and returns a reference to its element.  Throws an
    * EmptyBinarySearchTreeException if the binary search tree is
    * empty. 
    *
    * @return  the node with highest value in this tree
    * @throws EmptyCollectionException  if an empty collections exception occurs
    */
   public T removeMax() throws EmptyCollectionException 
   {
      //left as programming project 
   }

   /**
    * Returns the element with the least value in this binary search
    * tree.  It does not remove the node from the binary search
    * tree.  Throws an EmptyBinarySearchTreeException if the binary
    * search tree is empty.
    *
    * @return                           the element with the least value in 
    *                                   this binary search tree
    * @throws EmptyCollectionException  if an empty collection exception occurs
    */
   public T findMin() throws EmptyCollectionException 
   {
      //left as programming project 
   }

   /**
    * Returns the element with the highest value in this binary
    * search tree.  It does not remove the node from the binary
    * search tree.  Throws an EmptyBinarySearchTreeException if the 
    * binary search tree is empty.
    *
    * @return                           the element with the highest value in 
    *                                   this binary search tree
    * @throws EmptyCollectionException  if an empty collection exception occurs
    */

   public T findMax() throws EmptyCollectionException 
   {
      //left as programming project 
   }  

   /**
    * Removes the node specified for removal and shifts the tree
    * array accordingly.
    *
    * @param targetIndex  the node to be removed
    */
   protected void replace (int targetIndex) 
   {
      int currentIndex, parentIndex, temp, oldIndex, newIndex;
      ArrayUnorderedList<Integer> oldlist = new ArrayUnorderedList<Integer>();
      ArrayUnorderedList<Integer> newlist = new ArrayUnorderedList<Integer>();
      ArrayUnorderedList<Integer> templist = new ArrayUnorderedList<Integer>();
      Iterator<Integer> oldIt, newIt;

      /** if target node has no children */

      if ((targetIndex*2+1 >= tree.length) || (targetIndex*2+2 >= tree.length))
         tree[targetIndex] = null;

      /** if target node has no children */
      else if ((tree[targetIndex*2+1] == null) && (tree[targetIndex*2+2] == null))
         tree[targetIndex] = null;
      
      /** if target node only has a left child */
      else if ((tree[targetIndex*2+1] != null) && (tree[targetIndex*2+2] == null))
      {
         /** fill newlist with indices of nodes that will replace 
             the corresponding indices in oldlist */
         currentIndex = targetIndex*2+1;
         templist.addToRear(new Integer(currentIndex));
         while (!templist.isEmpty())
         {
            currentIndex = ((Integer)templist.removeFirst()).intValue();
            newlist.addToRear(new Integer(currentIndex));
            if ((currentIndex*2+2) <= (Math.pow(2,height)-2))
            {
               templist.addToRear(new Integer(currentIndex*2+1));
               templist.addToRear(new Integer(currentIndex*2+2));
            }
         }
         
         /** fill oldlist */
         currentIndex = targetIndex;
         templist.addToRear(new Integer(currentIndex));
         while (!templist.isEmpty())
         {
            currentIndex = ((Integer)templist.removeFirst()).intValue();
            oldlist.addToRear(new Integer(currentIndex));
            if ((currentIndex*2+2) <= (Math.pow(2,height)-2))
            {
               templist.addToRear(new Integer(currentIndex*2+1));
               templist.addToRear(new Integer(currentIndex*2+2));
            }
         }
         
         /** do replacement */
         oldIt = oldlist.iterator();
         newIt = newlist.iterator();
         while (newIt.hasNext())
         {
            oldIndex = oldIt.next();
            newIndex = newIt.next();
            tree[oldIndex] = tree[newIndex];
            tree[newIndex] = null;
         }         
      }

      /** if target node only has a right child */
      else if ((tree[targetIndex*2+1] == null) && (tree[targetIndex*2+2] != null))
      {
         /** fill newlist with indices of nodes that will replace 
             the corresponding indices in oldlist */
         currentIndex = targetIndex*2+2;
         templist.addToRear(new Integer(currentIndex));
         while (!templist.isEmpty())
         {
            currentIndex = ((Integer)templist.removeFirst()).intValue();
            newlist.addToRear(new Integer(currentIndex));
            if ((currentIndex*2+2) <= (Math.pow(2,height)-2))
            {
               templist.addToRear(new Integer(currentIndex*2+1));
               templist.addToRear(new Integer(currentIndex*2+2));
            }
         }
         
         /** fill oldlist */
         currentIndex = targetIndex;
         templist.addToRear(new Integer(currentIndex));
         while (!templist.isEmpty())
         {
            currentIndex = ((Integer)templist.removeFirst()).intValue();
            oldlist.addToRear(new Integer(currentIndex));
            if ((currentIndex*2+2) <= (Math.pow(2,height)-2))
            {
               templist.addToRear(new Integer(currentIndex*2+1));
               templist.addToRear(new Integer(currentIndex*2+2));
            }
         }
         
         /** do replacement */
         oldIt = oldlist.iterator();
         newIt = newlist.iterator();
         while (newIt.hasNext())
         {
            oldIndex = oldIt.next();

            newIndex = newIt.next();
            tree[oldIndex] = tree[newIndex];
            tree[newIndex] = null;
         }         
      }

      /** if target node has two children */
      else
      {
         currentIndex = targetIndex*2+2;
         
         while (tree[currentIndex*2+1] != null)
            currentIndex = currentIndex*2+1;
         
         tree[targetIndex] = tree[currentIndex];

         /** the index of the root of the subtree to be replaced */
         int currentRoot = currentIndex;  
         
         /** if currentIndex has a right child */
         if (tree[currentRoot*2+2] != null)
         {
            /** fill newlist with indices of nodes that will replace 
                the corresponding indices in oldlist */
            currentIndex = currentRoot*2+2;
            templist.addToRear(new Integer(currentIndex));
            while (!templist.isEmpty())
            {
               currentIndex = ((Integer)templist.removeFirst()).intValue();
               newlist.addToRear(new Integer(currentIndex));
               if ((currentIndex*2+2) <= (Math.pow(2,height)-2))
               {
                  templist.addToRear(new Integer(currentIndex*2+1));
                  templist.addToRear(new Integer(currentIndex*2+2));
               }
            }
               
            /** fill oldlist */
            currentIndex = currentRoot;
            templist.addToRear(new Integer(currentIndex));
            while (!templist.isEmpty())
            {
               currentIndex = ((Integer)templist.removeFirst()).intValue();
               oldlist.addToRear(new Integer(currentIndex));
               if ((currentIndex*2+2) <= (Math.pow(2,height)-2))
               {
                  templist.addToRear(new Integer(currentIndex*2+1));
                  templist.addToRear(new Integer(currentIndex*2+2));
               }
            }
         
            /** do replacement */
            oldIt = oldlist.iterator();
            newIt = newlist.iterator();
            while (newIt.hasNext())
            {
               oldIndex = oldIt.next();
               newIndex = newIt.next();

               
               tree[oldIndex] = tree[newIndex];
               tree[newIndex] = null;
            }               
         }
         else
            tree[currentRoot] = null;
      }
   }

   /**
    * Returns a string representation of the binary tree
    *
    * @return                           a string representation of the binary 
    *                                   tree
    */
   public String toString()
   {
      //left as programming project
   }

   /**
    * Returns a string representation of the binary tree
    *
    * @return                           a string representation of the binary 
    *                                   tree
    */
   public String toString2()
   {
      //left as programming project
   }

   /**
    * Returns the index of the specified target element if it is
    * found in this binary tree.  Throws an ElementNotFoundException if
    * the specified target element is not found in the binary tree.
    *
    * @param targetElement              the element being sought in the tree
    * @return                           true if the element is in the tree
    * @throws ElementNotFoundException  if an element not found exception occurs
    */
   protected int findIndex (Comparable<T>  targetElement, int n) throws ElementNotFoundException 
   {
      int result = 0;

      if (n > tree.length)
         throw new ElementNotFoundException("binary search tree");
      if (tree[n] == null)
         throw new ElementNotFoundException("binary search tree");
      if (targetElement.compareTo(tree[n]) == 0)
         result = n;
      else
         if (targetElement.compareTo(tree[n]) > 0)
            result = findIndex (targetElement, (2 * (n + 1)));
         else
            result = findIndex (targetElement, (2 * n + 1));
   
      return result;
   }

}

