/**
 * LinkedSet represents a linked implementation of a set.
 *
 * @author Dr. Chase
 * @author Dr. Lewis
 * @version 1.0, 9/21/2008
 */

package jss2;
import jss2.exceptions.*;
import java.util.*;

public class LinkedSet<T> implements SetADT<T>, Iterable<T>
{
  private static Random rand = new Random();
  private int count;  // the current number of elements in the set 
  private LinearNode<T> contents; 

  /**
   * Creates an empty set.
   */
  public LinkedSet()
  {
    count = 0;
    contents = null;
  }

  /**
   * Adds the specified element to this set if it is not already
   * present.
   *
   * @param element  the element to be added to this set
   */
  public void add (T element)
  {
    if (!(contains(element)))
    {
      LinearNode<T> node = new LinearNode<T> (element);
      node.setNext(contents);
      contents = node;
      count++;
    }
  }

  /**
   * Adds the contents of the parameter to this set.
   *
   * @param set  the set whose contents are to be added to this set
   */
  public void addAll (SetADT<T> set)
  {
          //left as programming project

 
  }

  /**
   * Removes and returns a random element from this set. Throws
   * an EmptySetException if the set contains no elements.
   *
   * @return                    a random element from this set
   * @throws EmptyCollectionException  if an empty set exception occurs
   */
  public T removeRandom() throws EmptyCollectionException
  {
    LinearNode<T> previous, current;
    T result = null;

    if (isEmpty())
      throw new EmptyCollectionException("Stack");
    
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

  /**
   * Removes and returns the specified element from this set.
   * Throws an EmptySetException if the set is empty and a
   * NoSuchElemetnException if the target is not in the set.
   *
   * @param target                   the element being sought in this set
   * @return                         the element just removed from this set
   * @throws EmptyCollectionException       if an empty set exception occurs
   * @throws NoSuchElementException  if a no such element exception occurs
   */
  public T remove (T target) throws EmptyCollectionException,
                                     NoSuchElementException
  {
    boolean found = false;
    LinearNode<T> previous, current;
    T result = null;

    if (isEmpty())
      throw new EmptyCollectionException("Set");

    if (contents.getElement().equals(target))
    {
      result = contents.getElement();
      contents = contents.getNext();
    }
    else
    {
      previous = contents;
      current = contents.getNext();
      for (int look=0; look < count && !found; look++)
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
  
  /**
   * Returns a new set that is the union of this set and the
   * parameter.
   *
   * @param set  the set to be unioned with this set
   * @return     a new set that is a union of this set and the parameter
   */
  public SetADT<T> union (SetADT<T> set)
  {
          //left as programming project

 }
  
  /**
   * Returns true if this set contains the specified target
   * element.
   *
   * @param target  the element being sought in this list
   * @return        true if the set contains the target element
   */
  public boolean contains (T target)
  {
          //left as programming project

 }
  
  /**
   * Returns true if this set contains exactly the same elements
   * as the parameter.
   *
   * @param set  the set to be compared with this set
   * @return     true if this set contains exactly the same elements
   *             as the parameter
   */
  public boolean equals (SetADT<T> set)
  {
          //left as programming project

 }

  /**
   * Returns true if this set is empty and false otherwise. 
   *
   * @return  true if this set is empty
   */
  public boolean isEmpty()
  {
          //left as programming project

 }
 
  /**
   * Returns the number of elements currently in this set.
   *
   * @return  the number of elements in this set
   */
  public int size()
  {
          //left as programming project

 }

  /**
   * Returns an iterator for the elements currently in this set.
   *
   * @return  an iterator for the elements in this set
   */
  public Iterator<T> iterator()
  {
          //left as programming project

 
  }

  /**
   * Returns a string representation of this set. 
   *
   * @return  a string representation of this set
   */
  public String toString()
  {
          //left as programming project

 }
}

