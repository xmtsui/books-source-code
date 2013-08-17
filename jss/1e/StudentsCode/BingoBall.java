
//********************************************************************
//  BingoBall.java       Authors: Lewis/Chase
//
//  Represents a ball used in a BINGO game.
//********************************************************************

public class BingoBall
{
   private char letter;
   private int number;

   //-----------------------------------------------------------------
   //  Sets up this BINGO ball wth the specified number and the
   //  appropriate letter.
   //-----------------------------------------------------------------
   public BingoBall (int num)
   {
      number = num;

      if (num <= 15)
         letter = 'B';
      else
         if (num <= 30)
           letter = 'I';
         else
            if (num <= 45)
               letter = 'N';
            else
               if (num <= 60)
                  letter = 'G';
               else
                  letter = 'O';
   }

   //-----------------------------------------------------------------
   //  Returns a string representation of this BINGO ball.
   //-----------------------------------------------------------------
   public String toString ()
   {
      return (letter + " " + number);
   }
}

