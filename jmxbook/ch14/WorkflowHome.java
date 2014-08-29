package jmxbook.ch14;

import javax.ejb.*;
import java.rmi.*;
import java.util.*;

public interface WorkflowHome extends EJBHome
{
  public Workflow create( String clientID ) throws CreateException, RemoteException;
  public Workflow findByPrimaryKey( String clientID ) throws FinderException, RemoteException;
}

