/**
 * CircularArrayQueue represents an array implementation of a queue in 
 * which the indexes for the front and rear of the queue circle back to 0
 * when they reach the end of the array.
 * 
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0 08/12/08
 */

package jss2;
import jss2.exceptions.*;
import java.util.Iterator;

public class CircularArrayQueue<T> implements QueueADT<T>
{
  private final int DEFAULT_CAPACITY = 100;
  private int front, rear, count;
  private T[] queue; 
  
  /**
   * Creates an empty queue using the default capacity.
   */
  public CircularArrayQueue()
  {
    front = rear = count = 0;
    queue = (T[]) (new Object[DEFAULT_CAPACITY]);
  }
  
  /**
   * Creates an empty queue using the specified capacity.
   * 
   * @param initialCapacity  the integer representation of the initial
   *                         size of the circular array queue
   */
  public CircularArrayQueue (int initialCapacity)
  {
    front = rear = count = 0;
    queue = ( (T[])(new Object[initialCapacity]) );
  }
  
  /**
   * Adds the specified element to the rear of this queue, expanding
   * the capacity of the queue array if necessary.
   * 
   * @param element  the element to add to the rear of the queue
   */
  public void enqueue (T element)
  {
    if (size() == queue.length) 
        expandCapacity();
    
    queue[rear] = element;
    rear = (rear+1) % queue.length;
    
    count++;
  }
  
  /**
   * Removes the element at the front of this queue and returns a
   * reference to it. Throws an EmptyCollectionException if the
   * queue is empty.
   *
   * @return                           the reference to the element at the front
   *                                   of the queue that was removed
   * @throws EmptyCollectionException  if an empty collections exception occurs
   */
  public T dequeue() throws EmptyCollectionException
  {
    if (isEmpty())
        throw new EmptyCollectionException ("queue");
    
    T result = queue[front];
    queue[front] = null;
    front = (front+1) % queue.length;
    
    count--;
    
    return result;
  }
  
  /**
   * Returns a reference to the element at the front of this queue.
   * The element is not removed from the queue.  Throws an
   * EmptyCollectionException if the queue is empty.
   *
   * @return                           a reference to the first element in the 
   *                                   queue
   * @throws EmptyCollectionException  if an empty collections exception occurs
   */
  public T first() throws EmptyCollectionException
  {
     // left as programming project
  }
  
  /**
   * Returns true if this queue is empty and false otherwise.
   * 
   * @return  returns true if this queue is empty and false if otherwise
   */
  public boolean isEmpty()
  {
     // left as programming project
  }
  
  /**
   * Returns the number of elements currently in this queue.
   * 
   * @return  the integer representation of the size of this queue
   */
  public int size()
  {
     // left as programming project
  }
  
  /**
   * Returns a string representation of this queue. 
   *
   * @return  the string representation of this queue
   */
  public String toString()
  {
     // left as programming project
  }

  /**
   * Creates a new array to store the contents of this queue with
   * twice the capacity of the old one.
   */
  public void expandCapacity()
  {
    T[] larger = (T[])(new Object[queue.length *2]);
    
    for(int scan=0; scan < count; scan++)
    {
      larger[scan] = queue[front];
      front=(front+1) % queue.length;
    }
    
    front = 0;
    rear = count;
    queue = larger;
  }
}
