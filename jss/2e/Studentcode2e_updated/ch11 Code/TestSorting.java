//*********************************************************************
//  TestSorting.java                 Authors: Lewis/Chase
//
//  Driver to test SortingAndSearching class.
//*********************************************************************

public class TestSorting
{
   public static void main(String[] args)
   {
      Integer[] x = {new Integer(5),new Integer(3), new Integer(9),
                     new Integer(2),new Integer(7), new Integer(1)};
      Integer[] y;
      y = x;
      SortingAndSearching<Integer> sorts = new SortingAndSearching<Integer>();
      sorts.insertionSort(y);
      
      System.out.println(sorts.linearSearch(x,0,5,new Integer(3)));
      System.out.println(sorts.linearSearch(x,0,5,new Integer(0)));
      System.out.println(sorts.binarySearch(y,0,5,new Integer(3)));
      System.out.println(sorts.binarySearch(y,0,5,new Integer(0)));
   }
}
