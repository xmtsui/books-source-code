//===================================================================
//  TournamentMaker.java       Authors: Lewis/Chase
//===================================================================


import jss2.*;
import jss2.exceptions.*;
import java.util.StringTokenizer;
import java.io.*;

public class TournamentMaker    
{
   //----------------------------------------------------------------
   //  Gets the next token from the input stream.
   //----------------------------------------------------------------
   private String get_next_token() throws IOException
   {
      String temptoken, instring;
      StringTokenizer tokenizer;
      BufferedReader in = new 
         BufferedReader( new InputStreamReader(System.in));

      instring = in.readLine();
      tokenizer = new StringTokenizer(instring);
      temptoken = (tokenizer.nextToken());
      return temptoken;
   }

   //----------------------------------------------------------------
   //  Determines and prints the tournament organization.
   //----------------------------------------------------------------
   public void make ( ) throws IOException
   {
      ArrayOrderedList tournament = new ArrayOrderedList();
      String team1, team2, teamname;
      int numwins, numteams = 0;

      System.out.println("Tournament Maker");

      while (((numteams % 2) != 0) || (numteams == 0))
      {
         System.out.println ("Enter the number of teams (must be even):");
         numteams = Integer.parseInt(get_next_token());
      }

      System.out.println ("Enter " + numteams + " team names and number of wins:");
      System.out.println("Teams may be entered in any order ");

      for (int count=1; count <= numteams; count++) 
      {
         System.out.println("Enter team name: ");
         teamname = get_next_token();
         System.out.println("Enter number of wins: ");
         numwins = Integer.parseInt(get_next_token());
         tournament.add(new Team(teamname, numwins));
       }

       System.out.println("The first round mathchups are: ");
       
       for (int count=1; count <=(numteams/2); count++)
       {
          team1 = ((Team)(tournament.removeFirst())).teamname;
          team2 = ((Team)(tournament.removeLast())).teamname;
          System.out.println ("Game " + count + " is " + team1 +
             " against " + team2);
          System.out.println ("with the winner to play the winner of game "
             + (((numteams/2)+1) - count));
       }

    }
} 


