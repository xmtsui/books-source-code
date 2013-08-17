/**
 * Team represents a team.
 * 
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 08/12/08
 */

import java.util.*; 
    
class Team implements Comparable<Team>
{
   public String teamName; 
   private int wins; 

   /**
    * Sets up this team with the specfied information.
    *
    * @param name     the string representation of the name of this team
    * @param numWins  the integer representation of the number of wins for this
    *                 team 
    */
   public Team (String name, int numWins)
   {
      teamName = name;
      wins = numWins;
   }

   /**
    * Returns the name of the given team.
    *
    * @return  the string representation of the name of this team
    */
   public String getName ()
   {
      return teamName;
   }

   /**
    * Compares number of wins of this team to another team. Returns 
    * -1, 0, or 1 for less than, equal to, or greater than.
    *
    * @param other  the team to compare wins against this team
    * @return       the integer representation of the result of this comparison,
    *               valid values are -1. 0, 1, for less than, equal to, and
    *               greater than.
    */
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

   /**
    * Returns the name of the team.
    * 
    * @return  the string representation of the name of this team
    */
   public String toString()
   {
     return teamName;
   }
}
