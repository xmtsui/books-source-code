package jmxbook.ch10;

import javax.management.*;
import com.sun.jdmk.comm.*;
import jmxbook.ch3.*;

public class MLetWrapperSetup implements NotificationListener
{

  public MLetWrapperSetup()
  {
    try
    {
      RmiConnectorClient client = RMIClientFactory.getClient();
      Object[] args = { "JMXBookAgent:name=mlet" };
      String[] sig = { "java.lang.String" };
      ObjectName wrapperName = new ObjectName( "JMXBookAgent:name=mletwrapper");
      client.createMBean( "jmxbook.ch10.MLetWrapper", wrapperName, args, sig );
      client.addNotificationListener( wrapperName, this, null, null );
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
    MLetWrapperSetup setup = new MLetWrapperSetup();
  }

}

