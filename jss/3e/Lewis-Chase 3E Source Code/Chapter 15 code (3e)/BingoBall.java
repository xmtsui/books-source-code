/**
 * BingoBall represents a ball used in a Bingo game.
 *
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 9/21/2008
 */

public class BingoBall
{
  private char letter;
  private int number;
  
  /**
   * Sets up this Bingo ball with the specified number and the
   * appropriate letter.
   *
   * @param num  the number to be applied to the new bingo ball
   */
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
  
  /**
   * Returns a string representation of this bingo ball.
   *
   * @return  a string representation of the bingo ball
   */
  public String toString ()
  {
    return (letter + " " + number);
  }
}
