/**
 * TowersOfHanoi represents the classic Towers of Hanoi puzzle.
 *
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 8/18/08
 */

public class TowersOfHanoi
{
   private int totalDisks;

   /**
    * Sets up the puzzle with the specified number of disks.
    *
    * @param disks  the number of disks to start the towers puzzle with
    */
   public TowersOfHanoi (int disks)
   {
      totalDisks = disks;
   }

   /**
    * Performs the initial call to moveTower to solve the puzzle.
    * Moves the disks from tower 1 to tower 3 using tower 2.
    */
   public void solve ()
   {
      moveTower (totalDisks, 1, 3, 2);
   }
   
   /**
    * Moves the specified number of disks from one tower to another
    * by moving a subtower of n-1 disks out of the way, moving one
    * disk, then moving the subtower back. Base case of 1 disk.
    *
    * @param numDisks  the number of disks to move
    * @param start     the starting tower
    * @param end       the ending tower
    * @param temp      the temporary tower
    */
   private void moveTower (int numDisks, int start, int end, int temp)
   {
      if (numDisks == 1)
         moveOneDisk (start, end);
      else
      {
         moveTower (numDisks-1, start, temp, end);
         moveOneDisk (start, end);
         moveTower (numDisks-1, temp, end, start);
      }
   }

   /**
    * Prints instructions to move one disk from the specified start
    * tower to the specified end tower.
    *
    * @param start  the starting tower
    * @param end    the ending tower
    */
   private void moveOneDisk (int start, int end)
   {
      System.out.println ("Move one disk from " + start + " to " +
                          end);
   }
}
