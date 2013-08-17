//===================================================================
// Team.java       Authors: Lewis/Chase
//                   Mods : Davis
//===================================================================

import java.util.*;	
	
class Team implements Comparable 
{
   public String teamname; 
   private int wins; 

   //----------------------------------------------------------------
   //  Sets up this team with the specfied information.
   //----------------------------------------------------------------
   public Team (String name, int numwins)
   {
      teamname = name;
      wins = numwins;
   }

   //----------------------------------------------------------------
   //  Returns -1, 0, 1 for less = >.
   //----------------------------------------------------------------
   public int compareTo (Object other)
   {
      if (this.wins < (((Team)other).wins))
          return -1;
      else
        if (this.wins == (((Team)other).wins))
           return 0;
        else
           return 1;
   }

   // returns the name of the team
   public String toString()
   {
     return teamname;
   }
}


