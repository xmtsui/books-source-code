/**
 * Coin represents a coin with two sides that can be flipped.
 * 
 * @author Lewis
 * @author Loftus
 * @version 1.0, 8/18/08
 */

public class Coin
{
   private final int HEADS = 0;
   private final int TAILS = 1;

   private int face;

   /**
    * Sets up the coin by flipping it initially.
    */
   public Coin ()
   {
      flip();
   }

   /**
    * Flips the coin by randomly choosing a face value.
    */
   public void flip ()
   {
      face = (int) (Math.random() * 2);
   }

   /**
    * Returns true if the current face of the coin is heads.
    *
    * @return  true if the face is heads
    */
   public boolean isHeads ()
   {
      return (face == HEADS);
   }

   /**
    * Returns the current face of the coin as a string.
    * 
    * @return  the string representation of the current face value of this coin    
    */
   public String toString()
   {
      String faceName;

      if (face == HEADS)
         faceName = "Heads";
      else
         faceName = "Tails";

      return faceName;
   }
}

