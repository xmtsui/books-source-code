//********************************************************************
//  NonComparableElementException.java     Authors: Lewis/Chase
//
//  Represents the situation in which an attempt is made to add an 
//  element that is not comparable to an ordered collection
//********************************************************************
package jss2.exceptions;

public class NonComparableElementException extends RuntimeException
{
   /******************************************************************
     Sets up this exception with an appropriate message.
   ******************************************************************/
   public NonComparableElementException (String collection)
   {
      super ("The " + collection + " requires comparable elements.");
   }
}
