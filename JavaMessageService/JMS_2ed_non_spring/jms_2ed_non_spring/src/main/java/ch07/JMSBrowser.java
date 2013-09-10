package ch07;

import java.util.Enumeration;

import javax.jms.BytesMessage;
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

public class JMSBrowser {

	public static void main(String[] args) {
		
	    try {
	    	Context ctx = new InitialContext();
			QueueConnectionFactory factory = (QueueConnectionFactory)
			ctx.lookup("QueueCF");
			QueueConnection connection = 
				factory.createQueueConnection();
			connection.start();
			
			Queue queue = (Queue)ctx.lookup("queue1");
			QueueSession session = 
				connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
	        QueueBrowser browser = session.createBrowser(queue);

	        Enumeration e = browser.getEnumeration();
	        while (e.hasMoreElements()) {
	        	System.out.println("------------------------------------");
	        	Message msg = (Message)e.nextElement();
	        	if (msg instanceof BytesMessage) {
		        	System.out.println("Message: " + msg.getStringProperty("SequenceMarker") + ", Redelivered: " + msg.getJMSRedelivered());
	        	} else {
		        	System.out.println("Message: " + ((TextMessage)msg).getText() + ", Redelivered: " + msg.getJMSRedelivered());
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

