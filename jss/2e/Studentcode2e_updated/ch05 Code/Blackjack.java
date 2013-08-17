//************************************************************
//  Blackjack.java		Authors: Lewis/Chase
//
//  The BlackJack class provides an implementation of a single
//  deck blackjack game.  It makes use of the Hand class to 
//  represent a player's hand and the Deck class to represent
//  the deck of cards for the game.
//*************************************************************
import jss2.exceptions.*;
import java.util.*;

public class Blackjack
{
  Hand dealer;    //to hold the dealer's cards
  Hand player;    //to hold the player's cards
  Deck newDeck;   //a set of cards

  /*************************************************************
    Creates dealer, player and deck.
  *************************************************************/
  public Blackjack(Hand dlr, Hand plr)
  {
    dealer = dlr;
    player = plr;
    newDeck = new Deck();
  }

  /*************************************************************
    Deals the intitial cards to each player.
  *************************************************************/
  public void dealInitialCards()            
  { 
    dealer.newCard(newDeck);
    dealer.newCard(newDeck);
    player.newCard(newDeck);
    player.newCard(newDeck);
  }

  /*************************************************************
    Adds the next random card from the deck to the given 
    player's hand.
  *************************************************************/
  public Card hit(Hand whoHit)
  {
    Card result = whoHit.newCard(newDeck);

    return result;
  }

  /*************************************************************
    Returns the value of the given player's hand.
  *************************************************************/
  public int handValue(Hand whoHand)
  {
    int result = whoHand.getHandValue();

    return result;
  }

  /*************************************************************
    Discards a given card from the given player's hand. 
    Throws an ElementNotFoundException if the card is not 
    in the hand.
  *************************************************************/
  public void discard(Hand whoDis, Card discrd) 
                      throws ElementNotFoundException
  {
    Card card=null;
    boolean found = false;
    Iterator<Card> scan = whoDis.iterator();
    while (scan.hasNext() && !found)
    {
        card = scan.next();
        if(discrd.equals(card))
        {
          whoDis.remove(card);
          found = true;
        }
    }
    if(!found)
        throw new ElementNotFoundException("BlackJack");
  }

  /***********************************************************
    Tests to see if the player's hand has a value of 21.
  ***********************************************************/
  public boolean blackj()
  {
    boolean result = false;

    if(player.getHandValue() == 21)
      result = true;

    return result;
  }

  /***********************************************************
    Tests a given player's hand to see if they have 
    gone over 21.
  ***********************************************************/
  public boolean bust(Hand whoBust)
  {
    boolean result = false;

    if(whoBust.getHandValue() > 21)
      result = true;

    return result;
  }

  /***********************************************************
     Adds cards to the dealer's hand until the value 
     is greater than or equal to 16.
  ***********************************************************/
  public Hand dealerPlays()
  {
    Hand result = dealer;

    while(dealer.getHandValue() <= 16)
    {
      dealer.newCard(newDeck);
    }

    return result;
  }
  
  /***********************************************************
    Determines the winner of the game.
  ***********************************************************/
  public String winner()
  {
    String result = "";
    if((player.getHandValue() < dealer.getHandValue()) && 
                 dealer.getHandValue() <= 21 )                 
      result = "Lose";
    else if ((player.getHandValue() == dealer.getHandValue())
              && dealer.getHandValue() <= 21 )
      result = "Push";
    else
      result = "Win";

    return result;
  }
}
