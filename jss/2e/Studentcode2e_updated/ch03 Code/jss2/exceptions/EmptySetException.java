//********************************************************************
//  EmptySetException.java     Authors: Lewis/Chase
//
//  Represents the situation in which a set is empty.
//********************************************************************
package jss2.exceptions;

public class EmptySetException extends RuntimeException
{
  /******************************************************************
    Creates the exception.
  *******************************************************************/
  public EmptySetException()
  {
    super ("The set is empty.");
  }
  
  /******************************************************************
    Creates the exception with the specified message.
  *******************************************************************/
  public EmptySetException (String message)
  {
    super (message);
  }
}
