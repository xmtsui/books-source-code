//********************************************************************// BinarySearchTreeADT.java         Authors:  Lewis/Chase//// Defines the interface to a binary search tree.//********************************************************************package jss2;public interface BinarySearchTreeADT<T> extends BinaryTreeADT<T> {   /** Adds the specified element to the proper location in this tree. */   public void addElement (T element);      /** Removes and returns the specified element from this tree. */     public T removeElement (T targetElement);      /** Removes all occurences of the specified element from this tree. */   public void removeAllOccurrences (T targetElement);      /** Removes and returns the smallest element from this tree. */   public T removeMin();      /** Removes and returns the largest element from this tree. */     public T removeMax();      /** Returns a reference to the smallest element in this tree. */    public T findMin();      /** Returns a reference to the largest element in this tree. */   public T findMax();}