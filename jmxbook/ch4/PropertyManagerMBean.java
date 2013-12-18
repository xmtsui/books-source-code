package jmxbook.ch4;

import java.util.*;

public interface PropertyManagerMBean
{
  public String getProperty( String key );
  public void setProperty( String key, String value );
  public Enumeration keys();
  public void setSource( String path );
}

