//********************************************************************
//  EmptyStackException.java     Authors: Lewis/Chase
//
//  Represents the situation in which a stack is empty.
//********************************************************************
package jss2.exceptions;

public class EmptyStackException extends RuntimeException
{
  /*******************************************************************
    Creates the exception.
  *******************************************************************/
  public EmptyStackException()
  {
    super ("The stack is empty.");
  }
  
  /*******************************************************************
    Creates the exception with the specified message.
  *******************************************************************/
  public EmptyStackException (String message)
  {
    super (message);
  }
}
