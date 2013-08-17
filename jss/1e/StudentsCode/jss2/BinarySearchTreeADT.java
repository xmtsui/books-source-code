//*******************************************************************
//
// BinarySearchTreeADT.java		Authors:  Lewis/Chase
//
// Defines the interface to a binary search tree
//*******************************************************************

package jss2;


public interface BinarySearchTreeADT extends BinaryTreeADT 
{
  
   public void addElement (Comparable element);
   
   public Comparable removeElement (Comparable targetElement);

   public void removeAllOccurrences (Comparable targetElement);

   public Comparable removeMin();
   
   public Comparable removeMax();
  
   public Comparable findMin();
   
   public Comparable findMax();
   
}  // interface BinarySearchTreeADT

