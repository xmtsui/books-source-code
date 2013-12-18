package jmxbook.ch3;

import com.sun.jdmk.comm.*;
import javax.management.*;
/*----------[ Added in Chapter 9 ]----------------*/
import jmxbook.ch9.TCPServer;
import jmxbook.ch9.JINIServer;
import java.rmi.*;
/*----------[ Added in Chapter 9 ]----------------*/

/*----------[ Added in Chapter 12 ]----------------*/
import javax.management.timer.Timer;
/*----------[ Added in Chapter 12 ]----------------*/

public class JMXBookAgent
{
  private MBeanServer server = null;

  public JMXBookAgent()
  {
    System.out.println("\n\tCREATE the MBeanServer.");
    server = MBeanServerFactory.createMBeanServer("JMXBookAgent");
    startHTMLAdapter();
    startRMIConnector();
    /*----------[ Added in Chapter 9 ]----------------*/
    startTCPAdapter();
    startJINIConnector();
    /*----------[ Added in Chapter 9 ]----------------*/

    /*----------[ Added in Chapter 10 ]----------------*/
    startMletService();
    /*----------[ Added in Chapter 10 ]----------------*/

    /*----------[ Added in Chapter 11 ]----------------*/
    createRelationService();
    /*----------[ Added in Chapter 11 ]----------------*/

    /*----------[ Added in Chapter 12 ]----------------*/
    startTimerService();
    /*----------[ Added in Chapter 12 ]----------------*/
  }

  protected void startHTMLAdapter()
  {
    HtmlAdaptorServer adapter = new HtmlAdaptorServer();
    ObjectName adapterName = null;

    try
    {
      adapter.setPort( 9092 );
      //create the HTML adapter
      adapterName = new ObjectName( "JMXBookAgent:name=html,port=9092" );
      server.registerMBean( adapter, adapterName );
      adapter.start();
    }
    catch(Exception e)
    {
      ExceptionUtil.printException( e );
      System.out.println("Error Starting HTML Adapter for Agent");
    }

  }


  protected void startRMIConnector()
  {
    RmiConnectorServer connector = new RmiConnectorServer();
    ObjectName connectorName = null;

    try
    {
      connector.setPort( 2099 );
      connectorName = new ObjectName( "JMXBookAgent:name=RMIConnector" );
      server.registerMBean( connector, connectorName );
      connector.start();
    }
    catch(Exception e)
    {
      ExceptionUtil.printException( e );
    }

  }

  public static void main(String[] args)
  {
    System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println("\n>>> START of JMXBook Agent");
    System.out.println("\n>>> CREATE the agent...");
    JMXBookAgent agent = new JMXBookAgent();
    System.out.println("\nAgent is Ready for Service...\n");
  }

  /*----------[ Added in Chapter 9 ]----------------*/
  protected void startTCPAdapter()
  {
    TCPServer tcp = new TCPServer();
    ObjectName adapterName = null;
    try
    {
      adapterName = new ObjectName( "JMXBookAgent:name=TCPAdapter" );
      server.registerMBean( tcp, adapterName );
      tcp.start();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  protected void startJINIConnector()
  {
/*
    ObjectName connectorName = null;

    try
    {
      System.setSecurityManager( new RMISecurityManager() );
      JINIServer jini = new JINIServer();
      ObjectName jiniName = null;
      jiniName = new ObjectName( "JMXBookAgent:name=JINIConnector" );
      server.registerMBean( jini, jiniName );
      jini.enableConnections();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
*/

  }
  /*----------[ Added in Chapter 9 ]----------------*/

  /*----------[ Added in Chapter 10 ]----------------*/
  protected void startMletService()
  {
    ObjectName mletName = null;
    try
    {
      mletName = new ObjectName( "JMXBookAgent:name=mlet" );
      server.createMBean( "javax.management.loading.MLet", mletName );
    }
    catch( Exception e )
    {
      ExceptionUtil.printException( e );
    }
  }
  /*----------[ Added in Chapter 10 ]----------------*/


  /*----------[ Added in Chapter 11 ]----------------*/
  public void createRelationService()
  {
    ObjectName relationServiceName = null;
    try
    {
      relationServiceName = new ObjectName( "JMXBookAgent:name=relationService" );

      Object[] params = new Object[1];
      params[0] = new Boolean(true);
      String[] signature = new String[1];
      signature[0] = "boolean";

      server.createMBean( "javax.management.relation." +"RelationService", relationServiceName, params, signature);
    }
    catch(Exception e)
    {
      System.out.println("Error Creating the Relation" + " Service MBean");
      e.printStackTrace();
    }

  }
  /*----------[ Added in Chapter 11 ]----------------*/

  /*----------[ Added in Chapter 12 ]----------------*/
  protected void startTimerService()
  {
    Timer timer = new Timer();
    ObjectName timerName = null;

    try
    {
      timerName =
      new ObjectName( "JMXBookAgent:name=timer" );
      server.registerMBean( timer, timerName );
      timer.setSendPastNotifications( true );
      //start timer
      timer.start();
    }
    catch( Exception e )
    {
      ExceptionUtil.printException( e );
    }

  }
  /*----------[ Added in Chapter 12 ]----------------*/


}

