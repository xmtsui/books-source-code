/**
 * ArrayUnorderedList represents an array implementation of an unordered list.
 *
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 08/12/08
 */

package jss2;
import jss2.exceptions.*;

public class ArrayUnorderedList<T> extends ArrayList<T> 
         implements UnorderedListADT<T>
{
   /**
    * Creates an empty list using the default capacity.
    */
   public ArrayUnorderedList()
   {
      super();
   }

   /**
    * Creates an empty list using the specified capacity.
    *
    * @param initialCapacity  the integer intial size of the list
    */
   public ArrayUnorderedList (int initialCapacity)
   {
      super(initialCapacity);
   }

   /**
    * Adds the specified element to the front of this list.
    * 
    * @param element  the element to be added to the front of the list
    */
   public void addToFront (T element)
   {
       //left as programming project
   }

   /**
    * Adds the specified element to the rear of this list.
    *
    * @param element  the element to be added to the list
    */
   public void addToRear (T element)
   {
       //left as programming project
   }

   /**
    * Adds the specified element after the specified target element.
    * Throws an ElementNotFoundException if the target is not found.
    *
    * @param element  the element to be added after the target element
    * @param target   the target that the element is to be added after
    */
   public void addAfter (T element, T target)
   {
      if (size() == list.length)
         expandCapacity();

      int scan = 0;
      while (scan < rear && !target.equals(list[scan])) 
          scan++;
      
      if (scan == rear)
         throw new ElementNotFoundException ("list");
    
      scan++;
      for (int scan2=rear; scan2 > scan; scan2--)
         list[scan2] = list[scan2-1];

      list[scan] = element;
      rear++;
   }
}
