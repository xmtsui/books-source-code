package com.jms.server;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;

import org.springframework.jms.listener.SessionAwareMessageListener;

public class JMSReceiverAsync2 implements SessionAwareMessageListener {

	public void onMessage(Message message, Session session) throws JMSException {
    	if (message instanceof TextMessage) {
    		System.out.println(((TextMessage)message).getText());
    	} else if (message instanceof MapMessage) {
    		MapMessage mmsg = (MapMessage)message;
    		System.out.println(
    				mmsg.getLong("acctId") + ", " + 
    				mmsg.getString("side") + ", " + 
    				mmsg.getString("symbol") + ", " + 
    				mmsg.getDouble("shares"));
    	} else {
    		throw new JMSException("Message type not supported");
    	}
    	long conf = 12345678;
    	//cannot cast to a QueueSession because the session is cast to a provider-specific session
    	//without access to JNDI, must use replyto in jms header
    	
    	//shorter code than using a jmstemplate because no need to create a messageCreator
    	//alternative would be to use a messagelistener and wire a jmstemplate to the class for sending result
    	MessageProducer sender = session.createProducer(message.getJMSReplyTo());
    	StreamMessage smsg = session.createStreamMessage();
    	smsg.setJMSCorrelationID(message.getJMSMessageID());
    	smsg.writeLong(conf);
    	sender.send(smsg);
	}
}


















