//********************************************************************
//  ArraySet.java       Authors: Lewis/Chase
//
//  Represents an array implementation of a set.
//********************************************************************
package jss2;
import jss2.exceptions.*;
import java.util.*;

public class ArraySet<T> implements SetADT<T>, Iterable<T>
{
  private static Random rand = new Random();
  private final int DEFAULT_CAPACITY = 100;
  private final int NOT_FOUND = -1;
  private int count;  // the current number of elements in the set
  private T[] contents; 
  
  /******************************************************************
    Creates an empty set using the default capacity.
  ******************************************************************/
  public ArraySet()
  {
    count = 0;
    contents = (T[])(new Object[DEFAULT_CAPACITY]);
  }
  
  /******************************************************************
    Creates an empty set using the specified capacity.
  ******************************************************************/
  public ArraySet (int initialCapacity)
  {
    count = 0;
    contents = (T[])(new Object[initialCapacity]);
  }
  
  /******************************************************************
    Adds the specified element to the set if it is not already
    present. Expands the capacity of the set array if necessary.
  ******************************************************************/
  public void add (T element)
  {
    if (!(contains(element)))
    {
      if (size() == contents.length)
        expandCapacity();
      
      contents[count] = element;
      count++;
    }
  }
  
  /******************************************************************
    Adds the contents of the parameter to this set.
  ******************************************************************/
  public void addAll (SetADT<T> set)
  {
    Iterator<T> scan = set.iterator();
    
    while (scan.hasNext())
      add (scan.next());
  }
  
  /******************************************************************
    Removes a random element from the set and returns it. Throws
    an EmptySetException if the set is empty.
  ******************************************************************/
  public T removeRandom() throws EmptySetException
  {
    if (isEmpty())
      throw new EmptySetException();
    
    int choice = rand.nextInt(count);
    
    T result = contents[choice];
    
    contents[choice] = contents[count-1];  // fill the gap
    contents[count-1] = null;
    count--;
    
    return result;
  }
  
  /******************************************************************
    Removes the specified element from the set and returns it.
    Throws an EmptySetException if the set is empty and a
    NoSuchElementException if the target is not in the set.
  ******************************************************************/
  public T remove (T target) throws EmptySetException,
                                      NoSuchElementException
  {
    int search = NOT_FOUND;
    
    if (isEmpty())
      throw new EmptySetException();
    
    for (int index=0; index < count && search == NOT_FOUND; index++)
      if (contents[index].equals(target))
        search = index;
    
    if (search == NOT_FOUND)
      throw new NoSuchElementException();
    
    T result = contents[search];
    
    contents[search] = contents[count-1];
    contents[count-1] = null;
    count--;
    
    return result;
  }
  
  /******************************************************************
    Returns a new set that is the union of this set and the
    parameter.
  ******************************************************************/
  public SetADT<T> union (SetADT<T> set)
  {
    ArraySet<T> both = new ArraySet<T>();
    
    for (int index = 0; index < count; index++)
      both.add (contents[index]);
    
    Iterator<T> scan = set.iterator();
    while (scan.hasNext())
      both.add (scan.next());
    
    return both;
  }
  
  /******************************************************************
    Returns true if this set contains the specified target
    element.
  ******************************************************************/
  public boolean contains (T target)
  {
    int search = NOT_FOUND;
    
    for (int index=0; index < count && search == NOT_FOUND; index++)
      if (contents[index].equals(target))
        search = index;
    
    return (search != NOT_FOUND);
  }
  
  /******************************************************************
    Returns true if this set contains exactly the same elements
    as the parameter.
  ******************************************************************/
  public boolean equals (SetADT<T> set)
  {
    boolean result = false;
    ArraySet<T> temp1 = new ArraySet<T>();
    ArraySet<T> temp2 = new ArraySet<T>();
    T obj;
    
    if (size() == set.size())
    {
      temp1.addAll(this);
      temp2.addAll(set);
      
      Iterator<T> scan = set.iterator();
      
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
  
  /******************************************************************
    Returns true if this set is empty and false otherwise.
  ******************************************************************/
  public boolean isEmpty()
  {
    return (count == 0);
  }
  
  /******************************************************************
    Returns the number of elements currently in this set.
  ******************************************************************/
  public int size()
  {
    return count;
  }
  
  /******************************************************************
    Returns an iterator for the elements currently in this set.
  ******************************************************************/
  public Iterator<T> iterator()
  {
    return new ArrayIterator<T> (contents, count);
  }
  
  /******************************************************************
    Returns a string representation of this set.
  ******************************************************************/
  public String toString()
  {
    String result = "";
    
    for (int index=0; index < count; index++) 
      result = result + contents[index].toString() + "\n";
    
    return result;
  }
  
  /******************************************************************
    Creates a new array to store the contents of the set with
    twice the capacity of the old one.
  ******************************************************************/
  private void expandCapacity()
  {
    T[] larger = (T[])(new Object[contents.length*2]);
    
    for (int index=0; index < contents.length; index++)
      larger[index] = contents[index];
    
    contents = larger;
  }
}
