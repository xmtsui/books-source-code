/**
 * SolveTowers demonstrates recursion.
 *
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 8/18/08
 */

public class SolveTowers
{
   /**
    * Creates a TowersOfHanoi puzzle and solves it.
    */
   public static void main (String[] args)
   {
      TowersOfHanoi towers = new TowersOfHanoi (4);
      towers.solve();
   }
}
