package com.jms.client;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;

import com.jms.common.Trade2Data;
import com.jms.common.TradeData;

public class JMSSenderConvert {

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

//		MessageCreator msg = new MessageCreator() {
//			public Message createMessage(Session session) throws JMSException {
//
//	            MapMessage msg = session.createMapMessage();
//	            msg.setLong("acctId", 12345);
//	            msg.setString("side", "SELL");
//	            msg.setString("symbol", "AAPL");
//	            msg.setDouble("shares", 250.0);
//	           	return msg;
//	        }
//		};
			
		StringBuffer xml = new StringBuffer("\n");
		xml.append("<?xml version=\"1.0\"?>" + "\n");
		xml.append("<trade>"                 + "\n");
		xml.append("  <acctId>998</acctId>"  + "\n");
		xml.append("  <side>BUY</side>"      + "\n");
		xml.append("  <symbol>AAPL</symbol>" + "\n");
		xml.append("  <shares>100</shares>"  + "\n");
		xml.append("</trade>"                + "\n");

		MessagePostProcessor postProcessor = new MessagePostProcessor() {
			public Message postProcessMessage(Message message) throws JMSException {
	            message.setStringProperty("Username", "fred123");
	            message.setJMSPriority(7);
	            return message;
	        }
		};

		TradeData trade = new TradeData();
		Trade2Data trade2 = new Trade2Data();
		//jmsTemplate.convertAndSend(queueDest, "BUY, AAPL, 100 Shares");
		//jmsTemplate.convertAndSend(queueName, xml.toString());
		jmsTemplate.convertAndSend(trade, postProcessor);
		System.out.println("Message Sent...");        			
	}

	public JmsTemplate jmsTemplate = null;
	public String queueName = null;
	public Destination queueDest = null;
	
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	public void setQueueDest(Destination queueName) {
		this.queueDest = queueName;
	}
	

}











