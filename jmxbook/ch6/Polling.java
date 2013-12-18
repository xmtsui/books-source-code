package jmxbook.ch6;

import javax.management.*;

public class Polling extends NotificationBroadcasterSupport implements PollingMBean, Runnable
{
  private boolean stop = true;
  private int index = 0;
  private long interval = 1000;

  public Polling()
  {
  }

  public void setInterval( long interval )
  {
    long temp = this.interval;
    this.interval = interval;
    AttributeChangeNotification notif = new AttributeChangeNotification( this, 0, System.currentTimeMillis(), "Attribute Change", "interval", "long", new Long( temp ), new Long( interval ) );
    sendNotification( notif );
  }

  public void start()
  {
    try
    {
      stop = false;
      Thread t = new Thread( this );
      t.start();
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }

  public void stop()
  {
    stop = true;
  }

  public void run()
  {
    while( !stop )
    {
      try
      {
        Thread.sleep( interval );
        System.out.println( "Polling" );
      }
      catch( Exception e )
      {
        e.printStackTrace();
      }
      Notification notif = new Notification( "ch6.PollingMBean.counter", this, index++ );
      sendNotification( notif );
    }//while
  }

  public MBeanNotificationInfo[] getNotificationInfo()
  {
    String[] type = { "ch6.PollingMBean.counter" };
    String[] attChanges = { AttributeChangeNotification.ATTRIBUTE_CHANGE };
    MBeanNotificationInfo[] info = new MBeanNotificationInfo[ 2 ];
    info[ 0 ] = new MBeanNotificationInfo( type, "javax.management.Notification", "The Polling MBean counter" );
    info[ 1 ] = new MBeanNotificationInfo( attChanges, "javax.management.AttributeChangeNotification", "The Polling MBean counter" );
    return info;
  }

}//class

