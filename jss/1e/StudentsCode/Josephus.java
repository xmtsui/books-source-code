//===================================================================
//   JosephusLists.java       Authors: Lewis/Chase
//===================================================================

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.*;

public class Josephus    
{

//==============================================================
//=     Continue around the circle eliminating every nth soldier 
//=     until all of the soldiers have been eliminated 
//==============================================================
    public static void main ( String[] args) throws IOException
    {
       String instring;
       int numpeople, gap, newgap, counter;
       StringTokenizer tokenizer;
       ArrayList list = new ArrayList();
       Object tempelement;
       BufferedReader in = new 
          BufferedReader( new InputStreamReader(System.in));

       // get the initial number of soldiers
       System.out.println("Enter the number of soldiers: ");
       instring = in.readLine();
       tokenizer = new StringTokenizer(instring);
       numpeople = Integer.parseInt (tokenizer.nextToken());

       // get the gap between soldiers
       System.out.println("Enter the gap between soldiers: ");
       instring = in.readLine();
       tokenizer = new StringTokenizer(instring);
       gap = Integer.parseInt (tokenizer.nextToken());

       // load the initial list of soldiers
       for (int count=1; count <= numpeople; count++)
       {
         list.add(new Integer(count));
       }
       counter = gap - 1; 
       newgap = gap;

       //  Treating the list as circular, remove every nth element
       //  until the list is empty 

       System.out.println("The order is: ");


       while (!(list.isEmpty())) 
       {
          tempelement = list.remove(counter);
          numpeople = numpeople - 1;
          if (numpeople > 0)
             counter = (counter + gap - 1) % numpeople;
          System.out.println(tempelement);
       }
   }
}


