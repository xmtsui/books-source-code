package jmxbook.ch2;
import javax.management.*;

public class HelloWorld extends NotificationBroadcasterSupport implements HelloWorldMBean
{

  public HelloWorld()
  {
    this.greeting = "Hello World! I am a Standard MBean";
  }

  public HelloWorld( String greeting )
  {
    this.greeting = greeting;
  }

  public void setGreeting( String greeting )
  {
    this.greeting = greeting;
    Notification notification = new Notification( "jmxbook.ch2.helloWorld.test", this, -1, System.currentTimeMillis(), greeting );
    sendNotification( notification );
  }

  public String getGreeting()
  {
    return greeting;
  }

  public void printGreeting()
  {
    System.out.println( greeting );
  }

  private String greeting;
}//class

