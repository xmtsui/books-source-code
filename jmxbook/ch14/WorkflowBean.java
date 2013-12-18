package jmxbook.ch14;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.ejb.*;
import javax.naming.*;
import javax.sql.*;
import javax.management.*;
import java.rmi.*;

public class WorkflowBean implements EntityBean
{
  private DataSource ds = null;
  private EntityContext ctx = null;
  private String clientID = null;
  private int state = -1;

  public void advanceState()
  {
    state++;
  }

  public String getState()
  {
    return state + "";
  }

  public void setState( String state )
  {
    this.state = Integer.parseInt( state );
  }

  public void ejbLoad()
  {
    Connection conn = null;
    PreparedStatement ps = null;
    clientID = ( String ) ctx.getPrimaryKey();

    try
    {
      conn = getConnection();
      ps = conn.prepareStatement( "select state from workflows " + " where clientid = ?" );
      ps.setString( 1, clientID );
      ps.executeQuery();
      ResultSet rs = ps.getResultSet();

      if( rs.next() )
      {
        state = rs.getInt( 1 );
        installEJBMBean();
      }
      else
      {
        throw new NoSuchEntityException( "Could not find data");

      }
    }
    catch ( Exception e )
    {
      throw new EJBException( e );

    }
    finally
    {
      cleanup( conn, ps );
    }

  }

  public void ejbStore()
  {
    Connection conn = null;
    PreparedStatement ps = null;

    try
    {
      conn = getConnection();
      ps = conn.prepareStatement( "update workflows set state = ? " + " where clientID = ?" );
      ps.setInt( 1, state );
      ps.setString( 2, clientID );
      ps.executeUpdate();
    }
    catch( Exception e )
    {
      throw new EJBException ( e );
    }

    finally
    {
      cleanup( conn, ps );
    }

  }

  public String ejbCreate( String clientID ) throws CreateException
  {
    this.clientID = clientID;
    Connection conn = null;
    PreparedStatement ps = null;

    try
    {
      conn = getConnection();
      ps = conn.prepareStatement( "insert into workflows " + " ( clientID, state ) values ( ?, ? )" );
      ps.setString( 1, clientID );
      ps.setInt( 2, 0 );
      ps.executeUpdate();
      return clientID;
    }
    catch ( Exception e )
    {
      e.printStackTrace();
      throw new CreateException( "Error, possible duplicate Key" );
    }

    finally
    {
      cleanup( conn, ps );
    }

  }

  public void ejbPostCreate( String clientID)
  {
    System.out.println( "Post create called" );
  }

  public void ejbRemove()
  {
    Connection conn = null;
    PreparedStatement ps = null;

    try
    {
      removeEJBMBean();
      conn = getConnection();
      clientID = ( String ) ctx.getPrimaryKey();
      ps = conn.prepareStatement( "delete from workflows where " + "clientID = ?" );
      ps.setString( 1, clientID );
      ps.executeUpdate();
    }
    catch ( Exception e )
    {
      throw new EJBException ( e );
    }

    finally
    {
      cleanup( conn, ps );
    }

  }

  public String ejbFindByPrimaryKey( String clientID ) throws FinderException, RemoteException
  {
    Connection conn = null;
    PreparedStatement ps = null;

    try
    {
      conn = getConnection();
      ps = conn.prepareStatement( "select state from workflows " + " where clientid = ?" );
      ps.setString( 1, clientID );
      ps.executeQuery();
      ResultSet rs = ps.getResultSet();

      if( rs.next() )
      {
        state = rs.getInt( 1 );
      }
      else
      {
        throw new FinderException( "No EJB Found" );
      }

    }
    catch( Exception e )
    {
      throw new EJBException ( e );
    }

    finally
    {
      cleanup( conn, ps );
    }

    return clientID;
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
    System.out.println( "Activate called" );
  }

  public void ejbPassivate()
  {
    System.out.println( "Passivate called" );
  }

  private Connection getConnection() throws Exception
  {
    InitialContext newCTX = null;

    try
    {
      if( ds != null )
      return ds.getConnection();
      newCTX = new InitialContext();
      ds = ( javax.sql.DataSource )
      newCTX.lookup( "exampleDataSource" );
      return ds.getConnection();
    }
    catch( Exception e )
    {
      throw new EJBException( e );
    }
  }

  private void installEJBMBean ()
  {
    try
    {
      MBeanServer mbs = getMBeanServer();
      WorkflowManager wm = new WorkflowManager( clientID, "workflowBean " );
      ObjectName obn = new ObjectName( mbs.getDefaultDomain(), "clientID", clientID );
      mbs.registerMBean( wm , obn );
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }

  private void removeEJBMBean ()
  {
    try
    {
      MBeanServer mbs = getMBeanServer();
      ObjectName obn = new ObjectName( mbs.getDefaultDomain(), "clientID", clientID );
      mbs.unregisterMBean( obn );
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }

  private MBeanServer getMBeanServer()
  {
    // Stubbed out code for locating MBeanServer
    return null;
  }

  private void cleanup( Connection dbconn , Statement stmt )
  {
    try
    {
      stmt.close();
      dbconn.close();
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
  }

}//class


