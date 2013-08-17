public class SortingandSearching  
{

   //-----------------------------------------------------------------
   //  Sorts the specified array of objects using the quick sort
   // algorithm.
   //-----------------------------------------------------------------
   public static void quickSort (Comparable[] data, int min, int max)
   {
      int indexofpartition;

      if (max - min  > 0)
      {
         // Create partitions
         indexofpartition = findPartition(data, min, max);

         // Sort the left side
         quickSort(data, min, indexofpartition - 1);

         // Sort the right side
         quickSort(data, indexofpartition + 1, max);
      }
   }


   //-----------------------------------------------------------------
   //  private Find Partition method used by the quick sort
   // algorithm.
   //-----------------------------------------------------------------
   private static int findPartition (Comparable[] data, int min, int max)
   {
      int left, right;
      Comparable temp, partitionelement;

      //use the first element as the partition element
      partitionelement = data[min];

      left = min;
      right = max;
   
      while (left<right)
      {
     
         // search for an element that is > the partitionelement

         while (data[left].compareTo(partitionelement) <=0 &&
                            left < right)
               left++;

         //search for an element that is < the partitionelement

         while (data[right].compareTo(partitionelement) > 0)
               right--;

         //swap the elements 

         if (left<right)
         {
            temp = data[left];
            data[left] = data[right];
            data[right] = temp;
         }
      }

      //move partition element to partition index
      temp = data[min];
      data[min] = data[right];
      data[right] = temp;
         
      return right;
}


   //-----------------------------------------------------------------
   //  Sorts the specified array of objects using the merge sort
   // algorithm.
   //-----------------------------------------------------------------
   public static void mergeSort (Comparable[] data, int min, int max)
   {
        Comparable temp[];
        int index1, left, right;


        //return on list of length one
        if (min==max)
            return; 

        //find the length and the midpoint of the list 
        int size = max - min + 1;
        int pivot = (min + max) / 2;

        temp = new Comparable[size];

        //sort left half of list
        mergeSort(data, min, pivot);

        //sort right half of list
        mergeSort(data, pivot + 1, max);

        //copy sorted data into workspace
        for (index1 = 0; index1 < size; index1++)
           temp[index1] = data[min + index1];

        //merge the two sorted lists
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


   //-----------------------------------------------------------------
   //  Sorts the specified array of objects using a bubble sort
   //  algorithm.
   //-----------------------------------------------------------------
   public static void bubbleSort (Comparable[] data)
   {
      int position, scan;
      Comparable temp;

      for (position =  data.length - 1; position >= 0; position--)
      {
         for (scan = 0; scan <= position - 1; scan++)
         {
            if (data[scan].compareTo(data[scan+1]) > 0)
            {
                // Swap the values
                temp = data[scan];
                data[scan] = data[scan + 1];
                data[scan + 1] = temp;
            }
         }
      }
   }

   //-----------------------------------------------------------------
   //  Searches the specified array of objects using a linear search
   //  algorithm.
   //-----------------------------------------------------------------
   public static boolean linearSearch (Comparable[] data, 
							   Comparable target)
   {
      int index = 0;
      boolean found = false;

      while (!found && index <= data.length) 
      {
         if (data[index].compareTo(target) == 0) 
            found = true;
         index++;
      }

      return found;
   }

   //-----------------------------------------------------------------
   //  Searches the specified array of objects using a binary search
   //  algorithm.
   //-----------------------------------------------------------------
   public static boolean binarySearch (Comparable[] data, int min, 
                                       int max, Comparable target)
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


   //-----------------------------------------------------------------
   //  Sorts the specified array of integers using the selection
   //  sort algorithm.
   //-----------------------------------------------------------------
   public static void selectionSort (Comparable[] data)
   {
      int min;
	 Comparable temp;
      for (int index = 0; index < data.length-1; index++)
      {
         min = index;
         for (int scan = index+1; scan < data.length; scan++)
            if (data[scan].compareTo(data[min])<0)
               min = scan;

         // Swap the values
         temp = data[min];
         data[min] = data[index];
         data[index] = temp;
      }
   }
   //-----------------------------------------------------------------
   //  Sorts the specified array of objects using an insertion
   //  sort algorithm.
   //-----------------------------------------------------------------
   public static void insertionSort (Comparable[] data)
   {
      for (int index = 1; index < data.length; index++)
      {
         Comparable key = data[index];
         int position = index;

         // Shift larger values to the right
         while (position > 0 && data[position-1].compareTo(key) > 0)
         {
            data[position] = data[position-1];
            position--;
         }
            
         data[position] = key;
      }
   }

}

