package com.jms.client;

import java.util.Enumeration;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class JMSQueueBrowser {

	public static void main(String[] args) {
		
	    try {
	    	//establish connection
	        Context context = new InitialContext();
	        QueueConnectionFactory factory = (QueueConnectionFactory) context.lookup("QueueCF");
	        QueueConnection connection = factory.createQueueConnection();
	        connection.start();

	        //establish session 
	        Queue queue = (Queue) context.lookup("queue1");
	        QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
	        QueueBrowser browser = session.createBrowser(queue);

	        Enumeration e = browser.getEnumeration();
	        while (e.hasMoreElements()) {
	        	System.out.println("------------------------------------");
	        	Message msg = (Message)e.nextElement();
	        	if (msg instanceof TextMessage) {
	        		System.out.println(((TextMessage)msg).getText());
	        	} else if (msg instanceof MapMessage) {
	        		MapMessage mmsg = (MapMessage)msg;
	        		System.out.println(mmsg.getLong("acctId") + ", " + 
	        				mmsg.getString("side") + ", " + 
	        				mmsg.getString("symbol") + ", " + 
	        				mmsg.getDouble("shares"));
	        	}
	        }
	        browser.close();
	        connection.close();
	        System.exit(0);
	        
	    } catch (Exception exception) {
	        exception.printStackTrace();
	    }
	}
}

