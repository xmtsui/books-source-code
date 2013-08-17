
//********************************************************************
//  Bingo.java       Authors: Lewis/Chase
//
//  Demonstrates the use of a bag collection.
//********************************************************************

import jss2.ArrayBag;

public class Bingo
{
   //-----------------------------------------------------------------
   //  Creates all 75 bingo balls and stores them in a bag. Then
   //  pulls several balls from the bag at random and prints them.
   //-----------------------------------------------------------------
   public static void main (String[] args)
   {
      final int NUM_BALLS = 75, NUM_PULLS = 10;

      ArrayBag bingoBag = new ArrayBag();
      BingoBall ball;

      for (int num = 1; num <= NUM_BALLS; num++)
      {
         ball = new BingoBall (num);
         bingoBag.add (ball);
      }

      System.out.println ("Size: " + bingoBag.size());
      System.out.println ();

      for (int num = 1; num <= NUM_PULLS; num++)
      {
         ball = (BingoBall) bingoBag.removeRandom();
         System.out.println (ball);
      }
   }
}

