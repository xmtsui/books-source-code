package ch07;

import javax.jms.ExceptionListener;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class JMSReceiverTransacted implements MessageListener, ExceptionListener {

	QueueSession session = null;

	public JMSReceiverTransacted() {

        try {
        	Context ctx = new InitialContext();
			QueueConnectionFactory factory = (QueueConnectionFactory)
			   ctx.lookup("QueueCF");
			QueueConnection connection = 
				factory.createQueueConnection();
			connection.start();
			connection.setExceptionListener(this);
			
			Queue queue = (Queue)ctx.lookup("queue1");
			
			session = 
				connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        	QueueReceiver receiver = session.createReceiver(queue);
			receiver.setMessageListener(this);			
        	
	    	System.out.println("Waiting for messages...");
        } catch (Exception up) {
            up.printStackTrace();
        }
	}

	public void onMessage(Message message) {

        try {
        	TextMessage msg = (TextMessage)message;
        	System.out.println("Message: " + msg.getText());
        	System.out.println("Waiting for messages...");
	    } catch (Exception up) {
	    	up.printStackTrace();
	    }
	}

	public static void main(String[] args) {
		try {
			new JMSReceiverTransacted();
	    } catch (Exception up) {
	    	up.printStackTrace();
	    }
		
	}
	
    public void onException ( javax.jms.JMSException jmse)
	{
	}	
}
