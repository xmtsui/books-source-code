package jmxbook.ch14;

import java.io.*;
import java.util.*;
import java.lang.reflect.*;

public class EJBMBeanGenerator
{
  private String remoteInterface = null;
  private String remoteInterfaceClass = null;
  private Hashtable atts = null;
  private Hashtable attTypes = null;
  private Hashtable opsArgTypes = null;
  private Hashtable opsReturns = null;
  private Vector ops = null;
  private Vector opNames = null;
  private PrintWriter out = null;

  public EJBMBeanGenerator( String remoteInterfaceClassName )
  {
    remoteInterfaceClass = remoteInterfaceClassName;
    remoteInterface = remoteInterfaceClassName.substring(
    remoteInterfaceClassName.lastIndexOf( "." ) + 1 );
  }

  public void buildMBean( String location )
  {
    try
    {
      Class remote = Class.forName( remoteInterfaceClass );
      buildAttributesAndOperations( remote );
      out = new PrintWriter( new FileOutputStream( location +
      "/" + remoteInterface + "Manager.java" ) );
      writeClassTop();
      writeConstructor();
      writeLookupEJB();
      writeClassEnd();
      out.flush();
      out.close();
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }//catch
  }

  private void writeClassTop()
  {
    out.println( "import javax.management.*;" );
    out.println( "import java.rmi.*;" );
    out.println( "import " + remoteInterfaceClass + ";" );
    out.println( "import javax.naming.*; " );
    out.println( "import java.lang.reflect.*; " );
    out.println();
    out.println( "public class " + remoteInterface + "Manager extends jmxbook.ch5.DynamicMBeanSupport " );
    out.println( "{");
    out.println();
    out.println( " private String jndiName = null;" );
    out.println( " private Object pk = null;" );
    out.println( " private " + remoteInterface + " remoteInterface = null;" );
    out.println();
  }

  private void writeClassEnd()
  {
    out.println( "} //class" );
  }

  private void writeConstructor()
  {
    out.println();
    out.println( " public " + remoteInterface + "Manager( Object pk, String lookupName ) throws Exception " );
    out.println( " {" );
    out.println( " jndiName = lookupName; " );
    out.println( " " + remoteInterfaceClass + "Home home = ( " + remoteInterfaceClass + "Home ) lookupEJB(); " );
    out.println( " remoteInterface = ( " + remoteInterfaceClass + " ) home.findByPrimaryKey( ( String ) pk ); " );
    out.println( " " );
    out.println();

    Enumeration enum = atts.keys();
    int index = 0;

    while( enum.hasMoreElements() )
    {
      String attName = ( String ) enum.nextElement();
      String rw = ( String ) atts.get( attName );
      String desc = "MBean attribute";
      String type = ( String ) attTypes.get( attName );
      boolean readable = ( rw.indexOf( "r" ) == -1 ) ? false:true;
      boolean writeable = ( rw.indexOf( "w" ) == -1 ) ? false:true;
      out.println( " addMBeanAttribute( \"" + attName + "\",\"" + type + "\", " + readable + "," + writeable + ", false ,\"" + desc + "\");" );
      index++;
    }//while

    enum = ops.elements();
    index = 0;
    out.println( " String[] types = null;");
    out.println( " String[] argNames = null;");
    out.println( " String[] argDescs = null;");

    while( enum.hasMoreElements() )
    {
      String opName = ( String ) enum.nextElement();
      String rType = ( String ) opsReturns.get( opName );
      String desc = "MBean operation";
      String[] types = ( String[] ) opsArgTypes.get( opName );
      String[] argNames = new String[ types.length ];
      String[] argDescs = new String[ types.length ];

      for( int j=0; j < types.length; j++ )
      {
        argNames[ j ] = "arg" + j;
        argDescs[ j ] = "Description";
      }

      out.println( " types = new String[ " + types.length + " ];");
      out.println( " argNames = new String[ " + types.length + " ];");
      out.println( " argDescs = new String[ " + types.length + " ];");

      for( int k = 0; k < types.length; k++ )
      {
        out.println( " types[ " + k + " ] = " + types[ k ] + ";");
        out.println( " argNames[ " + k + " ] = " + argNames[ k ] + ";");
        out.println( " argDescs[ " + k + " ] = " + argDescs[ k ] + ";");
      }

      out.println( " addMBeanOperation( \"" + opName + "\", types , argNames, argDescs, \"" + desc + "\", \"" + rType + "\" , \" + MBeanOperationInfo.UNKNOWN );" );
      index++;

    }//while

    out.println( " }");
    out.println();
  }

  private void writeLookupEJB()
  {
    out.println();
    out.println( "private Object lookupEJB() throws Exception" );
    out.println( " {" );
    out.println( " Context ctx = new InitialContext();" );
    out.println( " return ( " + remoteInterface +" ) ctx.lookup( jndiName );" );
    out.println( " }" );
    out.println();
  }

  private void buildAttributesAndOperations( Class c )
  {
    Method[] methods = c.getMethods();
    atts = new Hashtable();
    attTypes = new Hashtable();
    ops = new Vector();
    opsReturns = new Hashtable();
    opsArgTypes= new Hashtable();

    for( int i = 0; i < methods.length; i++ )
    {
      Method m = methods[i];
      String name = m.getName();
      boolean attributeSet = false;
      boolean attributeGet = false;

      if( name.startsWith( "set" ) )
      {
        atts.put( name.substring( 3 ), "w" );
        attributeSet = true;
      }
      else if( name.startsWith( "get" ) )
      {
        if ( atts.containsKey( name.substring( 3 ) ) ) atts.put( name.substring( 3 ), "rw" );
        else atts.put( name.substring( 3 ), "r" );
        attributeGet = true;
      }
      else
      {
        ops.addElement( name );
        if( m.getReturnType() != null ) opsReturns.put( name, m.getReturnType().getName() );
        Class[] sig = m.getParameterTypes();
        String[] params = new String[ sig.length ];
        for( int k = 0; k < sig.length; k++ ) params[k] = sig[k].getName();
        opsArgTypes.put( name, params );
      }

      if( attributeSet ) attTypes.put( name.substring( 3 ), m.getReturnType().getName() );
      else if( attributeGet ) attTypes.put( name.substring( 3 ), "java.lang.String" );
    }//for
  }

  public static void main( String args[] )
  {
    String classname = args[0];
    String location = args[1];
    EJBMBeanGenerator emg = new EJBMBeanGenerator( classname );
    emg.buildMBean( location );
  }

}//class


