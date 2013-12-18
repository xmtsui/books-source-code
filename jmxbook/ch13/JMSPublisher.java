package jmxbook.ch13;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicConnection;
import javax.jms.TopicSession;
import javax.jms.TopicPublisher;
import javax.jms.Topic;
import javax.jms.TextMessage;
import javax.jms.Session;
import javax.jms.JMSException;

public class JMSPublisher
{
  private TopicConnection topicConnection=null;
  private TopicSession topicSession=null;
  private TopicPublisher topicPublisher=null;
  private Topic topic=null;
  private TopicConnectionFactory topicFactory = null;

  public JMSPublisher( String factoryJNDI, String topicJNDI ) throws JMSException, NamingException
  {

    // Get the initial context
    Context context = new InitialContext();

    // Get the connection factory
    topicFactory=(TopicConnectionFactory)
    context.lookup(factoryJNDI);

    // Create the connection
    topicConnection = topicFactory.createTopicConnection();

    // Create the session
    topicSession=topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

    // Look up the destination
    topic = (Topic)context.lookup(topicJNDI);

    // Create a publisher
    topicPublisher = topicSession.createPublisher(topic);
  }

  public void publish(String msg) throws JMSException
  {

    // Create a message
    TextMessage message = topicSession.createTextMessage();
    message.setText(msg);

    // Publish the message
    topicPublisher.publish(topic, message);
  }

  public void close() throws JMSException {
    topicSession.close();
    topicConnection.close();
  }

  public static void main(String[] args) {
    JMSPublisher publisher=null;

    try{
      publisher= new JMSPublisher("TopicConnectionFactory", "topic/controlMessages");
      String msg = "MOVIELIGHTSOn";

      System.out.println("Publishing message: "+msg);
      publisher.publish(msg);
      try{Thread.sleep(2000);}catch(InterruptedException e){}

      msg = "MOVIELIGHTSOff";
      System.out.println("Publishing message: "+msg);
      publisher.publish(msg);
      try{Thread.sleep(2000);}catch(InterruptedException e){}

      msg = "doNothing";
      System.out.println("Publishing message: "+msg);
      publisher.publish(msg);

      // Close down your publisher
      publisher.close();
    }
    catch(Exception ex) {
      System.err.println("An exception occurred " + "while testing Publisher: " + ex);
      ex.printStackTrace();
    }

  }

}


