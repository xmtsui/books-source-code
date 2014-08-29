package jmxbook.ch10;

import javax.management.*;
import javax.management.loading.*;
import java.util.*;
import java.io.*;
import java.net.*;

public class MLetWrapper extends NotificationBroadcasterSupport implements MLetWrapperMBean, MBeanRegistration
{
  private ObjectName mletName = null;
  private MBeanServer mbs = null;

  public MLetWrapper( String mletServiceName )
  {
    try
    {
      this.mletName = new ObjectName( mletServiceName );
    }
    catch( Exception e )
    {
      //do nothing
    }
  }

  //
  //MBeanRegistration methods
  public void postDeregister(){}
  public void preDeregister(){}

  public ObjectName preRegister( MBeanServer server, ObjectName name ) throws Exception
  {
    this.mbs = server;
    //check for the existence of the M-let service.
    if( mletName == null || !mbs.isRegistered( mletName )  )
    throw new Exception( "M-let service not present in MBean Server" );
    return name;
  }

  public void postRegister( Boolean done ){}


  //MLetMBean methods
  public Set getMBeansFromURL( String url ) throws ServiceNotFoundException
  {
    Set rvalue = null;
    MLetNotification notif = null;
    Object[] obj = { url };
    String[] sig = { "java.lang.String" };

    try
    {
      rvalue = ( Set ) mbs.invoke( mletName, "getMBeansFromURL", obj, sig );
      notif = new MLetNotification( MLetNotification.MBEAN_TYPE, this, -1 );
      notif.setURL( url );
      notif.setObjectInstances( rvalue );
      sendNotification( notif );
      return rvalue;
    }
    catch( Exception e )
    {
      notif = new MLetNotification( MLetNotification.ERROR_TYPE,
      this, -1 );
      notif.setURL( url );
      sendNotification( notif );
      throw new ServiceNotFoundException( e.getMessage() );
    }

  }

  public Set getMBeansFromURL( URL url ) throws ServiceNotFoundException
  {
    Set rvalue = null;
    MLetNotification notif = null;
    Object[] obj = { url };
    String[] sig = { "java.net.URL" };

    try
    {
      rvalue = ( Set ) mbs.invoke( mletName, "getMBeansFromURL", obj, sig );
      notif = new MLetNotification( MLetNotification.MBEAN_TYPE, this, -1 );
      notif.setURL( url.toString() );
      notif.setObjectInstances( rvalue );
      sendNotification( notif );
      return rvalue;
    }
    catch( Exception e )
    {
      notif = new MLetNotification( MLetNotification.ERROR_TYPE, this, -1 );
      notif.setURL( url.toString() );
      sendNotification( notif );
      throw new ServiceNotFoundException( e.getMessage() );
    }

  }

  public void addURL( String url ) throws ServiceNotFoundException
  {
    Set rvalue = null;
    MLetNotification notif = null;
    Object[] obj = { url };
    String[] sig = { "java.lang.String" };

    try
    {
      mbs.invoke( mletName, "addURL", obj, sig );
      notif = new MLetNotification( MLetNotification.URL_TYPE, this, -1 );
      notif.setURL( url );
      sendNotification( notif );
    }
    catch( Exception e )
    {
      notif = new MLetNotification( MLetNotification.ERROR_TYPE, this, -1 );
      notif.setURL( url );
      sendNotification( notif );
    }

  }

  public void addURL( URL url )
  {
    Set rvalue = null;
    MLetNotification notif = null;
    Object[] obj = { url };
    String[] sig = { "java.net.URL" };

    try
    {
      mbs.invoke( mletName, "addURL", obj, sig );
      notif = new MLetNotification( MLetNotification.URL_TYPE, this, -1 );
      notif.setURL( url.toString() );
      sendNotification( notif );
    }
    catch( Exception e )
    {
      notif = new MLetNotification( MLetNotification.ERROR_TYPE, this, -1 );
      notif.setURL( url.toString() );
      sendNotification( notif );
    }

  }

  public URL[] getURLs()
  {
    try
    {
      return ( URL[] )mbs.invoke( mletName, "getURLs", null, null );
    }
    catch( Exception e )
    {
      //do something
      return null;
    }
  }

  public URL getResource( String name )
  {
    Object[] obj = { name };
    String[] sig = { "java.lang.String" };

    try
    {
      return ( URL ) mbs.invoke( mletName, "getResource", obj, sig );
    }
    catch( Exception e )
    {
      //do something
      return null;
    }

  }

  public InputStream getResourceAsStream( String name )
  {
    Object[] obj = { name };
    String[] sig = { "java.lang.String" };

    try
    {
      return ( InputStream ) mbs.invoke( mletName, "getResourceAsStream", obj, sig );
    }
    catch( Exception e )
    {
      //do something
      return null;
    }

  }

  public Enumeration getResources( String name )
  {
    Object[] obj = { name };
    String[] sig = { "java.lang.String" };

    try
    {
      return ( Enumeration ) mbs.invoke( mletName, "getResources", obj, sig );
    }
    catch( Exception e )
    {
      //do something
      return null;
    }

  }

  public String getLibraryDirectory()
  {
    try
    {
      return ( String ) mbs.invoke( mletName, "getLibraryDirectory", null, null );
    }
    catch( Exception e )
    {
      //do something
      return null;
    }
  }

  public void setLibraryDirectory( String path )
  {
    Object[] obj = { path };
    String[] sig = { "java.lang.String" };
    try
    {
      mbs.invoke( mletName, "setLibraryDirectory", obj, sig );
    }
    catch( Exception e )
    {
      //do something
    }
  }

  public MBeanNotificationInfo[] getNotificationInfo()
  {
    MBeanNotificationInfo[] info = new MBeanNotificationInfo[ 1 ];
    String[] types = { MLetNotification.MBEAN_TYPE, MLetNotification.URL_TYPE, MLetNotification.ERROR_TYPE };
    info[ 0 ] = new MBeanNotificationInfo( types, "jmxbook.ch10.MLetNotification", "Notifications from the MLetWrapper" );
    return info;
  }

}

