/**  
 * ArraySet represents an array implementation of a set.
 *
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 9/21/2008
 */

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
  
  /**
   * Creates an empty set using the default capacity.
   */
  public ArraySet()
  {
    count = 0;
    contents = (T[])(new Object[DEFAULT_CAPACITY]);
  }
  
  /**
   * Creates an empty set using the specified capacity.
   *
   * @param initialCapacity  the initial capacity for the array set
   */
  public ArraySet (int initialCapacity)
  {
    count = 0;
    contents = (T[])(new Object[initialCapacity]);
  }
  
  /**
   * Adds the specified element to the set if it is not already
   * present. Expands the capacity of the set array if necessary.
   *
   * @param element  the element to be added to the set array
   */
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
  
  /**
   * Adds the contents of the parameter to this set.
   *
   * @param set  the collection to be added to this set
   */
  public void addAll (SetADT<T> set)
  {
    Iterator<T> scan = set.iterator();
    
    while (scan.hasNext())
      add (scan.next());
  }
  
  /**
   * Removes a random element from the set and returns it. Throws
   * an EmptyCollectionException if the set is empty.
   *
   * @return                    a random element from the set
   * @throws EmptyCollectionException  if an empty set exception occurs
   */
  public T removeRandom() throws EmptyCollectionException
  {
    if (isEmpty())
      throw new EmptyCollectionException("Stack");
    
    int choice = rand.nextInt(count);
    
    T result = contents[choice];
    
    contents[choice] = contents[count-1];  // fill the gap
    contents[count-1] = null;
    count--;
    
    return result;
  }
  
  /**
   * Removes the specified element from the set and returns it.
   * Throws an EmptyCollectionException if the set is empty and a
   * NoSuchElementException if the target is not in the set.
   *
   * @param target                   the element being sought in the set
   * @return                         the element specified by the target
   * @throws EmptyCollectionException       if an empty set exception occurs
   * @throws NoSuchElementException  if a no such element exception occurs
   */
  public T remove (T target) throws EmptyCollectionException,
                                      NoSuchElementException
  {
    int search = NOT_FOUND;
    
    if (isEmpty())
      throw new EmptyCollectionException("Stack");

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
  
  /**
   * Returns a new set that is the union of this set and the
   * parameter.
   *
   * @param set  the set that is to be unioned with this set
   * @return     a new that that is the union of this set and 
   *             the parameter
   */
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
  
  /**
   * Returns true if this set contains the specified target
   * element.
   *
   * @param target  the element being sought within this set
   * @return        true if the set contains the target element
   */
  public boolean contains (T target)
  {
    int search = NOT_FOUND;
    
    for (int index=0; index < count && search == NOT_FOUND; index++)
      if (contents[index].equals(target))
        search = index;
    
    return (search != NOT_FOUND);
  }
  
  /**
   * Returns true if this set contains exactly the same elements
   * as the parameter.
   *
   * @param set  the set to be compared with this set
   * @return     true if the parameter set and this set contain
   *             exactly the same elements
   */
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
   * @return  the integer number of elements in this set
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
  
  /**
   * Creates a new array to store the contents of the set with
   * twice the capacity of the old one.
   */
  private void expandCapacity()
  {
    T[] larger = (T[])(new Object[contents.length*2]);
    
    for (int index=0; index < contents.length; index++)
      larger[index] = contents[index];
    
    contents = larger;
  }
}

