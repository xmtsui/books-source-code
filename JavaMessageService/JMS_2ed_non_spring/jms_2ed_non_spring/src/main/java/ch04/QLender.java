package ch04;

import java.io.*;
import javax.jms.*;
import javax.naming.*;

public class QLender implements MessageListener {
	
    private QueueConnection qConnect = null;    
    private QueueSession qSession = null;
    private Queue requestQ = null;

    public QLender(String queuecf, String requestQueue) {    	
    	try {
    		// Connect to the provider and get the JMS connection
			Context ctx = new InitialContext();
			QueueConnectionFactory qFactory = (QueueConnectionFactory)
				ctx.lookup(queuecf);
			qConnect = qFactory.createQueueConnection();
			
			// Create the JMS Session
			qSession = qConnect.createQueueSession(
				false, Session.AUTO_ACKNOWLEDGE);

			// Lookup the request queue
			requestQ = (Queue)ctx.lookup(requestQueue);

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
        	boolean accepted = false;

        	// Get the data from the message
        	MapMessage msg = (MapMessage)message;
        	double salary = msg.getDouble("Salary");
        	double loanAmt = msg.getDouble("LoanAmount");
        	
        	// Determine whether to accept or decline the loan
        	if (loanAmt < 200000) {
        		accepted = (salary / loanAmt) > .25; 
        	} else {
        		accepted = (salary / loanAmt) > .33;
        	}
        	System.out.println("" +
        		"Percent = " + (salary / loanAmt) + ", loan is " 
        		+ (accepted ? "Accepted!" : "Declined"));
        	
        	// Send the results back to the borrower
        	TextMessage tmsg = qSession.createTextMessage();
        	tmsg.setText(accepted ? "Accepted!" : "Declined");
        	tmsg.setJMSCorrelationID(message.getJMSMessageID());
        	
        	// Create the sender and send the message
			QueueSender qSender = 
				qSession.createSender((Queue)message.getJMSReplyTo());
        	qSender.send(tmsg);

        	System.out.println("\nWaiting for loan requests...");

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
      
    	QLender lender = new QLender(queuecf, requestq);
      
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
