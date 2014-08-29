package jmxbook.ch12;

import jmxbook.ch3.*;
import javax.management.*;
import com.sun.jdmk.comm.*;
import java.util.*;

public class TimerSetup implements NotificationListener
{

  public TimerSetup()
  {
    try
    {
      RmiConnectorClient client = RMIClientFactory.getClient();
      ObjectName timerName = new ObjectName( "JMXBookAgent:name=timer");
      Object[] args2 = { "ch12.timer.periodic", "message", "data", new Date(), new Long( 6000 ) };
      String[] sig2 = { "java.lang.String", "java.lang.String", "java.lang.Object", "java.util.Date", "long" };
      client.invoke( timerName, "addNotification", args2, sig2 );
      Object[] args3 = { "ch12.timer.periodic20", "message", "data", new Date(), new Long( 2000 ), new Long( 20 ) };
      String[] sig3 = { "java.lang.String", "java.lang.String", "java.lang.Object", "java.util.Date", "long", "long" };
      client.invoke( timerName, "addNotification", args3, sig3 );
      client.addNotificationListener( timerName, this, null, null );
    }
    catch( Exception e )
    {
      ExceptionUtil.printException( e );
    }

  }

  public void handleNotification( Notification not, Object obj )
  {
    String type = not.getType();
    System.out.println( type );
  }

  public static void main( String args[] )
  {
    TimerSetup setup = new TimerSetup();
  }

}

