//*******************************************************************
//
//      file:  Heap.java
//
//*******************************************************************

package jss2;

import jss2.exceptions.*;

public class Heap extends BinaryTree implements HeapADT 
{
   public HeapNode lastNode;  

   public Heap() 
   {


   }  // constructor Heap

   //================================================================
   //  Adds the specified element to the heap in the appropriate
   //  position according to its key value.  Note that equal elements
   //  are added to the right.
   //================================================================
   public void addElement (Comparable obj) 
   {
      HeapNode node = new HeapNode(obj);

      if (root == null)
         root=node;
      else
      {
         HeapNode next_parent = getNextParentAdd(); 
         if (next_parent.left == null)
            next_parent.left = node;
         else
            next_parent.right = node;
         node.parent = next_parent;
      }
      lastNode = node;
      count++;
      if (count>1)
         heapifyAdd();
   } //method addElement

   //================================================================
   //  Returns the node that will be the parent of the new node
   //================================================================

   private HeapNode getNextParentAdd()
   {
      HeapNode result = lastNode;
      while ((result != root) && (result.parent.left != result))
         result = result.parent;

      if (result != root)
         if (result.parent.right == null)
            result = result.parent;
         else
         {
            result = (HeapNode)result.parent.right;
            while (result.left != null)
               result = (HeapNode)result.left;
         }
      else
         while (result.left != null)
            result = (HeapNode)result.left;
        
      return result;
   } //method getNextParentAdd


   //================================================================
   //  Reorders the heap after adding a node
   //================================================================


   private void heapifyAdd()
   {
      Comparable temp;

      HeapNode next = lastNode;
      while ((next != root) && (((Comparable)next.element).compareTo(next.parent.element) < 0))
      {
         temp = (Comparable)next.element;
         next.element = next.parent.element;
         next.parent.element = temp;
         next = next.parent;
      }
   } //method heapifyAdd


   

   //================================================================
   //  Remove the element with the lowest value in the heap and
   //  returns a reference to it.  Throws an EmptyCollectionException if
   //  the heap is empty.
   //================================================================
   public Comparable removeMin() throws EmptyCollectionException 
   {
      
      if (isEmpty())
         throw new EmptyCollectionException ("Empty Heap");

      Comparable minElement = (Comparable) root.element;

      if (count == 1)
      {
         root = null;
         lastNode = null;
      }
      else
      {
         HeapNode next_last = getNewLastNode();
         if (lastNode.parent.left == lastNode)
            lastNode.parent.left = null;
         else
            lastNode.parent.right = null;

         root.element = lastNode.element;
         lastNode = next_last;
         heapifyRemove();
      }

      count--;
      return minElement;

   }  // method removeMin
   

   //================================================================
   //  Reorders the heap after removing the root element
   //================================================================

   private void heapifyRemove()
   {
      Comparable temp;
      HeapNode node = (HeapNode)root;
      HeapNode left = (HeapNode)node.left;
      HeapNode right = (HeapNode)node.right;
      HeapNode next;
      
      if ((left == null) && (right == null))
         next = null;
      else if (left == null)
         next = right;
      else if (right == null)
         next = left;
      else if (((Comparable)left.element).compareTo(right.element) < 0)
         next = left;
      else
         next = right;

      while ((next != null) && (((Comparable)next.element).compareTo(node.element) < 0))
         {
            temp = (Comparable)node.element;
            node.element = next.element;
            next.element = temp;
            node = next;
            left = (HeapNode)node.left;
            right = (HeapNode)node.right;
            if ((left == null) && (right == null))
               next = null;
            else if (left == null)
               next = right;
            else if (right == null)
               next = left;
            else if (((Comparable)left.element).compareTo(right.element) < 0)
               next = left;
            else
               next = right;
         }
   } //method heapifyRemove

   //================================================================
   //  Returns the node that will be the new last node after a remove
   //================================================================

   private HeapNode getNewLastNode()
   {
      HeapNode result = lastNode;


      while ((result != root) && (result.parent.left == result))
         result = result.parent;
      if (result != root)
         result = (HeapNode)result.parent.left;

      while (result.right != null)
         result = (HeapNode)result.right;

      return result;
   } //method getNewLastNode



   //================================================================
   //  Returns the element with the lowest value in the heap.
   //  Throws an EmptyCollectionException if the heap is empty.
   //================================================================
   public Comparable findMin () throws EmptyCollectionException {



   }  // method findMin

   


}  // class Heap

