package ch05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TBorrower implements MessageListener {

    private TopicConnection tConnect = null;    
    private TopicSession tSession = null;
    private Topic topic = null;
    private double currentRate;

    public TBorrower(String topiccf, String topicName, String rate) {    	
    	try {
    		currentRate = Double.valueOf(rate);
    		
    		// Connect to the provider and get the JMS connection
			Context ctx = new InitialContext();
			TopicConnectionFactory qFactory = (TopicConnectionFactory)
				ctx.lookup(topiccf);
			tConnect = qFactory.createTopicConnection();
			
			// Create the JMS Session
			tSession = tConnect.createTopicSession(
				false, Session.AUTO_ACKNOWLEDGE);

			// Lookup the request and response queues
			topic = (Topic)ctx.lookup(topicName);

			// Create the message listener
			TopicSubscriber subscriber = tSession.createSubscriber(topic);
			subscriber.setMessageListener(this);
			
            // Now that setup is complete, start the Connection
			tConnect.start();

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
        	// Get the data from the message
        	BytesMessage msg = (BytesMessage)message;
        	double newRate = msg.readDouble();
        	
        	// If the rate is at least 1 point lower then the current rate then 
        	//recommend refinancing
        	if ((currentRate - newRate) >= 1.0) {
        		System.out.println("New rate = " + newRate + " - Consider refinancing loan");
        	} else {
        		System.out.println("New rate = " + newRate + " - Keep existing loan");
        	}

        	System.out.println("\nWaiting for rate updates...");

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
    		tConnect.close( );
    	} catch (JMSException jmse) {
    		jmse.printStackTrace( );
    	}
    	System.exit(0);
    }

    public static void main(String argv[]) {
    	String topiccf = null;
    	String topicName = null;
    	String rate = null;
    	if (argv.length == 3) {
    		topiccf = argv[0];
    		topicName = argv[1];
    		rate = argv[2];
    	} else {
    		System.out.println("Invalid arguments. Should be: ");
    		System.out.println
               ("java TBorrower factory topic rate");
    		System.exit(0);
    	}
      
    	TBorrower borrower = new TBorrower(topiccf, topicName, rate);
      
    	try {
    		// Run until enter is pressed
    		BufferedReader stdin = new BufferedReader
            	(new InputStreamReader(System.in));
    		System.out.println ("TBorrower application started");
    		System.out.println ("Press enter to quit application");
    		stdin.readLine();
            borrower.exit();
    	} catch (IOException ioe) {
    		ioe.printStackTrace( );
    	}
   }
}
