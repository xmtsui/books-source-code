//********************************************************************
//   Josephus.java             Authors: Lewis/Chase
//********************************************************************
import java.util.ArrayList;
import java.util.Scanner;

public class Josephus    
{
   /******************************************************************
     Continue around the circle eliminating every nth soldier 
     until all of the soldiers have been eliminated.
   ******************************************************************/
   public static void main ( String[] args)
   {
      int numPeople, gap, newGap, counter;
      ArrayList<Integer> list = new ArrayList<Integer>();
      Scanner in = new Scanner(System.in);

      /** get the initial number of soldiers */
      System.out.println("Enter the number of soldiers: ");
      numPeople = in.nextInt();
      in.nextLine();

      /** get the gap between soldiers */
      System.out.println("Enter the gap between soldiers: ");
      gap = in.nextInt();

      /** load the initial list of soldiers */
      for (int count=1; count <= numPeople; count++)
      {
         list.add(new Integer(count));
      }
      counter = gap - 1; 
      newGap = gap;
      System.out.println("The order is: ");
      
      /**  Treating the list as circular, remove every nth element
           until the list is empty */
      while (!(list.isEmpty())) 
      {
         System.out.println(list.remove(counter));
         numPeople = numPeople - 1;
         if (numPeople > 0)
            counter = (counter + gap - 1) % numPeople;
      }
   }
}
