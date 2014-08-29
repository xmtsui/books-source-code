package jmxbook.ch4;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class DBSource
{
  private DataSource ds = null;
  private boolean commit = false;

  public DBSource( String JNDIName )
  {

    try
    {
      //lookup data source using JNDI
      Context ctx = new InitialContext();
      ds = ( DataSource ) ctx.lookup( JNDIName );
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }

  }

  public void resetDataSource( String name )
  {

    try
    {
      Context ctx = new InitialContext();
      ds = ( DataSource ) ctx.lookup( name );
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }

  }

  public Connection getConnection()
  {
    Connection con = null;

    try
    {
      con = ds.getConnection();
      con.setAutoCommit( commit );
      return con;
    }
    catch( Exception e )
    {
      e.printStackTrace();
      con = null;
      return null;
    }

  }

  public boolean getAutoCommit( )
  {
    return commit;
  }

  public void setAutoCommit( boolean commit )
  {
    this.commit = commit;
  }

}


