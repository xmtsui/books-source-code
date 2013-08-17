//********************************************************************
//  EmptyCollectionException.java     Authors: Lewis/Chase
//
//  Represents the situation in which a collection is empty.
//********************************************************************
package jss2.exceptions;

public class EmptyCollectionException extends RuntimeException
{
  /******************************************************************
    Sets up this exception with an appropriate message.
  ******************************************************************/
  public EmptyCollectionException (String collection)
  {
    super ("The " + collection + " is empty.");
  }
}
