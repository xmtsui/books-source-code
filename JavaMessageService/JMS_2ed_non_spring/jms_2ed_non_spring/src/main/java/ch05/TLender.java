package ch05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TLender {

    private TopicConnection tConnect = null;    
    private TopicSession tSession = null;
    private Topic topic = null;

    public TLender(String topiccf, String topicName) {    	
    	try {
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

            // Now that setup is complete, start the Connection
			tConnect.start();
			
    	} catch (JMSException jmse) {
    		jmse.printStackTrace( ); 
    		System.exit(1);
        } catch (NamingException jne) {
            jne.printStackTrace( ); 
            System.exit(1);
        }
    }

    private void publishRate(double newRate) {
    	try {
        	// Create JMS message
        	BytesMessage msg = tSession.createBytesMessage();
        	msg.writeDouble(newRate);

        	// Create the publisher and publish the message
			TopicPublisher publisher = tSession.createPublisher(topic);
        	publisher.publish(msg);
        	        	
    	} catch (JMSException jmse) {
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
    	if (argv.length == 2) {
    		topiccf = argv[0];
    		topicName = argv[1];
    	} else {
    		System.out.println("Invalid arguments. Should be: ");
    		System.out.println
               ("java TLender factory topic");
    		System.exit(0);
    	}
      
    	TLender lender = new TLender(topiccf, topicName);
      
    	try {
    		// Read all standard input and send it as a message
    		BufferedReader stdin = new BufferedReader
            	(new InputStreamReader(System.in));
    		System.out.println ("TLender Application Started");
    		System.out.println ("Press enter to quit application");
    		System.out.println ("Enter: Rate");
    		System.out.println("\ne.g. 6.8");

    		while (true) {
        		System.out.print("> ");
    			
        		String rate = stdin.readLine();
        		if (rate == null || rate.trim().length( ) <= 0) {
        			lender.exit();
        		}
            
        		// Parse the deal description
        		double newRate = Double.valueOf(rate);
        		lender.publishRate(newRate);
    		}
    	} catch (IOException ioe) {
    	  ioe.printStackTrace( );
    	}
    }
}
