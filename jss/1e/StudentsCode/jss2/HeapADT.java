//*******************************************************************
//
//  HeapADT.java		Authors:  Lewis and Chase
//
//  defines the interface to a Heap
//*******************************************************************

package jss2;

public interface HeapADT extends BinaryTreeADT {

   
   public void addElement (Comparable obj);

   public Comparable removeMin();
   
   public Comparable findMin();

}  // interface HeapADT
