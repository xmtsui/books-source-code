package jmxbook.ch14;

import javax.management.*;
import javax.naming.*;
import java.rmi.*;
import java.lang.reflect.*;

public class WorkflowManager implements DynamicMBean
{
  private String clientID = null;
  private Workflow ejb = null;

  public WorkflowManager( String clientID, String JNDIName ) throws Exception
  {
    this.clientID = clientID;
    WorkflowHome home = lookUpHome();
    ejb = ( Workflow ) home.findByPrimaryKey( clientID );
  }

  public MBeanInfo getMBeanInfo()
  {

    try
    {
      MBeanAttributeInfo[] atts = new MBeanAttributeInfo[ 1 ];
      atts[0] = new MBeanAttributeInfo( "State", "java.lang.String", "Workflow state of client " + clientID, true, true, false );
      MBeanOperationInfo[] ops = new MBeanOperationInfo[ 1 ];
      MBeanParameterInfo[] sig = new MBeanParameterInfo[ 0 ];
      ops[0] = new MBeanOperationInfo( "advanceState", "Advance the workflow", sig, "void", MBeanOperationInfo.ACTION );
      Class consig[] = { Class.forName( "java.lang.String" ),
      Class.forName( "java.lang.String" ) };
      Constructor construct = this.getClass().getConstructor( consig );
      MBeanConstructorInfo cons[] = new MBeanConstructorInfo[ 1 ];
      cons[0] = new MBeanConstructorInfo( "Constructor", construct );
      MBeanInfo mbi = new MBeanInfo( this.getClass().getName(), "Manages Workflow Entity EJB", atts, cons, ops, null );
      return mbi;
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }

    return null;
  }

  public void setAttribute( Attribute att ) throws AttributeNotFoundException, MBeanException, ReflectionException, InvalidAttributeValueException
  {
    if( att.getName().equals( "State" ) )
    {
      try
      {
        ejb.setState( ( String ) att.getValue() );
      }catch( RemoteException re )
      {
        throw new MBeanException( re );
      }
    }
  }

  public AttributeList setAttributes( AttributeList list )
  {
    AttributeList rvalue = new AttributeList();

    try
    {
      Attribute[] values = ( Attribute[] ) list.toArray();
      for( int i = 0; i< values.length; i++ )
      {
        setAttribute( values[ i ] );
        rvalue.add( values[ i ] );
      }//for
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }

    return rvalue;
  }

  public Object getAttribute( String name ) throws AttributeNotFoundException, MBeanException, ReflectionException
  {
    try
    {
      if( name.equals( "State" ) )
      {
        Object temp = ejb.getState();
        return temp;
      }
      else
        throw new AttributeNotFoundException( name );
    }
    catch( Exception e )
    {
      throw new MBeanException( e );
    }
  }

  public AttributeList getAttributes( String[] names )
  {
    AttributeList rvalue = new AttributeList();

    try
    {
      String[] list = names;
      for( int i = 0; i< list.length; i++ )
      {
        String attName = list[i];
        rvalue.add( new Attribute( attName, getAttribute( attName ) ) );
      }//for
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }

    return rvalue;
  }

  private WorkflowHome lookUpHome() throws Exception
  {
    Context ctx = new InitialContext();
    return ( WorkflowHome ) ctx.lookup( "workflow" );
  }

  public Object invoke( String actionName, Object[] args, String[] sig ) throws MBeanException, ReflectionException
  {
    try
    {
      String methodName = actionName;
      Class types[] = new Class[ sig.length ];
      for( int i = 0; i < types.length; i++ )
      types[ i ] = Class.forName( sig[ i ] );
      Method m = ejb.getClass().getMethod( methodName, types );
      Object temp = m.invoke( ejb, args );
      return temp;
    }
    catch( Exception e )
    {
      throw new MBeanException( e );
    }
  }

}

