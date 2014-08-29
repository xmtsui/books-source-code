package jmxbook.ch12;

import javax.management.*;
import javax.management.monitor.*;

public class CorrectiveStringMonitor extends StringMonitor implements CorrectiveStringMonitorMBean
{
  private ObjectName executeName = null;
  private String executeMethod = null;

  public CorrectiveStringMonitor()
  {
    super();
  }

  public void setExecutableMethodOnDiffer( ObjectName name, String methodName )
  {
    this.executeName = name;
    this.executeMethod = methodName;
  }

  public void sendNotification( Notification not )
  {
    if( not.getType().equals( MonitorNotification.STRING_TO_COMPARE_VALUE_DIFFERED ) )
    {
      try
      {
        server.invoke( executeName, executeMethod, null, null );
      }
      catch( Exception e )
      {
        e.printStackTrace();
      }
    }

    super.sendNotification( not );
  }

}

