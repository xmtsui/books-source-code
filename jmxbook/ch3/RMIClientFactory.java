package jmxbook.ch3;

import javax.management.*;
import com.sun.jdmk.comm.*;

public class RMIClientFactory
{

  public static RmiConnectorClient getClient()
  {
    RmiConnectorClient client = new RmiConnectorClient();
    RmiConnectorAddress address = new RmiConnectorAddress();
    address.setPort( 2099 );
    System.out.println("\t\tTYPE\t= " + address.getConnectorType ());
    System.out.println("\t\tPORT\t= " + address.getPort());
    System.out.println("\t\tHOST\t= " + address.getHost());
    System.out.println("\t\tSERVER\t= " + address.getName());

    try
    {
      client.connect( address );
    }
    catch( Exception e )
    {
      ExceptionUtil.printException( e );
    }

    return client;
  }

}

