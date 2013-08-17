//********************************************************************
//  Bingo.java       Authors: Lewis/Chase
//
//  Demonstrates the use of a set collection.
//********************************************************************
import jss2.ArraySet;

public class Bingo
{
  /******************************************************************
    Creates all 75 Bingo balls and stores them in a set. Then
    pulls several balls from the set at random and prints them.
  *******************************************************************/
  public static void main (String[] args)
  {
    final int NUM_BALLS = 75, NUM_PULLS = 10;
    ArraySet<BingoBall> bingoSet = new ArraySet<BingoBall>();
    BingoBall ball;
    
    for (int num = 1; num <= NUM_BALLS; num++)
    {
      ball = new BingoBall (num);
      bingoSet.add (ball);
    }
    
    System.out.println ("Size: " + bingoSet.size());
    System.out.println ();
    
    for (int num = 1; num <= NUM_PULLS; num++)
    {
      ball = bingoSet.removeRandom();
      System.out.println (ball);
    }
  }
}
