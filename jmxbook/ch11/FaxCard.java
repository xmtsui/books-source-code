package jmxbook.ch11;

public class FaxCard implements FaxCardMBean
{

  public FaxCard(){}

  public void disable()
  {
    System.out.println( "FaxCardMBean::The FaxCard has been disabled" );
  }

}

