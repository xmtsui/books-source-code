package jmxbook.ch14;

import java.rmi.*;
import javax.ejb.*;

public interface UserInfo extends EJBObject
{
  public int getNumberOfLogins() throws RemoteException;
  public boolean login() throws RemoteException;
  public void logout() throws RemoteException;
  public void setLoginAllowed( boolean isAllowed ) throws RemoteException;
}

