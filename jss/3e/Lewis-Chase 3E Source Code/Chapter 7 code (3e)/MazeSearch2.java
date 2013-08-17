/**
 * MazeSearch demonstrates recursion.
 *
 * @author Dr. Chase
 * @author Dr. Lewis
 * @version 1.0, 8/18/08
 */

public class MazeSearch2
{
   /**
    * Creates a new maze, prints its original form, attempts to
    * solve it, and prints out its final form.
    */
   public static void main (String[] args)
   {
      Maze2 labyrinth = new Maze2();
      
      System.out.println (labyrinth);

      if (labyrinth.traverse (0, 0))
         System.out.println ("The maze was successfully traversed!");
      else
         System.out.println ("There is no possible path.");

      System.out.println (labyrinth);
   }
}
