//********************************************************************
//  LinkedBag.java       Authors: Lewis/Chase
//
//  Represents a linked implementation of a bag.
//********************************************************************

package jss2;

import jss2.exceptions.*;
import java.util.*;

public class LinkedBag implements BagADT
{
   private static Random rand = new Random();

   private int count;  // the current number of elements in the bag 

   private LinearNode contents; 

   //-----------------------------------------------------------------
   //  Creates an empty bag.
   //-----------------------------------------------------------------
   public LinkedBag()
   {
      count = 0;
      contents = null;
   }

   //-----------------------------------------------------------------
   //  Adds the specified element to the bag.
   //-----------------------------------------------------------------
   public void add (Object element)
   {
      LinearNode node = new LinearNode (element);
      node.setNext(contents);
      contents = node;
      count++;
   }

   //-----------------------------------------------------------------
   //  Adds the contents of the parameter to this bag.
   //-----------------------------------------------------------------
   public void addAll (BagADT bag)
   {


   }

   //-----------------------------------------------------------------
   //  Removes a random element from the bag and returns it. Throws
   //  an EmptyBagException if the bag is empty.
   //-----------------------------------------------------------------
   public Object removeRandom() throws EmptyBagException
   {
      LinearNode previous, current;
      Object result = null;

      if (isEmpty())
         throw new EmptyBagException();

      int choice = rand.nextInt(count) + 1;

      if (choice == 1)
      {
         result = contents.getElement();
         contents = contents.getNext();
      }
      else
      {
         previous = contents;
         for (int skip=2; skip < choice; skip++)
            previous = previous.getNext();
         current = previous.getNext();
         result = current.getElement();
         previous.setNext(current.getNext());
      }
      
      count--;
 
      return result;
   }

   //-----------------------------------------------------------------
   //  Removes one occurance of the specified element from the bag
   //  and returns it. Throws an EmptyBagException if the bag is
   //  empty and a NoSuchElemetnException if the target is not in
   //  the bag.
   //-----------------------------------------------------------------
   public Object remove (Object target) throws EmptyBagException,
                                            NoSuchElementException
   {
      boolean found = false;
      LinearNode previous, current;
      Object result = null;

      if (isEmpty())
         throw new EmptyBagException();

      if (contents.getElement().equals(target))
      {
         result = contents.getElement();
         contents = contents.getNext();
      }
      else
      {
         previous = contents;
         current = contents.getNext();
         for (int look=1; look < count && !found; look++)
            if (current.getElement().equals(target))
               found = true;
            else
            {
               previous = current;
               current = current.getNext();
            }

         if (!found)
            throw new NoSuchElementException();

         result = current.getElement();
         previous.setNext(current.getNext());
      }
         
      count--;
 
      return result;
   }
   
   //-----------------------------------------------------------------
   //  Returns a new bag that is the union of this bag and the
   //  parameter.
   //-----------------------------------------------------------------
   public BagADT union (BagADT bag)
   {


   }

   //-----------------------------------------------------------------
   //  Returns true if this bag contains the specified target
   //  element.
   //-----------------------------------------------------------------
   public boolean contains (Object target)
   {


   }

   //-----------------------------------------------------------------
   //  Returns true if this bag contains exactly the same elements
   //  as the parameter.
   //-----------------------------------------------------------------
   public boolean equals (BagADT bag)
   {


   }

   //-----------------------------------------------------------------
   //  Returns true if this bag is empty and false otherwise. 
   //-----------------------------------------------------------------
   public boolean isEmpty()
   {


   }
 
   //-----------------------------------------------------------------
   //  Returns the number of elements currently in this bag.
   //-----------------------------------------------------------------
   public int size()
   {


   }

   //-----------------------------------------------------------------
   //  Returns an iterator for the elements currently in this bag.
   //-----------------------------------------------------------------
   public Iterator iterator()
   {
      return new LinkedIterator (contents, count);
   }

   //-----------------------------------------------------------------
   //  Returns a string representation of this bag. 
   //-----------------------------------------------------------------
   public String toString()
   {


   }
}


