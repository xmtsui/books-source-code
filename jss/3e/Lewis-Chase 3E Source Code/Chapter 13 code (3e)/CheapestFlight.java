/**
 * CheapestFlight demonstrates a simulation of an airline scheduling system 
 * using a weighted graph data structure.
 *
 * @author Dr. Chase
 * @author Dr. Lewis
 * @version 1.0, 9/16/2008
 */


import java.io.*;
import java.util.*;
import java.text.*;
import jss2.*;

public class CheapestFlight
{
   /**
    * Creates a new network and populates it with data from a file 
    * which contains the cost of flights to various cities. The user
    * enters two cities and the program returns the cheapest path
    * between the two cities.
    */
   public static void main (String[] args)
   {
      String file = "flights.txt";
      
      try 
      {
         BufferedReader inFile = new BufferedReader
                                (new FileReader(file));
         BufferedReader keyboard = new BufferedReader
                                  (new InputStreamReader(System.in));
         Network<String> cities = new Network<String>();
         StringTokenizer tokenizer;
         String line, city1, city2;
         double cost;
         int numCities;
      
         /** Read in the list of cities from the input file and add
             add them to the network.  List of cities will be 
             terminated by a blank line. */
         line = inFile.readLine();
         while (line.length() != 0)
         {
            cities.addVertex(line);
            line = inFile.readLine();
         }

         /** Read in the connections between cities from the input
             file and add them to the network. */
         line = inFile.readLine();
         while (line != null)
         {
            tokenizer = new StringTokenizer(line, "\t\n");
            city1 = tokenizer.nextToken();
            city2 = tokenizer.nextToken();
            cost = Double.parseDouble(tokenizer.nextToken());
            cities.addEdge(city1, city2, cost);
            line = inFile.readLine();
         }

         /** Display the list of cities */
         numCities = cities.size();
         Iterator<String> cityIterator = cities.iteratorBFS(0);
         
         System.out.println("Cities");
         System.out.println("------");
         while (cityIterator.hasNext())
         {
            System.out.println(cityIterator.next());
         }
         
         /** Prompt the user to enter two cities */
         System.out.print("\nCity 1: ");
         city1 = keyboard.readLine();
         System.out.print("\nCity 2: ");
         city2 = keyboard.readLine();
         
         /** Display the shortest path between the two cities and
             how much it would cost */
         cost = cities.shortestPathWeight(city1, city2);
         if (cost < Double.POSITIVE_INFINITY)
         {
            System.out.print("\nThe cheapest path from " + city1 + 
                             " to " + city2 + " is ");
            NumberFormat money = NumberFormat.getCurrencyInstance();
            System.out.println(money.format(cities.shortestPathWeight
                              (city1, city2)));
            System.out.print("You would have to travel from ");
            Iterator<String> it = cities.iteratorShortestPath(city1, city2);
            
            while (it.hasNext()) 
            {
               System.out.println(it.next());
               if (it.hasNext())
                  System.out.print(" to ");
            }
         }
         else
            System.out.println("\nThere is no path from " + city1 + 
                               " to " + city2);
      }

      catch (FileNotFoundException e) {System.out.println("file " + file + 
                                       " not found");}
      catch (IOException e) {System.out.println("IO exception");}
   }
}

