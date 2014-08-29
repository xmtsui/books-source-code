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

public class DebugSubscriber implements MessageListener
{
  private TopicConnection topicConnection=null;
  private TopicSession topicSession=null;
  private TopicSubscriber topicSubscriber=null;
  private Topic topic=null;
  private TopicConnectionFactory topicFactory=null;
  private int count_=0;
  private Context context=null;

  public DebugSubscriber() throws JMSException, NamingException
  {
    String factoryJNDI="TopicConnectionFactory";
    String topicJNDI="topic/deviceMessages";

    // Get the initial context
    System.out.println("Getting Initial Context:");
    context = new InitialContext();
    System.out.println("Got Initial Context:"+context);

    // Get the connection factory
    System.out.println("Getting Topic Factory:");
    topicFactory=(TopicConnectionFactory)
    context.lookup(factoryJNDI);
    System.out.println("Got Topic Factory:"+topicFactory);

    // Create the connection
    topicConnection = topicFactory.createTopicConnection();

    // Create the session
    topicSession=topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
    //
    // Look up the destination
    topic = (Topic)context.lookup(topicJNDI);
    //
    // Create a subscriber
    topicSubscriber = topicSession.createSubscriber(topic);
    //
    // Set the message listener,
    // which is this class since we implement
    // the MessageListener interface
    topicSubscriber.setMessageListener(this);
    System.out.println("DeviceSubscriber subscribed to topic: " + topicJNDI);

    // OBS! For the message listener to receive any messages
    // the connection has to be started
    topicConnection.start();
  }

  public void onMessage(Message m)
  {
    try {
      String msg = ((TextMessage)m).getText();
      System.out.println("DeviceSubscriber got message: " + msg);
    }
    catch(Exception ex) {
      System.err.println("Device Could not handle message: " + ex);
      ex.printStackTrace();
    }
  }

  public void close() throws JMSException
  {
    topicSession.close();
    topicConnection.close();
  }

  public static void main(String[] args)
  {
    DebugSubscriber subscriber=null;

    try{
      System.out.println("Starting Debugging Subscriber");
      subscriber=new DebugSubscriber();
    }
    catch(Exception e){
      System.out.println("Error Starting Device DebugClient");
      e.printStackTrace();
    }

  }

}


