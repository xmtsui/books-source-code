package jmxbook.ch3;

import javax.management.*;
import jmxbook.ch2.*;
import com.sun.jdmk.comm.*;

public class HelloWorldSetup
{

  public HelloWorldSetup()
  {

    try
    {
      RmiConnectorClient client = RMIClientFactory.getClient();
      ObjectName hwName = new ObjectName( "JMXBookAgent:name=helloWorld");
      client.createMBean( "jmxbook.ch2.HelloWorld", hwName );
      client.invoke( hwName, "printGreeting", null, null );
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }

  }

  public static void main( String args[] )
  {
    HelloWorldSetup setup = new HelloWorldSetup ();
  }

}

