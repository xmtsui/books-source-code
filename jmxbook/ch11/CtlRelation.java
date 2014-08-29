package jmxbook.ch11;

import javax.management.*;
import javax.management.relation.*;
import java.util.*;

public class CtlRelation extends RelationSupport implements CtlRelationMBean, MBeanRegistration
{
  private MBeanServer server = null;

  public CtlRelation(String relationId, ObjectName relationServiceName, String relationTypeName, RoleList roleList) throws InvalidRoleValueException, IllegalArgumentException
  {
    super(relationId, relationServiceName, relationTypeName, roleList);
  }

  public void disablePhoneCard( int cardNum ) throws MBeanException
  {

    System.out.println("Relation MBean::Disabling Phone Card");
    try
    {
      ObjectName phoneCardName = new ObjectName( "JMXBookAgent:name=phoneCard,slot="+cardNum);
      ObjectName routingTableName = new ObjectName( "JMXBookAgent:name=routingTable");
      server.invoke(phoneCardName, "disable", null, null);

      Object[] params = new Object[1];
      params[0] = new Integer(cardNum);
      String[] sig = new String[1];
      sig[0] = "java.lang.Integer";

      server.invoke(routingTableName, "removePhoneRoute", params, sig);
    }
    catch(Exception e)
    {
      System.out.println("Relation MBean::Error Removing " +" Phone Card:"+e);
      throw new MBeanException(e);
    }

  }

  public void disableFaxCard() throws MBeanException
  {

    System.out.println("Relation MBean::Disabling Fax Card");
    try
    {
      ObjectName faxCardName = new ObjectName( "JMXBookAgent:name=faxCard");
      ObjectName routingTableName = new ObjectName( "JMXBookAgent:name=routingTable");
      server.invoke(faxCardName, "disable", null, null);
      server.invoke(routingTableName, "removeFaxRoute", null, null);
    }
    catch(Exception e)
    {
      System.out.println("Relation MBean::" + "Error Removing Fax Card:"+e);
      throw new MBeanException(e);
    }

  }

  public ObjectName preRegister( MBeanServer server, ObjectName name ) throws Exception
  {
    this.server = server;
    return name;
  }

}

