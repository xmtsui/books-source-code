package jmxbook.ch11;

public class PhoneCard implements PhoneCardMBean
{
  private int cardNum=0;

  public PhoneCard( int cardNum )
  {
    this.cardNum = cardNum;
  }

  public void disable()
  {
    System.out.println( "PhoneCardMBean::PhoneCard #" + cardNum+" has been disabled" );
  }

}

