package jmxbook.ch14;

import com.sun.jdmk.comm.*;
import javax.management.*;
import jmxbook.ch3.RMIClientFactory;
import javax.naming.*;
import java.util.Hashtable;
import javax.rmi.PortableRemoteObject;

public class UserInfoMgr implements UserInfoMgrMBean{

  public UserInfoMgr(){
    System.out.println("Creating UserInfoMgr MBean");
  }

  public UserInfo getUserInfo( String userName )
  {
    int count=0;
    UserInfo userInfo=null;
    System.out.println("Getting UserQueryInfo:");
    System.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
    System.setProperty("java.naming.provider.url", "localhost:1099");

    try{
      InitialContext jndiContext = new InitialContext();
      System.out.println("Got context");
      Object ref = jndiContext.lookup("jmxbook/ch14/UserInfo");
      System.out.println("Got reference");
      UserInfoHome home = (UserInfoHome)
      PortableRemoteObject.narrow (ref, UserInfoHome.class);
      userInfo=home.findByPrimaryKey(userName);
    }
    catch(Exception e){
      System.out.println(e.toString());
    }

    return userInfo;
  }

  public int getQueryCount( String userName )
  {
    int count=0;
    UserInfo userInfo=null;

    try{
      userInfo=getUserInfo(userName);
      count=userInfo.getNumberOfLogins();
      System.out.println("Number of Logins:"+count);
    }
    catch(Exception e){
      System.out.println(e.toString());
    }

    return count;
  }

  public void allowLogin( String userName, boolean isAllowed )
  {
    UserInfo userInfo=null;

    try{
      userInfo=getUserInfo(userName);
      userInfo.setLoginAllowed(isAllowed);
      System.out.println("Set isLoginAllowed:"+isAllowed);
    }
    catch(Exception e){
      System.out.println(e.toString());
    }
  }

  public static void main( String[] args ){

    System.out.println("\n\tCONNECT to the MBeanServer.");
    RmiConnectorClient client = RMIClientFactory.getClient();
    System.out.println("\n\tGot RMI Client.");

    try{
      //register the JMX_MBean
      Object[] params = new Object[0];
      String[] sig = new String[0];
      System.out.println("\n>>> REGISTERING JMX MBean");

      //register the JMX Controller MBean
      System.out.println("\n>>> REGISTERING JMX Controller MBean");
      ObjectName JMXBeanName = new ObjectName( "JMXBookAgent:name=EJB_UserInfo_Bean" );
      client.createMBean( "jmxbook.ch14.UserInfoMgr", JMXBeanName );
      client.disconnect();
    }
    catch(Exception e)
    {
      e.printStackTrace();
      System.out.println("Error Registering MBeans");
    }

  }

}

