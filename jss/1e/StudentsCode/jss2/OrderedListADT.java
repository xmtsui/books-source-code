//********************************************************************
//  OrderedListADT.java       Authors: Lewis/Chase
//
//  Defines the interface to an ordered list collection. Only
//  Comparable elements are stored, kept in the order determined by
//  the inherent relationship among the elements.
//********************************************************************

package jss2;

public interface OrderedListADT extends ListADT
{
   //  Adds the specified element to this list at the proper location
   public void add (Comparable element);
}


