package com.jms.client;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.StreamMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class JMSSenderReply {

	Message sentMessage;
	
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
//	    		xml.append("  <shares>500</shares>"  + "\n");
//	   			xml.append("</trade>"                + "\n");
//
//	            return session.createTextMessage(xml.toString());
//	        }
//		};

		MessageCreator mc = new MessageCreator() {
			
			public Message createMessage(Session session) throws JMSException {

	            MapMessage msg = session.createMapMessage();
	            msg.setLong("acctId", 345);
	            msg.setString("side", "SELL");
	            msg.setString("symbol", "AAPL");
	            msg.setDouble("shares", 500.0);
	            Destination dest = jmsTemplate.getDestinationResolver().resolveDestinationName(session, replyQ, false);
	            msg.setJMSReplyTo(dest);
	            sentMessage = msg;
	           	return msg;
	        }			
		};
			
		jmsTemplate.send(requestQ, mc);
		System.out.println("Message Sent...");        			
		
		String filter = "JMSCorrelationID = '" + sentMessage.getJMSMessageID() + "'";
		StreamMessage smsg = (StreamMessage)jmsTemplate.receiveSelected(replyQ, filter);
		System.out.println("Conf = " + smsg.readLong());
	}

	public JmsTemplate jmsTemplate = null;
	public String requestQ = null;
	public String replyQ = null;
	
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	public void setRequestQ(String requestQ) {
		this.requestQ = requestQ;
	}
	
	public void setReplyQ(String replyQ) {
		this.replyQ = replyQ;
	}

}











