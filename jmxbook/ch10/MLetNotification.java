package jmxbook.ch10;

import javax.management.*;
import java.util.*;

public class MLetNotification extends Notification
{
  public static final String URL_TYPE = "jmxbook.mletwrapper.urlAdded";
  public static final String MBEAN_TYPE = "jmxbook.mletwrapper.mbeanAdded";
  public static final String ERROR_TYPE = "jmxbook.mletwrapper.error";
  private String url = null;
  private Set objectInstances = null;

  public MLetNotification( String type, Object source, long sequence )
  {
    super( type, source, sequence );
  }

  public void setObjectInstances( Set oi )
  {
    this.objectInstances = oi;
  }

  public void setURL( String url )
  {
    this.url = url;
  }

  public String getURL()
  {
    return url;
  }

  public Set getObjectInstances()
  {
    return objectInstances;
  }

}

