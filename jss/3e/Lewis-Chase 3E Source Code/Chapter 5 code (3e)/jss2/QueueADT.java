/** * QueueADT defines the interface to a queue collection.
 *
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 8/12/08
 */
package jss2;public interface QueueADT<T>{
   /**  
    * Adds one element to the rear of this queue. 
    * 
    * @param element  the element to be added to the rear of this queue  
    */   public void enqueue (T element);   /**  
    * Removes and returns the element at the front of this queue.
    * 
    * @return  the element at the front of this queue
    */   public T dequeue();   /**  
    * Returns without removing the element at the front of this queue.
    *
    * @return  the first element in this queue
    */   public T first();      /**  
    * Returns true if this queue contains no elements.
    * 
    * @return  true if this queue is empty
    */   public boolean isEmpty();   /**  
    * Returns the number of elements in this queue. 
    * 
    * @return  the integer representation of the size of this queue
    */   public int size();   /**  
    * Returns a string representation of this queue. 
    *
    * @return  the string representation of this queue
    */   public String toString();}
