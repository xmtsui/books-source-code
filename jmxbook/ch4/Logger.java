package jmxbook.ch4;

import javax.management.*;
import java.io.*;
import java.util.*;

public class Logger implements LoggerMBean, MBeanRegistration
{
  public static final int ALL = 3;
  public static final int ERRORS = 2;
  public static final int NONE = 1;
  private PrintWriter out = null;
  private int logLevel = Logger.ALL;
  private MBeanServer server = null;

  public Logger()
  {

    try
    {
      out = new PrintWriter( new FileOutputStream( "record.log" ) );
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }

  }

  public void setLogLevel( int level )
  {
    logLevel = level;
  }

  public int getLogLevel()
  {
    return logLevel;
  }

  public String retrieveLog( int linesback )
  {
    //implementation here
    return null;
  }

  public void writeLog( String message, int type )
  {

    try
    {
      if( type <= logLevel )
      out.println( message );
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }

  }

  public void postDeregister() {}
  public void postRegister( Boolean registrationDone ) {}
  public void preDeregister() {}

  public ObjectName preRegister( MBeanServer server, ObjectName name )
  {
    this.server = server;

    try
    {
      ObjectName name1 = new ObjectName( "JMXBookAgent:name=props" );
      Object[] params = { "loglevel" };
      String[] sig = { "java.lang.String" };
      String value = ( String ) server.invoke( name1, "getProperty", params, sig );

      logLevel = Integer.parseInt( value );
    }
    catch( Exception e )
    {
      e.printStackTrace();
      logLevel = 0;
    }

    return name;
  }

}//class

