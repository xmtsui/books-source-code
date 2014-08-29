package jmxbook.ch6;

import javax.management.*;

public interface PollingMBean
{
  public void start();
  public void stop();
  public void setInterval( long time );
}

