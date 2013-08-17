//*******************************************************************
//
//      file:  LinkedList.java
//
//*******************************************************************

package jss2;
import jss2.exceptions.*;
import java.util.*;

public class LinkedList implements ListADT {

   protected int count;
   protected LinearNode head, tail;

   //===========================================================
   //  Creates an empty list.
   //===========================================================
   public LinkedList() {


   }  // constructor List

   //===========================================================
   //  Removes the first element in the list and returns a reference
   //  to it.  Throws an EmptyListException if the list is empty.
   //===========================================================
   public Object removeFirst() throws EmptyCollectionException {




   } // method removeFirst
   
   //===========================================================
   //  Removes the last element in the list and returns a reference
   //  to it.  Throws an EmptyListException if the list is empty.
   //===========================================================
   public Object removeLast() throws EmptyCollectionException {



   } // method removeLast
   
   //===========================================================
   //  Removes the first instance of the specified element from the
   //  list if it is found in the list and returns a reference to it.
   //  Throws an EmptyListException if the list is empty.  Throws a
   //  NoSuchElementException if the specified element is not found
   //  on the list.
   //===========================================================
   public Object remove (Object targetElement) throws
   EmptyCollectionException, ElementNotFoundException 
   {

      if (isEmpty())
         throw new EmptyCollectionException ("List");

      boolean found = false;

      LinearNode previous = null;
      LinearNode current = head;

      while (current != null && !found) 
         if (targetElement.equals (current.getElement()))
            found = true;
         else {
            previous = current;
            current = current.getNext();
         }

      if (!found)
         throw new ElementNotFoundException ("List");

      if (size() == 1)
         head = tail = null;
      else if (current.equals (head)) 
              head = current.getNext();
           else if (current.equals (tail)) 
                {
                   tail = previous;
                   tail.setNext(null);
                } 
                else 
                   previous.setNext(current.getNext());

      count--;

      return current.getElement();

   }  // method remove
   
   //===========================================================
   //  Finds the first instance of the specified element from the
   //  list if it is found in the list and returns true. 
   //  Returns false otherwise                                     
   //===========================================================
   public boolean contains (Object targetElement) throws
   EmptyCollectionException {


   }  // method contains 
   
 
   //===========================================================
   //  Returns true if the list is empty and false otherwise
   //===========================================================
   public boolean isEmpty() {


   }  // method isEmpty

   //===========================================================
   //  Returns the number of elements in the list.
   //===========================================================
   public int size() {


   }  // method size



   //===========================================================
   //  Returns a string representation of the list.
   //===========================================================
   public String toString() {




   } // method toString

   //===========================================================
   //  Returns ... 
   //===========================================================
   public Iterator iterator() {


   }  // method elements

   //===========================================================
   //  Returns the first element of the list. 
   //===========================================================
   public Object first() {


   }  // method firstElement

   //===========================================================
   //  Returns the last element of the list. 
   //===========================================================
   public Object last() {


   }  // method lastElement

}  // class LinkedList


