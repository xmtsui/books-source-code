//********************************************************************
//  StackADT.java       Authors: Lewis/Chase
//
//  Defines the interface to a stack data structure.
//********************************************************************

package jss2;

public interface StackADT
{
   //  Adds one element to the top of this stack
   public void push (Object element);

   //  Removes and returns the top element from this stack
   public Object pop();

   //  Returns without removing the top element of this stack
   public Object peek();
   
   //  Returns true if this stack contains no elements
   public boolean isEmpty();

   //  Returns the number of elements in this stack
   public int size();

   //  Returns a string representation of this stack
   public String toString();
}
