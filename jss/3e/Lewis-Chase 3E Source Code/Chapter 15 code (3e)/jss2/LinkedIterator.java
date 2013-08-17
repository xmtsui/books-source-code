/**
 * LinkedIterator represents an iterator for a linked list of linear nodes.
 *
 * @author Dr. Chase
 * @author Dr. Lewis
 * @version 1.0, 9/21/2008
 */

package jss2;
import jss2.exceptions.*;
import java.util.*;
    
public class LinkedIterator<T> implements Iterator<T>
{
   private int count;  // the number of elements in the collection
   private LinearNode<T> current;  // the current position
    
    /**
     * Sets up this iterator using the specified items.
     *
     * @param collection  the collection the iterator is to be 
     *                    created for
     * @param size        the size of the collection
     */
    public LinkedIterator (LinearNode<T> collection, int size)
    {
       current = collection;
       count = size;
    }
 
    /**
     * Returns true if this iterator has at least one more element
     * to deliver in the iteration.
     *
     * @return  true if this iterator has at least one more element
     */
    public boolean hasNext()
    {
       return (current!= null);
    }
 
    /**
     * Returns the next element in this iteration. Throws a 
     * NoSuchElementException if there are no more elements in 
     * the iteration.
     *
     * @return                         the next element in this iteration
     * @throws NoSuchElementException  if a no such element exception
     *                                 occurs
     */
    public T next() throws NoSuchElementException
    {
       if (! hasNext())
          throw new NoSuchElementException();
 
       T result = current.getElement();
       current = current.getNext();
       return result;
    }
 
    /**
     * The remove operation is not supported.
     *
     * @throws UnsupportedOperationException  if an unsupported operation
     *                                        exception occurs
     */
    public void remove() throws UnsupportedOperationException
    {
       throw new UnsupportedOperationException();
    }
}
