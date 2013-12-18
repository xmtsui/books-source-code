package jmxbook.ch14;

import javax.ejb.*;
import java.rmi.*;
import java.util.*;

public interface UserInfoHome extends EJBHome
{
  public UserInfo create( String userName ) throws CreateException, RemoteException;
  public UserInfo findByPrimaryKey( String userName ) throws FinderException, RemoteException;
}

