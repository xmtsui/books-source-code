/**
 * TournamentMaker demonstrates first round of tournament team match-ups 
 * using an ordered list.
 *
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 08/12/08
 */

import jss2.*;
import jss2.exceptions.*;
import java.util.Scanner;
import java.io.*;
import java.lang.*;

public class TournamentMaker    
{
   /**
    * Determines and prints the tournament organization.
    *
    * @throws IOException  if an IO exception is encountered
    */
   public void make ( ) throws IOException
   {
      ArrayOrderedList<Team> tournament = new ArrayOrderedList<Team>();
      String team1, team2, teamName;
      int numWins, numTeams = 0;
      double checkInput=-1;

      Scanner in = new Scanner(System.in);

      System.out.println("Tournament Maker\n");

      while (((numTeams % 2) != 0) || (numTeams == 2) || (checkInput!=0))
      {
         System.out.println ("Enter the number of teams (must be an even \n" +
                      "number valid for a single elimination tournament):");
         numTeams = in.nextInt();
         in.nextLine();// advance beyond new line char
         
         /** checks if numTeams is valid for single elimination tournament */
         checkInput = (Math.log(numTeams)/Math.log(2)) % 1;
      }

      System.out.println ("\nEnter " + numTeams + " team names and number of wins.");
      System.out.println("Teams may be entered in any order.");

      for (int count=1; count <= numTeams; count++) 
      {
         System.out.println("Enter team name: ");
         teamName = in.nextLine();
         System.out.println("Enter number of wins: ");
         numWins = in.nextInt();
         in.nextLine();// advance beyond new line char
         tournament.add(new Team(teamName, numWins));
       }

       System.out.println("\nThe first round mathchups are: ");
       
       for (int count=1; count <=(numTeams/2); count++)
       {
          team1 = (tournament.removeFirst()).getName();
          team2 = (tournament.removeLast()).getName();
          System.out.println ("Game " + count + " is " + team1 +
             " against " + team2);
          System.out.println ("with the winner to play the winner of game "
             + (((numTeams/2)+1) - count) + "\n") ;
       }
    }
}
