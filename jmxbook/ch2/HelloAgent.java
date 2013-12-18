package jmxbook.ch2;

import javax.management.*;
import com.sun.jdmk.comm.HtmlAdaptorServer;

public class HelloAgent implements NotificationListener
{
  private MBeanServer mbs = null;

  public HelloAgent ( )
  {
    mbs = MBeanServerFactory.createMBeanServer( "HelloAgent" );
    HtmlAdaptorServer adapter = new HtmlAdaptorServer();
    HelloWorld hw = new HelloWorld();
    ObjectName adapterName = null;
    ObjectName helloWorldName = null;

    try
    {
      adapterName = new ObjectName( "HelloAgent:name=htmladapter,port=9092" );
      mbs.registerMBean( adapter, adapterName );
      adapter.setPort( 9092 );
      adapter.start();
      helloWorldName = new ObjectName( "HelloAgent:name=helloWorld1" );
      mbs.registerMBean( hw, helloWorldName );
      hw.addNotificationListener( this, null, null );
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }

  }//constructor

  public void handleNotification( Notification notif, Object handback )
  {
    System.out.println( "Receiving notification..." );
    System.out.println( notif.getType() );
    System.out.println( notif.getMessage() );
  }

  public static void main( String args[] )
  {
    HelloAgent agent = new HelloAgent();
  }

}//class

