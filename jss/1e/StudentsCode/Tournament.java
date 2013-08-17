//===================================================================
//  Tournament.java       Authors: Lewis/Chase
//===================================================================
public class Tournament    
{
   //----------------------------------------------------------------
   //  Determines and prints the tournament organization.
   //----------------------------------------------------------------
   public static void main (String[] args ) 
   {
	try 
	{
	   TournamentMaker temp = new TournamentMaker();
	   temp.make();
	}
	catch (Exception IOException)
	{
	   System.out.println("Invalid input reported");
	}
   } 
}

