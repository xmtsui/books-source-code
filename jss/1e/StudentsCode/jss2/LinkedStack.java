//********************************************************************
//  LinkedStack.java       Authors: Lewis/Chase
//
//  Represents a linked implementation of a stack.
//********************************************************************

package jss2;

import jss2.exceptions.*;
import java.util.Iterator;

public class LinkedStack implements StackADT
{
   private int count;  // indicates the next open slot
   private LinearNode top; 

   //-----------------------------------------------------------------
   //  Creates an empty stack.
   //-----------------------------------------------------------------
   public LinkedStack()
   {
      count = 0;
      top = null;
   }

   //-----------------------------------------------------------------
   //  Adds the specified element to the top of the stack.
   //-----------------------------------------------------------------
   public void push (Object element)
   {
      LinearNode temp = new LinearNode (element);

      temp.setNext(top);
      top = temp;
      count++;
   }

   //-----------------------------------------------------------------
   //  Removes the element at the top of the stack and returns a
   //  reference to it. Throws an EmptyStackException if the stack
   //  is empty.
   //-----------------------------------------------------------------
   public Object pop() throws EmptyStackException
   {
      if (isEmpty())
         throw new EmptyStackException();

      Object result = top.getElement();
      top = top.getNext();
      count--;
 
      return result;
   }
   
   //-----------------------------------------------------------------
   //  Returns a reference to the element at the top of the stack.
   //  The element is not removed from the stack.  Throws an
   //  EmptyStackException if the stack is empty.  
   //-----------------------------------------------------------------
   public Object peek() throws EmptyStackException
   {


   }

   //-----------------------------------------------------------------
   //  Returns true if the stack is empty and false otherwise. 
   //-----------------------------------------------------------------
   public boolean isEmpty()
   {


   }
 
   //-----------------------------------------------------------------
   //  Returns the number of elements in the stack.
   //-----------------------------------------------------------------
   public int size()
   {


   }

   //-----------------------------------------------------------------
   //  Returns a string representation of the stack. 
   //-----------------------------------------------------------------
   public String toString()
   {


   }
}
