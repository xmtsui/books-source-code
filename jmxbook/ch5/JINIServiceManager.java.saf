package jmxbook.ch5;

import net.jini.core.entry.*;
import net.jini.discovery.*;
import net.jini.core.lookup.*;
import java.util.*;
import javax.management.*;
import java.rmi.*;
import java.lang.reflect.*;

public class JINIServiceManager implements DynamicMBean
{
  private ManagedJINIService serviceRef = null;
  private String jiniInterfaceName = null;
  private Entry initialAttribute = null;
  private String serviceName = null;

  public JINIServiceManager(Entry att )
  {
    jiniInterfaceName = "jmxbook.ch5.ManagedJINIService";
    serviceRef = ( jmxbook.ch5.ManagedJINIService )
    lookUpService();
    initialAttribute = att;
  }

  public Object getAttribute( String name ) throws MBeanException, AttributeNotFoundException, ReflectionException
  {
    throw new AttributeNotFoundException( name );
  }

  public AttributeList getAttributes( String[] names )
  {
    AttributeList rvalue = new AttributeList();
    return rvalue;
  }

  public void setAttribute( Attribute att ) throws MBeanException, AttributeNotFoundException, ReflectionException, InvalidAttributeValueException
  {
    throw new AttributeNotFoundException( "No attributes can be set" );
  }

  public AttributeList setAttributes( AttributeList list )
  {
    AttributeList rvalue = new AttributeList();
    return rvalue;
  }

  public Object invoke( String actionName, Object args[], String sig[] ) throws MBeanException, ReflectionException
  {

    try
    {
      String methodName = actionName;
      Class types[] = new Class[ sig.length ];
      for( int i = 0; i < types.length; i++ )
        types[ i ] = Class.forName( sig[ i ] );
      Method m = serviceRef.getClass().getMethod( methodName, types );
      Object temp = m.invoke( serviceRef, args );
      return temp;
    }
    catch( Exception e )
    {
      throw new MBeanException( e );
    }
  }

  public MBeanInfo getMBeanInfo()
  {
    MBeanConstructorInfo[] cons = new MBeanConstructorInfo[ 1 ];
    MBeanNotificationInfo[] nots = null;
    MBeanAttributeInfo[] atts = null;
    MBeanOperationInfo[] ops = new MBeanOperationInfo[ 2 ];

    try
    {
      Class conargs[] = { Class.forName( "java.lang.String" ),
      Class.forName( "net.jini.core.entry.Entry" ) };
      MBeanConstructorInfo cinfo =
      new MBeanConstructorInfo( "Main constructor",
      this.getClass().getConstructor( conargs ) );
      cons[ 0 ] = cinfo;
    }
    catch( Exception e ){}

    MBeanParameterInfo[] sig0 = new MBeanParameterInfo[ 1 ];
    sig0[ 0 ] = new MBeanParameterInfo( "entries", "java.util.Vector", "Entries to Add" );
    ops[ 0 ] = new MBeanOperationInfo( "addEntries", "Used to add service attributes", sig0, "void", MBeanOperationInfo.ACTION );
    MBeanParameterInfo[] sig1 = new MBeanParameterInfo[ 2 ];
    sig1[ 0 ] = new MBeanParameterInfo( "oldEntries", "java.util.Vector", "Old Entries to modify" );
    sig1[ 1 ] = new MBeanParameterInfo( "newEntries", "java.util.Vector", "New Entries" );
    ops[ 1 ] = new MBeanOperationInfo( "modifyEntries", "Modify service attributes", sig1, "void", MBeanOperationInfo.ACTION );
    MBeanInfo mbi = new MBeanInfo( "jmxbook.ch5.JINIServiceManager", "Manages Service: " + initialAttribute.toString(), atts, cons, ops, nots );
    return mbi;
  }
  
  private Object lookUpService()
  {

    try
    {
      Class[] interfaces = { Class.forName( jiniInterfaceName ) };
      Entry[] ents = new Entry[ 1 ];
      ents[ 0 ] = initialAttribute;
      ServiceTemplate template = new ServiceTemplate(null,interfaces,ents );
      ServiceRegistrar reg = RegistryFinder.getRegistry();
      ServiceMatches matches = reg.lookup( template,10000 );
      ServiceItem item = matches.items[ 0 ];
      return item.service;
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }

    return null;
  }
} //class

