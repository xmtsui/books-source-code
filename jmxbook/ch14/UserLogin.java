package jmxbook.ch14;

import javax.naming.*;
import java.util.Hashtable;
import javax.rmi.PortableRemoteObject;

public class UserLogin{

  public static void main(String[] args)
  {
    System.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
    System.setProperty("java.naming.provider.url", "localhost:1099");

    try{
      // Get a naming context
      InitialContext jndiContext = new InitialContext();
      System.out.println("Got context");

      // Get a reference to the UserInfo Bean
      Object ref = jndiContext.lookup("jmxbook/ch14/UserInfo");
      System.out.println("Got reference");

      // Get a reference from this to the Bean's Home interface
      UserInfoHome home = (UserInfoHome)
      PortableRemoteObject.narrow (ref, UserInfoHome.class);
      UserInfo userInfo=home.findByPrimaryKey(args[0]);

      if(userInfo==null){
        System.out.println("No Existing userInfo Found:");
        return;
      }
      else{
        System.out.println("Existing userInfo Found:");
      }

      boolean success=userInfo.login();
      if(!success){
        System.out.println("User Login not successful: Permission " + " denied");
      }
      else{
        System.out.println("User Successfully Logged in");
      }
    }
    catch(Exception e)
    {
      System.out.println(e.toString());
    }

  }

}


