package jmxbook.ch4;

import java.util.*;
import java.io.*;

public class PropertyManager implements PropertyManagerMBean
{
  private Properties props = null;

  public PropertyManager( String path )
  {
    try
    {
      //load supplied property file
      props = new Properties();
      FileInputStream f = new FileInputStream( path );
      props.load( f );
      f.close();
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }

  }

  public String getProperty( String key )
  {
    return props.getProperty( key );
  }

  public void setProperty( String key, String value )
  {
    props.setProperty( key, value );
  }

  public Enumeration keys()
  {
    return props.keys();
  }

  public void setSource( String path )
  {

    try
    {
      props = new Properties();
      FileInputStream f = new FileInputStream( path );
      props.load( f );
      f.close();
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }

  }

}//class


