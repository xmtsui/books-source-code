//********************************************************************
//  UnorderedListADT.java       Authors: Lewis/Chase
//
//  Defines the interface to an unordered list collection. Elements
//  are stored in any order the user desires.
//********************************************************************

package jss2;

public interface UnorderedListADT extends ListADT
{
   //  Adds the specified element to the front of this list
   public void addToFront (Object element);

   //  Adds the specified element to the rear of this list
   public void addToRear (Object element);

   //  Adds the specified element after the specified target
   public void addAfter (Object element, Object target);
}


