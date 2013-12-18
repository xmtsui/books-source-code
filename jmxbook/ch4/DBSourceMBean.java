package jmxbook.ch4;

import java.sql.*;

public interface DBSourceMBean
{
  public void resetDataSource( String name );
  public void setAutoCommit( boolean commit );
  public boolean getAutoCommit( );
  public Connection getConnection();
}


