package jmxbook.ch7;

import javax.management.*;
import javax.management.modelmbean.*;
import jmxbook.ch3.RMIClientFactory;
import com.sun.jdmk.comm.*;

public class ModeledClass implements java.io.Serializable
{
  private String attribute = "My Attribute";

  public ModeledClass()
  {
  }

  public String getMyAttribute()
  {
    System.out.println( "Returning attribute to MBean" );
    return attribute;
  }

  public void printAttribute()
  {
    System.out.println( attribute );
  }

  public static void main( String[] args ) throws Exception
  {
    ModeledClass obj = new ModeledClass();
    ModelMBeanInfoBuilder builder = new ModelMBeanInfoBuilder();
    Descriptor attDesc = builder.buildAttributeDescriptor( "MyAttribute", null, "always", "10", null, "getMyAttribute", null, "10" );
    builder.addModelMBeanAttribute( "MyAttribute", "java.lang.String", true, false, false, "", attDesc );
    Descriptor opGetDesc = builder.buildOperationDescriptor( "getMyAttribute", null, "getter", null, null, "jmxbook.ch7.ModeledClass", "10" );
    builder.addModelMBeanMethod( "getMyAttribute", null, null, null, "", "java.lang.String" , MBeanOperationInfo.INFO, opGetDesc );
    Descriptor opDesc = builder.buildOperationDescriptor( "printAttribute", null, "operation", null, null, "jmxbook.ch7.ModeledClass", "10" );
    builder.addModelMBeanMethod( "printAttribute", null, null, null, "", "void" , MBeanOperationInfo.ACTION, opDesc );
    Descriptor mbeanDesc = builder.buildMBeanDescriptor( "modeledClass", "", "always", "10", "." ,"ModeledClass", null, null );
    ModelMBeanInfo info = builder.buildModelMBeanInfo( mbeanDesc );
    RmiConnectorClient client = RMIClientFactory.getClient();
    ObjectName mName = new ObjectName("JMXBookAgent:name=Modeled");
    client.createMBean( "javax.management.modelmbean.RequiredModelMBean", mName );
    String[] sig = { "java.lang.Object", "java.lang.String" };
    Object[] params = { obj, "ObjectReference" };
    client.invoke( mName, "setManagedResource", params, sig );
    sig = new String[ 1 ];
    sig[ 0 ] = "javax.management.modelmbean.ModelMBeanInfo" ;
    params = new Object[ 1 ];
    params[ 0 ] = info;
    client.invoke( mName, "setModelMBeanInfo", params, sig );
    //store the MBean
    client.invoke( mName,"store",null,null );
  }

}

