//*******************************************************************
//  Team.java                      Authors: Lewis/Chase
//
//  Represents a team.
//*******************************************************************
import java.util.*; 
    
class Team implements Comparable<Team>
{
   public String teamName; 
   private int wins; 

   /*****************************************************************
     Sets up this team with the specfied information.
   *****************************************************************/
   public Team (String name, int numWins)
   {
      teamName = name;
      wins = numWins;
   }

   /*****************************************************************
     Returns the name of the given team.
   *****************************************************************/
   public String getName ()
   {
      return teamName;
   }

   /*****************************************************************
     Compares number of wins of this team to another team. Returns 
     -1, 0, or 1 for less than, equal to, or greater than.
   *****************************************************************/
   public int compareTo (Team other)
   {
      if (this.wins < other.wins)
          return -1;
      else
        if (this.wins == other.wins)
           return 0;
        else
           return 1;
   }

   /*****************************************************************
     Returns the name of the team.
   *****************************************************************/
   public String toString()
   {
     return teamName;
   }
}
