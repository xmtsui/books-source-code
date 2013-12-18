package jmxbook.ch2;

public interface HelloWorldMBean
{
  public void setGreeting( String greeting );
  public String getGreeting();
  public void printGreeting();
}

