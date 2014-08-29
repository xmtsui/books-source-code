package jmxbook.ch9;

import java.rmi.*;
import java.rmi.server.*;
import javax.management.*;

public class JINIConnectorImpl extends UnicastRemoteObject implements JINIConnector
{
  private JINIServer server = null;

  public JINIConnectorImpl( JINIServer server ) throws RemoteException
  {
    this.server = server;
  }

  public Integer getMBeanCount() throws JINIConnectorException
  {
    try
    {
      return server.getMBeanCount();
    }
    catch( Exception e )
    {
      throw new JINIConnectorException( "getMBeanCount", e );
    }
  }

  public ObjectInstance createMBean(String className, ObjectName name) throws JINIConnectorException
  {
    try
    {
      return server.createMBean( className, name );
    }
    catch( Exception e )
    {
      throw new JINIConnectorException( "createMBean", e );
    }
  }

}

