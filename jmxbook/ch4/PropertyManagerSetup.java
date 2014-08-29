package jmxbook.ch4;

import javax.management.*;
import com.sun.jdmk.comm.*;
import jmxbook.ch3.*;

public class PropertyManagerSetup
{

  public PropertyManagerSetup()
  {

    try
    {
      RmiConnectorClient client = RMIClientFactory.getClient();
      ObjectName propertyName = new ObjectName( "JMXBookAgent:name=property");
      client.createMBean( "jmxbook.ch4.PropertyManager", propertyName );
    }
    catch( Exception e )
    {
      ExceptionUtil.printException( e );
    }

  }

  public static void main( String args[] )
  {
    PropertyManagerSetup setup = new PropertyManagerSetup();
  }

}

