package jmxbook.ch13;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.jms.TopicPublisher;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicConnection;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.jms.Topic;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.Session;
import javax.jms.MessageListener;
import javax.jms.JMSException;

public class JMSController implements MessageListener, JMSControllerMBean
{
  private TopicConnection topicConnection=null;
  private TopicSession topicSession=null;
  private TopicSubscriber topicSubscriber=null;
  private Topic topic=null;
  private TopicConnectionFactory topicFactory=null;
  private int count_=0;
  private Context context=null;

  public JMSController() throws JMSException, NamingException
  {
    String factoryJNDI="TopicConnectionFactory";
    String topicJNDI="topic/controlMessages";

    // Get the initial context
    System.out.println("Getting Initial Context:");
    context = new InitialContext();
    System.out.println("Got Initial Context:"+context);

    // Get the connection factory
    System.out.println("Getting Topic Factory:");
    topicFactory = (TopicConnectionFactory)
    context.lookup(factoryJNDI);
    System.out.println("Got Topic Factory:"+topicFactory);

    // Create the connection
    topicConnection = topicFactory.createTopicConnection();

    // Create the session
    topicSession=topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

    // Look up the destination
    topic = (Topic)context.lookup(topicJNDI);

    // Create a subscriber
    topicSubscriber =
    topicSession.createSubscriber(topic);

    // Set the message listener,
    // which is this class since we implement
    // the MessageListener interface
    topicSubscriber.setMessageListener(this);
    topicConnection.start();
  }

  public void onMessage( Message m )
  {
    Topic topic=null;
    TopicPublisher topicPublisher=null;
    TopicSession sendTopicSession=null;
    TextMessage message=null;
    String msg=null;
    String msg2=null;

    try
    {
      msg = ((TextMessage)m).getText();

      if( msg.equals("MOVIELIGHTSOn") )
      {
        msg="SurroundOn";
        msg2="ScreenDown";
        publishMessages(msg,msg2);
      }
      else if( msg.equals("MOVIELIGHTSOff") )
      {
        msg="SurroundOff";
        msg2="ScreenUp";
        publishMessages(msg,msg2);
      }
      else
      {
        System.out.println("This message is not handled" + " by this MBean");
        return;
      }

    }
    catch(Exception ex)
    {
      System.err.println("Could not handle message: " + ex);
      ex.printStackTrace();
    }

  }

  public void publishMessages(String msg,String msg2)
  {
    Topic topic=null;
    TopicPublisher topicPublisher=null;
    TopicSession sendTopicSession=null;
    TextMessage message=null;

    try
    {
      System.out.println("Will publish "+msg +" Message to Device topic");

      // Look up the destination
      topic = (Topic)context.lookup("/topic/deviceMessages");
      System.out.println("Found the deviceMessages Topic");

      // Create a publisher
      sendTopicSession = topicConnection.createTopicSession( false, Session.AUTO_ACKNOWLEDGE);
      topicPublisher = sendTopicSession.createPublisher(topic);

      // Create a message
      message = sendTopicSession.createTextMessage();
      message.setText(msg);

      // Publish the message
      topicPublisher.publish(topic, message);
      System.out.println("Published "+msg +" to deviceMessages Topic");

      // Create a message
      message = sendTopicSession.createTextMessage();
      message.setText(msg2);

      // Publish the message
      topicPublisher.publish(topic, message);
      System.out.println("Published "+msg2 +" to deviceMessages Topic");
    }
    catch(Exception ex)
    {
      System.err.println("Could not handle message: " + ex);
      ex.printStackTrace();
    }
  }

  public void close() throws JMSException {
    topicSession.close();
    topicConnection.close();
  }

  public void turnOnHomeTheater()
  {
    System.out.println("Turning On Home Theater System");
    publishMessages("SurroundOn","ScreenDown");
  }

  public void turnOffHomeTheater()
  {
    System.out.println("Turning Off Home Theater System");
    publishMessages("SurroundOff","ScreenUp");
  }

}

