package jmxbook.ch12;

import javax.management.*;
import javax.management.monitor.*;

public interface CorrectiveStringMonitorMBean extends StringMonitorMBean
{
  public void setExecutableMethodOnDiffer( ObjectName name, String methodName );
}

