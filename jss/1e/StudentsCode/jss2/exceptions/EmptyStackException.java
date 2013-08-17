//********************************************************************
//  EmptyStackException.java     Authors: Lewis/Chase
//
//  Represents the situation in which a stack is empty.
//********************************************************************

package jss2.exceptions;

public class EmptyStackException extends RuntimeException
{
   //-----------------------------------------------------------------
   //-----------------------------------------------------------------
   public EmptyStackException()
   {
      super ("The stack is empty.");
   }

   //-----------------------------------------------------------------
   //-----------------------------------------------------------------
   public EmptyStackException (String message)
   {
      super (message);
   }
}
