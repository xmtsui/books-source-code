package com.jms.server;

import java.util.Enumeration;

import javax.jms.BytesMessage;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class JMSReceiver implements MessageListener {

	QueueSession session = null;
	
	public JMSReceiver() {

        try {
        	Context ctx = new InitialContext();
        	QueueConnectionFactory factory = (QueueConnectionFactory)
				   ctx.lookup("QueueCF");
			QueueConnection connection = factory.createQueueConnection();
			connection.start();
			
			Queue queue = (Queue)ctx.lookup("request");
			session = 
				connection.createQueueSession(false, Session.DUPS_OK_ACKNOWLEDGE);
			QueueReceiver receiver = session.createReceiver(queue);
			receiver.setMessageListener(this);
			
	    	System.out.println("Waiting for messages...");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
	}

	public void onMessage(Message message) {

        try {
            System.out.println("Message Received From " + message.getStringProperty("username"));
            TextMessage msg = (TextMessage)message;
            System.out.println(msg.getText());
            long conf = 98765;
            StreamMessage smsg = session.createStreamMessage();
            smsg.writeLong(conf);
            QueueSender sender = session.createSender((Queue)message.getJMSReplyTo());
            sender.send(smsg);
            
            System.out.println("Waiting for messages...");
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}

	public static void main(String[] args) {
		try {
			new JMSReceiver();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
		
	}

}
