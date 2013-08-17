//************************************************************
//  Card.java                      Authors:  Lewis/Chase
//
//  Provides an implementation of a class to represent a
//  playing card.
//************************************************************
import java.util.Random;
import javax.swing.*;

public class Card
{
  protected String face;
  protected ImageIcon cardPic;
  protected int value; 
  protected String suit;
  
  /***********************************************************
    Constructs a card.
  ***********************************************************/
  public Card()
  {
    cardPic = null;
    value = 0;
    suit = null;
    face = null;
  }
  
  /***********************************************************
    Draws the shape of this card.
  ***********************************************************/
  public Card(ImageIcon x, int val, String s, String f)
  {
    cardPic = x;
    value = val;
    face = f;
    suit = s;
  }
  
  /***********************************************************
    Returns the image of this card.
  ***********************************************************/
  public ImageIcon getImage()
  {
    return cardPic;
  }
  
  /***********************************************************
    Returns the value of this card.
  ***********************************************************/
  public int getValue()
  {
    return value;
  }
  
  /***********************************************************
    Allows the user to set the value of this card.
  ***********************************************************/
  public void setValue(int v)
  {
    value = v;
  }

  /***********************************************************
     Returns the suit of this card.
  ***********************************************************/
  public String getSuit()
  {
    return suit;
  }

  /***********************************************************
     Returns the face of this card.
  ***********************************************************/
  public String getFace()
  {
    return face;
  }
  
  /***********************************************************
     Returns a string representation this card.
  ***********************************************************/
  public String toString()
  {
    return "Face: "+ face + " Suit"+ suit +" Value: "+ value;
  }
}
