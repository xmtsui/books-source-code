package jmxbook.ch9;

import javax.management.*;
import java.net.*;

public class TCPServer implements TCPServerMBean, MBeanRegistration, Runnable
{
  private int port = 1555;
  private boolean stopped = false;
  private ServerSocket ss = null;
  private MBeanServer mbs = null;

  public TCPServer()
  {
  }

  public void setPort( int port )
  {
    this.port = port;
  }

  public int getPort()
  {
    return port;
  }

  public boolean start()
  {
    stopped = false;
    Thread t = new Thread( this );
    t.start();
    return true;
  }

  public boolean stop()
  {
    stopped = true;
    return true;
  }

  public void run()
  {
    try
    {
      System.out.println( "Binding to port: " + port );
      ss = new ServerSocket( port );
      while( !stopped )
      {
        Socket client = ss.accept();
        System.out.println( "Client being accepted" );
        Thread t = new Thread( new TCPAdapter( client, mbs ) );
        t.start();
      }
      ss.close();
    }
    catch( Exception e )
    {
      e.printStackTrace();
      stopped = true;
    }
  }

  public void postDeregister() {}
  public void postRegister( Boolean done ) {}
  public void preDeregister() {}

  public ObjectName preRegister( MBeanServer server, ObjectName name )
  {
    this.mbs = server;
    return name;
  }

}

