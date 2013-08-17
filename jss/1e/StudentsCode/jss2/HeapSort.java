//*******************************************************************
//
// HeapSort.java		Authors: Lewis/Chase
//
//*******************************************************************

package jss2;

public class HeapSort 
{

   //================================================================
   //  sorts a given array of Comparable objects using a heap
   //================================================================
   public static void HeapSort(Comparable[] data, int min, int max) 
   {
      Heap temp = new Heap();

      //copy the array into a heap

      for (int ct=min; ct<=max; ct++)
         temp.addElement(data[ct]);

      //place the sorted elements back into the array

      int count=min;
      while (!(temp.isEmpty()))
      {
         data[count] = temp.removeMin();
         count++;
      }

   }  // method HeapSort

} //class HeapSort

