package ch07;

import javax.jms.*;
import javax.naming.*;

public class JMSSenderTransacted {

	private QueueConnection connection = null;
	private QueueSession session = null;
	private QueueSender sender = null;
		
	public static void main(String[] args) {
	    try {
	    	JMSSenderTransacted app = new JMSSenderTransacted();
	    	app.sendMessages();
	    	System.exit(0);
	    } catch (Exception up) {
	        up.printStackTrace();
	    }
	}

	//initialize jms and send messages
	public void sendMessages() {
		try {
	    	//send the messages in a transaction
			System.out.println("Session Transacted: " + session.getTransacted());
	    	sendMessage("First Message");
	    	sendMessage("Second Message");
	    	//if (true) throw new Exception("rolling back");
	    	sendMessage("Third Message");
	    	session.commit(); //no send if not committed - default is NOT o send messages - they just disappear
	    	connection.close();
	    } catch (Exception ex) {
	    	try {
	    		System.out.println("Exception caught, rolling back session");
		    	session.rollback();
	    	} catch (JMSException jmse) {
	    		jmse.printStackTrace();
	    	}
	    }
	}

	//create the connection, session, and sender
	public JMSSenderTransacted() {
		try {
		    Context ctx = new InitialContext();
		   	QueueConnectionFactory factory = (QueueConnectionFactory)
		   		ctx.lookup("QueueCF");
		    connection = factory.createQueueConnection();
		    connection.start();
		    session = connection.createQueueSession(true, Session.AUTO_ACKNOWLEDGE);	    	
	    	Queue queue = (Queue)ctx.lookup("queue1");
			sender = session.createSender(queue);    	
		} catch (Exception jmse) {
			jmse.printStackTrace();
			System.exit(0);
		}
	}

	//send a simple text message within the group of messages
	private void sendMessage(String text) throws Exception {
		TextMessage msg = session.createTextMessage(text);
		sender.send(msg);
	}
}
