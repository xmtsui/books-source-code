/**
 * ArrayOrderedList represents an array implementation of an ordered list.
 *
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 08/12/08
 */

package jss2;
import jss2.exceptions.*;

public class ArrayOrderedList<T> extends ArrayList<T>
         implements OrderedListADT<T>
{
   /**
    * Creates an empty list using the default capacity.
    */
   public ArrayOrderedList()
   {
      super();
   }

   /**
    * Creates an empty list using the specified capacity.
    *
    * @param initialCapacity  the integer initial size of the list
    */
   public ArrayOrderedList (int initialCapacity)
   {
      super(initialCapacity);
   }

   /**
    * Adds the specified Comparable element to this list, keeping
    * the elements in sorted order.
    *
    * @param element  the element to be added to this list
    */
   public void add (T element)
   {
      if (size() == list.length)
         expandCapacity();

      Comparable<T> temp = (Comparable<T>)element;

      int scan = 0;   
       while (scan < rear && temp.compareTo(list[scan]) > 0)
          scan++;

      for (int scan2=rear; scan2 > scan; scan2--)
         list[scan2] = list[scan2-1];

      list[scan] = element;
      rear++;
   }
}
