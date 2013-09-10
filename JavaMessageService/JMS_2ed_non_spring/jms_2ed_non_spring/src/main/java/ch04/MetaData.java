package ch04;

import java.util.Enumeration;

import javax.jms.ConnectionMetaData;
import javax.jms.JMSException;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MetaData {

    public static void main(String[] args) {    	
    	try {
			// Connect to the provider and get the JMS connection
			Context ctx = new InitialContext();
			QueueConnectionFactory qFactory = (QueueConnectionFactory)
				ctx.lookup("QueueCF");
			QueueConnection qConnect = qFactory.createQueueConnection();
			ConnectionMetaData metadata = qConnect.getMetaData();
			System.out.println("JMS Version:  " + metadata.getJMSMajorVersion() + "." + metadata.getJMSMinorVersion());
			System.out.println("JMS Provider: " + metadata.getJMSProviderName());
			System.out.println("JMS Provider Version: " + metadata.getProviderMajorVersion());
			System.out.println("JMSX Properties Supported: ");
			Enumeration e = metadata.getJMSXPropertyNames();
			while (e.hasMoreElements()) {
				System.out.println("   " + e.nextElement());
			}
			
		} catch (JMSException jmse) {
			jmse.printStackTrace( ); 
			System.exit(1);
		} catch (NamingException jne) {
		    jne.printStackTrace( ); 
		    System.exit(1);
		}
    }
	
}
