/**  
 * SetADT defines the interface to a set collection.
 *
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 9/21/2008
 */

package jss2;
import java.util.Iterator;

public interface SetADT<T>
{
  /** 
   * Adds one element to this set, ignoring duplicates. 
   *
   * @param element  the element to be added to this set
   */
  public void add (T element);
  
  /** 
   * Removes and returns a random element from this set. 
   * 
   * @return  a random element from this set
   */
  public T removeRandom ();
  
  /** 
   * Removes and returns the specified element from this set. 
   *
   * @param element  the element to be removed from this list
   * @return         the element just removed from this list
   */
  public T remove (T element);
  
  /**  
   * Returns the union of this set and the parameter 
   * 
   * @param set  the set to be unioned with this set
   * @return     a set that is the union of this set and the parameter
   */
  public SetADT<T> union (SetADT<T> set);
  
  /**  
   * Returns true if this set contains the parameter 
   *
   * @param target  the element being sought in this set
   * @return        true if this set contains the parameter
   */
  public boolean contains (T target);
  
  /**  
   * Returns true if this set and the parameter contain exactly
   * the same elements 
   *
   * @param set  the set to be compared with this set
   * @return     true if this set and the parameter contain exactly
   *             the same elements
   */
  public boolean equals (SetADT<T> set);
  
  /**  
   * Returns true if this set contains no elements 
   *
   * @return  true if this set contains no elements
   */
  public boolean isEmpty();
  
  /**  
   * Returns the number of elements in this set 
   *
   * @return  the interger number of elements in this set
   */
  public int size();
  
  /**  
   * Returns an iterator for the elements in this set 
   *
   * @return  an iterator for the elements in this set
   */
  public Iterator<T> iterator();
  
  /**  
   * Returns a string representation of this set 
   * 
   * @return  a string representation of this set
   */
  public String toString();
}
