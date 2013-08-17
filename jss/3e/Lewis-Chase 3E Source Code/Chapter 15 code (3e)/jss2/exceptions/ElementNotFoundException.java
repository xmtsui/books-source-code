/**
 * ElementNotFoundException represents the situation in which a target 
 * element is not present in a collection
 *
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 9/21/2008
 */

package jss2.exceptions;

public class ElementNotFoundException extends RuntimeException
{
  /**
   * Sets up this exception with an appropriate message.
   *
   * @param collection  the custom message for this exception
   */
  public ElementNotFoundException (String collection)
  {
    super ("The target element is not in this " + collection);
  }
}
