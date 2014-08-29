package jmxbook.ch12;

public interface ObservableObjectMBean
{
  public String getString();
  public void setString( String value );
  public Float getGauge();
  public void setGauge( Float value );
  public Integer getCounter();
  public void setCounter( Integer value );
}

