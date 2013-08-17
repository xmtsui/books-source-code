//************************************************************
//  Hand.java             Authors:  Lewis/Chase
//  
//  Provides an implementation of a hand of cards using a 
//  set to represent the cards.
//************************************************************
import jss2.*;
import jss2.exceptions.*;
import java.util.*;

public class Hand implements Iterable
{
  protected ArraySet<Card> inHand;
  protected int handValue,count;
  
  /***********************************************************
    Constructs a hand of Cards.
  ***********************************************************/
  public Hand()
  {
    inHand = new ArraySet<Card>(12);
    handValue=0;
    count =0;
  }
  
  /***********************************************************
    Reduces hand value when newCard makes player go over 
    21 and there is an ace in the hand.
  ***********************************************************/
  private void reduceHand(Card newCard)
  {
    if((handValue) > 21)
    {
      if(aceInHand())
        handValue -= 10;
    }
  }
  
  /***********************************************************
    Checks to see if there is an ace in this hand.
  ***********************************************************/
  private boolean aceInHand()
  {
    boolean result = false;
    Card cardChk = null;
    Iterator<Card> scan = inHand.iterator();

    while (scan.hasNext() && !result)
    {
      cardChk = scan.next();
      if(cardChk.getValue() == 11)
      {
        cardChk.setValue(1);
        result = true;
      }
    }
    
    return result;
  }
  
  /***********************************************************
    Adds a new card from the specified deck to this hand.
  ***********************************************************/
  public Card newCard(Deck currentDeck)
  {
    Card result;
    
    result = currentDeck.getCard();
    inHand.add(result);
    handValue+=result.getValue();
    reduceHand(result);
    count++;
    
    return result;
  }
  
  /***********************************************************
    Returns the value of this hand.
  ***********************************************************/
  public int getHandValue()
  {
    return handValue;
  }

  /***********************************************************
    Returns an iterator over this hand.
  ***********************************************************/
  public Iterator<Card> iterator()
  {
    return inHand.iterator();
  }

  /***********************************************************
    Removes a card from this hand.
  ***********************************************************/
  public Card remove(Card crd) throws ElementNotFoundException
  {
    return(inHand.remove(crd));
  }

  /***********************************************************
    Returns a string representation of this hand.
  ***********************************************************/
  public String toString()
  {
    String result="";
    Card cardStr = null;
    int i=0;
    Iterator<Card> scan = inHand.iterator();
    
    while (scan.hasNext())
    {
      cardStr= scan.next();
      result += "card"+ i +": "+cardStr.getValue()+"\n";
      i++;
    }
    
    return result;
  }
}
