/**
 * NonComparableElementException  represents the situation in which an attempt 
 * is made to add an element that is not comparable to an ordered collection
 *
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 08/13/08
 */

package jss2.exceptions;

public class NonComparableElementException extends RuntimeException
{
   /**
    * Sets up this exception with an appropriate message.
    * 
    * @param collection  the exception message to deliver
    */
   public NonComparableElementException (String collection)
   {
      super ("The " + collection + " requires comparable elements.");
   }
}
