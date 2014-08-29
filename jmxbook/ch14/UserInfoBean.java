package jmxbook.ch14;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.ejb.*;
import javax.naming.*;
import javax.management.*;
import javax.sql.*;
import java.rmi.*;
import jmxbook.ch3.RMIClientFactory;
import com.sun.jdmk.comm.*;

public class UserInfoBean implements EntityBean
{
  private EntityContext ctx = null;
  private String userName = null;
  private int count=0;
  private boolean loginIsAllowed=true;

  public int getNumberOfLogins(){
    System.out.println("Return Number Of Queries:"+count);
    return count;
  }

  public boolean login()throws RemoteException
  {
    if(!loginIsAllowed)
    {
      System.out.println("User does not have " + "permissions to login");
      return false;
    }

    this.count++;
    System.out.println("User has successfully logged in");
    return true;
  }

  public void logout()throws RemoteException{
    System.out.println("User has successfully logged out");
  }

  public void setLoginAllowed(boolean isAllowed){
    this.loginIsAllowed=isAllowed;
    System.out.println("Setting login isAllowed:"+loginIsAllowed);
  }

  public void ejbLoad(){
    System.out.println("EJBLoad::Loading New UserInfo Bean:");
  }

  public void ejbStore(){
    System.out.println("EJBStore::Storing UserInfo Bean:"+userName);
  }

  public String ejbCreate( String userName ) throws CreateException{
    System.out.println("EJBCreate::Creating New UserInfo" + " Bean for:"+userName);
    return userName;
  }

  public void ejbPostCreate( String userName)
  {
    System.out.println( "Post create called" );
  }

  public void ejbRemove()
  {
    System.out.println("EJBCreate::Removing New UserInfo Bean");
  }

  public String ejbFindByPrimaryKey( String userName ) throws FinderException,RemoteException
  {
    System.out.println("find::Current Count:" + count++);
    this.userName=userName;
    return userName;
  }

  public void setEntityContext( EntityContext ctx )
  {
    this.ctx = ctx;
  }

  public void unsetEntityContext()
  {
    this.ctx = null;
  }

  public void ejbActivate()
  {
    System.out.println( "Activate called:"+userName );
  }

  public void ejbPassivate()
  {
    System.out.println( "Passivate called" );
  }

}


