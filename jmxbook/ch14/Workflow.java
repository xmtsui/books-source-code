package jmxbook.ch14;

import java.rmi.*;
import javax.ejb.*;

public interface Workflow extends EJBObject
{
  public void advanceState() throws RemoteException;
  public String getState() throws RemoteException;
  public void setState( String state ) throws RemoteException;
}

