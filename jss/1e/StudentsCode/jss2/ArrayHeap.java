//*******************************************************************
//
//  ArrayHeap.java				Authors:  Lewis/Chase
//
//  Provides an array implementation of a minheap. 
//*******************************************************************

package jss2;

import jss2.exceptions.*;

public class ArrayHeap extends ArrayBinaryTree implements HeapADT 
{
     

   public ArrayHeap() 
   {


   }  // constructor Heap

   //================================================================
   //  Adds the specified element to the heap in the appropriate
   //  position according to its key value.  Note that equal elements
   //  are added to the right.
   //================================================================
   public void addElement (Comparable obj) 
   {
      if (count==size())
         expandCapacity();

      tree[count] =obj;
      count++;

      if (count>1)
         heapifyAdd();
   } //method addElement

   //================================================================
   //  Reorders the heap to maintain the ordering property
   //================================================================
   private void heapifyAdd()
   {
      Comparable temp;

      int next = count - 1;
      while ((next != 0) && (((Comparable)tree[next]).compareTo(tree[(next-1)/2]) < 0))
      {
         temp = (Comparable)tree[next];
         tree[next] = tree[(next-1)/2];
         tree[(next-1)/2]= temp;
         next = (next-1)/2;
      }
   } //method heapifyAdd


   

   //================================================================
   //  Remove the element with the lowest value in the heap and
   //  returns a reference to it.  Throws an EmptyHeapException if
   //  the heap is empty.
   //================================================================
   public Comparable removeMin() throws EmptyCollectionException 
   {
      
      if (isEmpty())
         throw new EmptyCollectionException ("Empty Heap");

      Comparable minElement = (Comparable) tree[0];

      tree[0] = tree[count-1];
      heapifyRemove();
      count--;
      return minElement;

   }  // method removeMin
   

   //================================================================
   //  reorder the heap to maintain the ordering property
   //================================================================

   private void heapifyRemove()
   {
      Comparable temp;
      int node = 0;
      int left = 1;
      int right = 2;
      int next;
      
      if ((tree[left] == null) && (tree[right] == null))
         next = count;
      else if (tree[left] == null)
         next = right;
      else if (tree[right] == null)
         next = left;
      else if (((Comparable)tree[left]).compareTo(tree[right]) < 0)
         next = left;
      else
         next = right;
      while ((next < count) && (((Comparable)tree[next]).compareTo(tree[node]) < 0))
         {
            temp = (Comparable)tree[node];
            tree[node] = tree[next];
            tree[next] = temp;
            node = next;
            left = 2*node+1;
            right = 2*(node+1);
            if ((tree[left] == null) && (tree[right] == null))
               next = count;
            else if (tree[left] == null)
               next = right;
            else if (tree[right] == null)
               next = left;
            else if (((Comparable)tree[left]).compareTo(tree[right]) < 0)
               next = left;
            else
               next = right;
         }
   } //method heapifyRemove






   //================================================================
   //  Returns the element with the lowest value in the heap.
   //  Throws an EmptyHeapException if the heap is empty.
   //================================================================
   public Comparable findMin() throws EmptyCollectionException {




   }  // method findMin

   


}  // class ArrayHeap

