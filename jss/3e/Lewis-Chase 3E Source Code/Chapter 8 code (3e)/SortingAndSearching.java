/**
 * SortingAndSearching demonstrates sorting and searching on an array 
 * of objects.
 *
 * @author Dr. Chase
 * @author Dr. Lewis
 * @version 1.0, 8/18/08 */

public class SortingAndSearching 
{
   /**
    * Sorts the specified array of objects using the quick sort
    * algorithm.
    * 
    * @param data  the array to be sorted
    * @param min   the integer representation of the minimum value   
    * @param max   the integer representation of the maximum value
    */
   public static <T extends Comparable<? super T>> void quickSort (T[] data, int min, int max)
   {
      int indexofpartition;

      if (max - min  > 0)
      {
         /** Create partitions */
         indexofpartition = findPartition(data, min, max);

         /** Sort the left side */
         quickSort(data, min, indexofpartition - 1);

         /** Sort the right side */
         quickSort(data, indexofpartition + 1, max);
      }
   }

   /**
    * Used by the quick sort algorithm to find the partition.
    *
    * @param data  the array to be sorted
    * @param min   the integer representation of the minimum value  
    * @param max   the integer representation of the maximum value
    */
   private static <T extends Comparable<? super T>> int findPartition (T[] data, int min, int max)
   {
      int left, right;
      T temp, partitionelement;
      int middle = (min + max)/2;

      partitionelement = data[middle]; // use middle element as partition
      left = min;
      right = max;
   
      while (left<right)
      {
         /** search for an element that is > the partitionelement */
         while (data[left].compareTo(partitionelement) <=0 &&
                            left < right)
            left++;

         /** search for an element that is < the partitionelement */
         while (data[right].compareTo(partitionelement) > 0)
            right--;

         /** swap the elements  */
         if (left<right)
         {
            temp = data[left];
            data[left] = data[right];
            data[right] = temp;
         }
      }

      /** move partition element to partition index*/
      temp = data[min];
      data[min] = data[right];
      data[right] = temp;
         
      return right;
   }

   /**
    * Sorts the specified array of objects using the merge sort
    * algorithm.
    *
    * @param data  the array to be sorted
    * @param min   the integer representation of the minimum value 
    * @param max   the integer representation of the maximum value
    */
   public static <T extends Comparable<? super T>> void mergeSort (T[] data, int min, int max)
   {
      T[] temp;
      int index1, left, right;

      /** return on list of length one */
      if (min==max)
         return; 

      /** find the length and the midpoint of the list */
      int size = max - min + 1;
      int pivot = (min + max) / 2;
      temp = (T[])(new Comparable[size]);
      
      mergeSort(data, min, pivot); // sort left half of list
      mergeSort(data, pivot + 1, max); // sort right half of list

      /** copy sorted data into workspace */
      for (index1 = 0; index1 < size; index1++)
         temp[index1] = data[min + index1];
      
      /** merge the two sorted lists */
      left = 0;
      right = pivot - min + 1;
      for (index1 = 0; index1 < size; index1++)
      {
         if (right <= max - min)
            if (left <= pivot - min)
               if (temp[left].compareTo(temp[right]) > 0)
                  data[index1 + min] = temp[right++];
               
               else
                  data[index1 + min] = temp[left++];
            else
               data[index1 + min] = temp[right++];
         else
            data[index1 + min] = temp[left++];
      }
   }

   /**
    * Sorts the specified array of objects using a bubble sort
    * algorithm.
    *
    * @param data  the array to be sorted
    */
   public static <T extends Comparable<? super T>> void bubbleSort (T[] data)
   {
      int position, scan;
      T temp;

      for (position =  data.length - 1; position >= 0; position--)
      {
         for (scan = 0; scan <= position - 1; scan++)
         {
            if (data[scan].compareTo(data[scan+1]) > 0)
            {
               /** Swap the values */
               temp = data[scan];
               data[scan] = data[scan + 1];
               data[scan + 1] = temp;
            }
         }
      }
   }

   /**
    * Searches the specified array of objects using a linear search
    * algorithm.
    *
    * @param data    the array to be sorted
    * @param min     the integer representation of the minimum value 
    * @param max     the integer representation of the maximum value
    * @param target  the element being searched for
    * @return        true if the desired element is found
    */
   public static <T extends Comparable<? super T>>  boolean linearSearch (T[] data, int min, int max, T target)
   {
      int index = min;
      boolean found = false;

      while (!found && index <= max) 
      {
         if (data[index].compareTo(target) == 0) 
            found = true;
         index++;
      }

      return found;
   }

   /**
    * Searches the specified array of objects using a binary search
    * algorithm.
    *
    * @param data    the array to be sorted
    * @param min     the integer representation of the minimum value 
    * @param max     the integer representation of the maximum value
    * @param target  the element being searched for 
    * @return        true if the desired element is found
    */
   public static <T extends Comparable<? super T>>  boolean binarySearch (T[] data, int min, int max, T target)
   {  
      boolean found = false;
      int midpoint = (min + max) / 2;  // determine the midpoint

      if (data[midpoint].compareTo(target) == 0)
         found = true;

      else if (data[midpoint].compareTo(target) > 0)
      {
         if (min <= midpoint - 1)
            found = binarySearch(data, min, midpoint - 1, target);
      }
      
      else if (midpoint + 1 <= max)
         found = binarySearch(data, midpoint + 1, max, target);

      return found;
   }

   /**
    * Sorts the specified array of integers using the selection
    * sort algorithm.
    *
    * @param data  the array to be sorted
    */
    
   public static <T extends Comparable<? super T>> void selectionSort (T[] data)
   {
      int min;
      T temp;
      
      for (int index = 0; index < data.length-1; index++)
      {
         min = index;
         for (int scan = index+1; scan < data.length; scan++)
            if (data[scan].compareTo(data[min])<0)
               min = scan;

         /** Swap the values */
         temp = data[min];
         data[min] = data[index];
         data[index] = temp;
      }
   }

   /**
    * Sorts the specified array of objects using an insertion
    * sort algorithm.
    *
    * @param data  the array to be sorted
    */
   public static <T extends Comparable<? super T>> void insertionSort (T[] data)
   {
      for (int index = 1; index < data.length; index++)
      {
         T key = data[index];
         int position = index;

         /** Shift larger values to the right */
         while (position > 0 && data[position-1].compareTo(key) > 0)
         {
            data[position] = data[position-1];
            position--;
         }
            
         data[position] = key;
      }
   }
}

