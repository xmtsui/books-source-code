//********************************************************************
//  ArrayStack.java       Authors: Lewis/Chase
//
//  Represents an array implementation of a stack.
//********************************************************************

package jss2;

import jss2.exceptions.*;
import java.util.Iterator;

public class ArrayStack implements StackADT
{
   private final int DEFAULT_CAPACITY = 100;
   private int top;  // indicates the next open slot
   private Object[] stack; 

   //-----------------------------------------------------------------
   //  Creates an empty stack using the default capacity.
   //-----------------------------------------------------------------
   public ArrayStack()
   {
      top = 0;
      stack = new Object[DEFAULT_CAPACITY];
   }

   //-----------------------------------------------------------------
   //  Creates an empty stack using the specified capacity.
   //-----------------------------------------------------------------
   public ArrayStack (int initialCapacity)
   {
      top = 0;
      stack = new Object[initialCapacity];
   }

   //-----------------------------------------------------------------
   //  Adds the specified element to the top of the stack, expanding
   //  the capacity of the stack array if necessary.
   //-----------------------------------------------------------------
   public void push (Object element)
   {
      if (size() == stack.length) 
         expandCapacity();

      stack[top] = element;
      top++;
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

      top--;
      Object result = stack[top];
      stack[top] = null; 
 
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

   //-----------------------------------------------------------------
   //  Creates a new array to store the contents of the stack with
   //  twice the capacity of the old one.
   //-----------------------------------------------------------------
   private void expandCapacity()
   {
      Object[] larger = new Object[stack.length*2];

      for (int index=0; index < stack.length; index++)
         larger[index] = stack[index];

      stack = larger;
   }
}
