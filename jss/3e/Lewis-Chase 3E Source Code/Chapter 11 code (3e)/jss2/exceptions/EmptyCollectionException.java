/**
 * EmptyCollectionException represents the situation in which a collection 
 * is empty.
 * 
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 8/19/08
 */

package jss2.exceptions;

public class EmptyCollectionException extends RuntimeException
{
   /**
    * Sets up this exception with an appropriate message.
    *
    * @param collection  the custom message for the exception
    */
   public EmptyCollectionException (String collection)
   {
      super ("The " + collection + " is empty.");
   }
}

