/**
 * HeapSort sorts a given array of Comparable objects using a heap.
 * 
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 8/19/08
 */

package jss2;

public class HeapSort<T>
{

  /**
   * @param data  the data to be added to the heapsort
   * @param min   the integer minimum value 
   * @param max   the integer maximum value
   */
   public void HeapSort(T[] data, int min, int max) 
   {
      ArrayHeap<T> temp = new ArrayHeap<T>();

      /** copy the array into a heap */
      for (int ct = min; ct <= max; ct++)
         temp.addElement(data[ct]);


      /** place the sorted elements back into the array */
      int count = min;
      while (!(temp.isEmpty()))
      {
         data[count] = temp.removeMin();
         count++;
      }
   }
}


