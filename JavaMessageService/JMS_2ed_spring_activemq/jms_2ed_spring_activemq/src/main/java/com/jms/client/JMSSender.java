package com.jms.client;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class JMSSender {

	public void sendMessage() throws Exception {

//		MessageCreator msg = new MessageCreator() {
//			public Message createMessage(Session session) throws JMSException {
//
//				StringBuffer xml = new StringBuffer("\n");
//	    		xml.append("<?xml version=\"1.0\"?>" + "\n");
//	    		xml.append("<trade>"                 + "\n");
//	    		xml.append("  <acctId>998</acctId>"  + "\n");
//	    		xml.append("  <side>BUY</side>"      + "\n");
//	    		xml.append("  <symbol>AAPL</symbol>" + "\n");
//	    		xml.append("  <shares>600</shares>"  + "\n");
//	   			xml.append("</trade>"                + "\n");
//
//	            return session.createTextMessage(xml.toString());
//	        }
//		};

		MessageCreator msg = new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage msg = session.createTextMessage("TEST 1");
//	            MapMessage msg = session.createMapMessage();
//	            msg.setLong("acctId", 12345);
//	            msg.setString("side", "SELL");
//	            msg.setString("symbol", "AAPL");
//	            msg.setDouble("shares", 250.0);
	           	return msg;
	        }
		};
			
		jmsTemplate.send(queueName, msg);
		System.out.println("Message Sent...");        			
	}

	public JmsTemplate jmsTemplate = null;
	public String queueName = null;
	
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	

}











