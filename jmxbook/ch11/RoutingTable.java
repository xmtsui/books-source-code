package jmxbook.ch11;

public class RoutingTable implements RoutingTableMBean
{

  public RoutingTable(){}

  public void removePhoneRoute(Integer cardNum)
  {
    System.out.println( "RoutingTableMBean::PhoneCard" + cardNum.intValue() + " removed from " + " routing table");
  }

  public void removeFaxRoute()
  {
    System.out.println( "RoutingTableMBean::FaxCard removed " + " from routing table");
  }

}

