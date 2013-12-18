package jmxbook.ch12;

public class ObservableObject implements ObservableObjectMBean
{
  private Integer counter = null;
  private Float gauge = null;
  private String string = null;

  public ObservableObject()
  {
    counter = new Integer( 0 );
    gauge = new Float( 0 );
    string = "abc";
  }

  public String getString()
  {
    return string;
  }

  public void setString( String value )
  {
    string = value;
  }

  public Float getGauge()
  {
    return gauge;
  }

  public void setGauge( Float value )
  {
    gauge = value;
  }

  public Integer getCounter()
  {
    return counter;
  }

  public void setCounter( Integer value )
  {
    counter = value;
  }

}

