package jmxbook.ch9;

import java.net.*;
import javax.management.*;
import java.io.*;
import java.lang.reflect.*;
import java.util.*;

public class TCPAdapter implements Runnable
{
  private MBeanServer server = null;
  private Socket socket = null;
  private BufferedReader in = null;
  private PrintWriter out = null;
  public static String SHUTDOWN = "shutdown";
  public static String CREATE_MBEAN = "create mbean";
  public static String GET_ATTRIBUTE = "get_attribute";
  public static String SET_ATTRIBUTE = "set_attribute";
  public static String INVOKE = "invoke";
  public static String ARGS = "args";

  public TCPAdapter( Socket socket, MBeanServer server )
  {
    this.socket = socket;
    this.server = server;

    try
    {
      this.out = new PrintWriter( socket.getOutputStream() );
      this.in = new BufferedReader(
      new InputStreamReader( socket.getInputStream() ) );
      System.out.println( "TCP Adapter CREATED" );
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }

  }

  public void run()
  {
    try
    {
      System.out.println( "TCP adapter starting..." );
      String line = in.readLine();
      while( !line.equals( SHUTDOWN ) )
      {

        if( line.equals( CREATE_MBEAN ) )
        {
          try
          {
            createMBean( );
            out.println( "SUCCESS" );
            out.flush();
          }
          catch( Exception e )
          {
            e.printStackTrace();
            out.println( "ERROR " + e.getMessage() );
            out.flush();
          }
        }

        else if( line.equals( GET_ATTRIBUTE ) )
        {
          try
          {
            out.println( getAttribute( ) );
            out.flush();
          }
          catch( Exception e )
          {
            e.printStackTrace();
            out.println( "ERROR " + e.getMessage() );
            out.flush();
          }
        }

        else if( line.equals( SET_ATTRIBUTE ) )
        {
          try
          {
            setAttribute( );
            out.println( "SUCCESS" );
            out.flush();
          }
          catch( Exception e )
          {
            e.printStackTrace();
            out.println( "ERROR " + e.getMessage() );
            out.flush();
          }
        }

        else if( line.equals( INVOKE ) )
        {
          try
          {
            out.println( invoke() );
            out.flush();
          }
          catch( Exception e )
          {
            e.printStackTrace();
            out.println( "ERROR " + e.getMessage() );
            out.flush();
          }
        }

        line = in.readLine();
      }

      in.close();
      out.close();
      socket.close();
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }

  }

  private void createMBean() throws Exception
  {
    String classname = null;
    String objectName = null;
    String line = in.readLine();
    String arglist = null;
    String siglist = null;

    classname = line;
    objectName = in.readLine();
    line = in.readLine();

    if( line.equals( ARGS ) )
    {
      arglist = in.readLine();
      siglist = in.readLine();
    }

    String[] sig = createSignature( siglist );
    Object[] args = createObjectList( arglist, sig );

    System.out.println( "NOW CREATING MBEAN" );
    server.createMBean( classname, new ObjectName( objectName ), args, sig );
  }

  private String getAttribute() throws Exception
  {
    String attname = null;
    String objectName = null;
    String line = in.readLine();
    attname = line;
    objectName = in.readLine();

    System.out.println( "GETTING ATTRIBUTE " + attname + " FROM " + objectName );
    Object obj = server.getAttribute( new ObjectName( objectName ), attname );
    return obj.toString();
  }

  private void setAttribute() throws Exception
  {
    String attName = null;
    String objectName = null;
    String line = in.readLine();
    String arglist = null;
    String siglist = null;

    attName = line;
    objectName = in.readLine();
    line = in.readLine();
    arglist = in.readLine();
    siglist = in.readLine();
    String[] sig = createSignature( siglist );
    Object[] args = createObjectList( arglist, sig );
    System.out.println( "SETTING ATTRIBUTE " + attName + " FROM " + objectName );
    server.setAttribute( new ObjectName( objectName ), new Attribute( attName, args[0] ) );
  }

  private String invoke() throws Exception
  {
    String operation = null;
    String objectName = null;
    String line = in.readLine();
    String arglist = null;
    String siglist = null;

    operation = line;
    objectName = in.readLine();
    line = in.readLine();

    if( line.equals( ARGS ) )
    {
      arglist = in.readLine();
      siglist = in.readLine();
    }

    String[] sig = createSignature( siglist );
    Object[] args = createObjectList( arglist, sig );

    System.out.println( "INVOKING OPERATION " + operation + " FROM " + objectName );
    Object result = server.invoke( new ObjectName( objectName ), operation, args, sig );
    return result.toString();
  }

  private String[] createSignature( String siglist )
  {
    if( siglist == null ) return null;

    StringTokenizer toks = new StringTokenizer( siglist, "," );
    String[] result = new String[ toks.countTokens() ];
    int i = 0;

    while( toks.hasMoreTokens() )
    {
      result[ i++ ] = toks.nextToken();
    }
    return result;
  }

  private Object[] createObjectList( String objects, String[] sig ) throws Exception
  {
    if( objects == null ) return null;

    Object[] results = new Object[ sig.length ];
    StringTokenizer toks = new StringTokenizer( objects, "," );
    int i = 0;

    while( toks.hasMoreTokens() )
    {
      String object = toks.nextToken();
      Class conSig[] = { Class.forName( sig[i] ) };
      Object[] conParams = { object };
      Class c = Class.forName( sig[i] );
      Constructor con = c.getConstructor( conSig );
      results[ i ] = con.newInstance( conParams );
      i++;
    }

    return results;
  }
}

