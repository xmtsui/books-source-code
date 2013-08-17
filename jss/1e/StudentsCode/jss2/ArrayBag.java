
//********************************************************************
//  ArrayBag.java       Authors: Lewis/Chase
//
//  Represents an array implementation of a bag.
//********************************************************************

package jss2;

import jss2.exceptions.*;
import java.util.*;

public class ArrayBag implements BagADT
{
   private static Random rand = new Random();

   private final int DEFAULT_CAPACITY = 100;
   private final int NOT_FOUND = -1;

   private int count;  // the current number of elements in the bag 

   private Object[] contents; 

   //-----------------------------------------------------------------
   //  Creates an empty bag using the default capacity.
   //-----------------------------------------------------------------
   public ArrayBag()
   {
      count = 0;
      contents = new Object[DEFAULT_CAPACITY];
   }

   //-----------------------------------------------------------------
   //  Creates an empty bag using the specified capacity.
   //-----------------------------------------------------------------
   public ArrayBag (int initialCapacity)
   {
      count = 0;
      contents = new Object[initialCapacity];
   }

   //-----------------------------------------------------------------
   //  Adds the specified element to the bag, expanding the capacity
   //  of the bag array if necessary.
   //-----------------------------------------------------------------
   public void add (Object element)
   {
      if (size() == contents.length) 
         expandCapacity();

      contents[count] = element;
      count++;
   }

   //-----------------------------------------------------------------
   //  Adds the contents of the parameter to this bag.
   //-----------------------------------------------------------------
   public void addAll (BagADT bag)
   {
      Iterator scan = bag.iterator();

      while (scan.hasNext())
         add (scan.next());
   }


   //-----------------------------------------------------------------
   //  Removes a random element from the bag and returns it. Throws
   //  an EmptyBagException if the bag is empty.
   //-----------------------------------------------------------------
   public Object removeRandom() throws EmptyBagException
   {
      if (isEmpty())
         throw new EmptyBagException();

      int choice = rand.nextInt(count);

      Object result = contents[choice];

      contents[choice] = contents[count-1];  // fill the gap
      contents[count-1] = null;
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
      int search = NOT_FOUND;

      if (isEmpty())
         throw new EmptyBagException();

      for (int index=0; index < count && search == NOT_FOUND; index++)
         if (contents[index].equals(target))
            search = index;

      if (search == NOT_FOUND)
         throw new NoSuchElementException();

      Object result = contents[search];

      contents[search] = contents[count-1];
      contents[count-1] = null;
      count--;
 
      return result;
   }
   
   //-----------------------------------------------------------------
   //  Returns a new bag that is the union of this bag and the
   //  parameter.
   //-----------------------------------------------------------------
   public BagADT union (BagADT bag)
   {
      ArrayBag both = new ArrayBag();

      for (int index = 0; index < count; index++)
         both.add (contents[index]);

      Iterator scan = bag.iterator();
      while (scan.hasNext())
         both.add (scan.next());

      return both;
   }

   //-----------------------------------------------------------------
   //  Returns true if this bag contains the specified target
   //  element.
   //-----------------------------------------------------------------
   public boolean contains (Object target)
   {
      int search = NOT_FOUND;

      for (int index=0; index < count && search == NOT_FOUND; index++)
         if (contents[index].equals(target))
            search = index;

      return (search != NOT_FOUND);
   }

   //-----------------------------------------------------------------
   //  Returns true if this bag contains exactly the same elements
   //  as the parameter.
   //-----------------------------------------------------------------
   public boolean equals (BagADT bag)
   {
      boolean result = false;
      ArrayBag temp1 = new ArrayBag();
	 ArrayBag temp2 = new ArrayBag();
	 Object obj;

      if (size() == bag.size())
      { 
	    temp1.addAll(this);
         temp2.addAll(bag);

         Iterator scan = bag.iterator();

	    while (scan.hasNext())
	    {
		  obj = scan.next();   
	       if (temp1.contains(obj))
		     {
			   temp1.remove(obj);
			   temp2.remove(obj);
			}
		  
	    }
	    result = (temp1.isEmpty() && temp2.isEmpty());
      }

      return result;
   }

   //-----------------------------------------------------------------
   //  Returns true if this bag is empty and false otherwise. 
   //-----------------------------------------------------------------
   public boolean isEmpty()
   {
      return (count == 0);
   }
 
   //-----------------------------------------------------------------
   //  Returns the number of elements currently in this bag.
   //-----------------------------------------------------------------
   public int size()
   {
      return count;
   }

   //-----------------------------------------------------------------
   //  Returns an iterator for the elements currently in this bag.
   //-----------------------------------------------------------------
   public Iterator iterator()
   {
      return new ArrayIterator (contents, count);
   }

   //-----------------------------------------------------------------
   //  Returns a string representation of this bag. 
   //-----------------------------------------------------------------
   public String toString()
   {
      String result = "";

      for (int index=0; index < count; index++) 
         result = result + contents[index].toString() + "\n";

      return result;
   }

   //-----------------------------------------------------------------
   //  Creates a new array to store the contents of the bag with
   //  twice the capacity of the old one.
   //-----------------------------------------------------------------
   private void expandCapacity()
   {
      Object[] larger = new Object[contents.length*2];

      for (int index=0; index < contents.length; index++)
         larger[index] = contents[index];

      contents = larger;
   }
}

