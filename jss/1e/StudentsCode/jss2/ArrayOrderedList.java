//********************************************************************
//  ArrayOrderedList.java       Authors: Lewis/Chase
//                              Mods   : JCD
//  Represents an array implementation of an ordered list.
//********************************************************************

package jss2;
import jss2.exceptions.*;

public class ArrayOrderedList extends ArrayList implements OrderedListADT
{






   //-----------------------------------------------------------------
   //  Adds the specified Comparable element to the list, keeping
   //  the elements in sorted order.
   //-----------------------------------------------------------------
   public void add (Comparable element)
   {
      if (size() == list.length)
         expandCapacity();

      int scan = 0;   
       while (scan < rear && element.compareTo(list[scan]) > 0)
          scan++;

      for (int scan2=rear; scan2 > scan; scan2--)
         list[scan2] = list[scan2-1];

      list[scan] = element;
      rear++;
   }
}


