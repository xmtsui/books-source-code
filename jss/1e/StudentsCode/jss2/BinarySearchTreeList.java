//*******************************************************************
//
// BinarySearchTreeList.java		Authors: Lewis/Chase
//
// Represents an ordered list implemented using a binary search tree.
//*******************************************************************

package jss2;
import jss2.exceptions.*;
import java.util.Iterator;

public class BinarySearchTreeList extends BinarySearchTree implements ListADT, OrderedListADT
{

   //================================================================
   //  Creates an empty BinarySearchTreeList.
   //================================================================
   public BinarySearchTreeList() 
   {
      super();
   }  // constructor BinarSearchTreeList


   //================================================================
   //  adds the given element to the BinarySearchTreeList
   //================================================================
public void add (Comparable element)
   {
      addElement(element);
   }  // method add

//================================================================
//  Removes and returns the first element from this list
//================================================================

   public Object removeFirst ()
   {
      return removeMin();
   }

//================================================================
//  Removes and returns the last element from this list
//================================================================
   public Object removeLast ()
   {
      return removeMax();
   }

//================================================================
//  Removes and returns the specified element from this list
//================================================================
   public Object remove (Object element)
   {
      return removeElement((Comparable)element);
   }

//================================================================
//  Returns a reference to the first element on this list
//================================================================
   public Object first ()
   {
      return findMin();
   }

//================================================================
//  Returns a reference to the last element on this list
//================================================================
   public Object last ()
   {
      return findMax();
   }

//================================================================
//  Returns an iterator for the list
//================================================================
   public Iterator iterator()
   {
      return iteratorInOrder();
   }



}  // class BinarySearchTreeList

