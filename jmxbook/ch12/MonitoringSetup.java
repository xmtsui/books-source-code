package jmxbook.ch12;

import jmxbook.ch3.*;
import javax.management.*;
import javax.management.monitor.*;
import com.sun.jdmk.comm.*;

public class MonitoringSetup implements NotificationListener
{

  public MonitoringSetup()
  {
    try
    {
      RmiConnectorClient client = RMIClientFactory.getClient();
      ObjectName sm = new ObjectName( "JMXBookAgent:name=string");
      client.createMBean( "javax.management.monitor.StringMonitor", sm);
      client.addNotificationListener( sm, this, null, null );
      ObjectName gm = new ObjectName( "JMXBookAgent:name=gauge");
      client.createMBean( "javax.management.monitor.GaugeMonitor", gm );
      client.addNotificationListener( gm, this, null, null );
      ObjectName cm = new ObjectName( "JMXBookAgent:name=counter");
      client.createMBean( "javax.management.monitor.CounterMonitor", cm );
      client.addNotificationListener( cm, this, null, null );
      ObjectName oo = new ObjectName( "JMXBookAgent:name=subject");
      client.createMBean( "jmxbook.ch12.ObservableObject", oo );
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
    MonitoringSetup setup = new MonitoringSetup();
  }

}


