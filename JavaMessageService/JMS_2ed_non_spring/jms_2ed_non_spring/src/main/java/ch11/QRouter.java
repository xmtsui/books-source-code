package ch11;

import java.io.*;
import javax.jms.*;
import javax.naming.*;

public class QRouter implements MessageListener {
	
    private QueueConnection qConnect = null;    
    private QueueSession qSession = null;
    private Queue requestQ = null;

    private OrderProcessor orderProcessor = null;
    private StatusProcessor statusProcessor = null;
    private CancelProcessor cancelProcessor = null;

    public QRouter(String queueCF, String queue) {    	
    	try {
			Context ctx = new InitialContext();
			QueueConnectionFactory qFactory = (QueueConnectionFactory)
				ctx.lookup(queueCF);
			qConnect = qFactory.createQueueConnection();
			
			// Create the JMS Session
			qSession = qConnect.createQueueSession(
				false, Session.AUTO_ACKNOWLEDGE);

			// Lookup the request queue
			requestQ = (Queue)ctx.lookup(queue);

			orderProcessor = new OrderProcessor();
			statusProcessor = new StatusProcessor();
			cancelProcessor = new CancelProcessor();
            
			// Now that setup is complete, start the Connection
			qConnect.start();

			// Create the message listener
			QueueReceiver qReceiver = qSession.createReceiver(requestQ);
			qReceiver.setMessageListener(this);

			System.out.println("Waiting for loan requests...");
			
    	} catch (JMSException jmse) {
    		jmse.printStackTrace( ); 
    		System.exit(1);
        } catch (NamingException jne) {
            jne.printStackTrace( ); 
            System.exit(1);
        }
    }

    public void onMessage(Message message) {

    	try {
    		//get the message payload
    		String xml = ((TextMessage)message).getText();

    		//get the message type
    		int type = message.getIntProperty("type");
    		if (type == RouterConstants.NEW_BOOK_ORDER) {
    			orderProcessor.placeOrder(xml);
    		} else if (type == RouterConstants.ORDER_STATUS) {
    			statusProcessor.checkOrderStatus(xml);
    		} else if (type == RouterConstants.CANCEL_ORDER) {
    			cancelProcessor.cancelOrder(xml);
    		} else {
    			throw new Exception("Invalid Order Type: " + type);
    		}

    	} catch (JMSException jmse) {
    		jmse.printStackTrace( ); 
    		System.exit(1);
    	} catch (Exception jmse) {
    		jmse.printStackTrace( ); 
    		System.exit(1);
    	}
    }
    
    private void exit() {
    	try {
    		qConnect.close( );
    	} catch (JMSException jmse) {
    		jmse.printStackTrace( );
    	}
    	System.exit(0);
    }

    public static void main(String argv[]) {
    	String queuecf = null;
    	String requestq = null;
    	if (argv.length == 2) {
    		queuecf = argv[0];
    		requestq = argv[1];
    	} else {
    		System.out.println("Invalid arguments. Should be: ");
    		System.out.println
               ("java QLender factory request_queue");
    		System.exit(0);
    	}
      
    	QRouter lender = new QRouter(queuecf, requestq);
      
    	try {
    		// Run until enter is pressed
    		BufferedReader stdin = new BufferedReader
            	(new InputStreamReader(System.in));
    		System.out.println ("QLender application started");
    		System.out.println ("Press enter to quit application");
    		stdin.readLine();
            lender.exit();
    	} catch (IOException ioe) {
    		ioe.printStackTrace( );
    	}
   }
}


class RouterConstants {

	public static final int NEW_BOOK_ORDER = 1;
	public static final int ORDER_STATUS = 2;
	public static final int CANCEL_ORDER = 3;
}


class OrderProcessor {
	
	public void placeOrder(String xml) {
		System.out.println("Placing order: " + xml);
	}
	
}

class StatusProcessor {
	
	public void checkOrderStatus(String xml) {
		System.out.println("Checking status: " + xml);
	}
	
}

class CancelProcessor {
	
	public void cancelOrder(String xml) {
		System.out.println("Canceling order: " + xml);
	}
	
}