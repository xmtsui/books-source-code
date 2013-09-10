package com.jms.server;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.TextMessage;

public class JMSReceiverPOJO {

	public void receive(Message message) {
		try {
			//System.out.println("Message Received From: " + message.getStringProperty("Username"));
	    	if (message instanceof TextMessage) {
	    		System.out.println(((TextMessage)message).getText());
	    	} else if (message instanceof MapMessage) {
	    		MapMessage mmsg = (MapMessage)message;
	    		System.out.println(mmsg.getLong("acctId") + ", " + 
	    				mmsg.getString("side") + ", " + 
	    				mmsg.getString("symbol") + ", " + 
	    				mmsg.getDouble("shares"));
	    	} else {
	    		throw new UnsupportedOperationException("Message Type Not Supported");
	    	}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public void receiveMap(Map message) {
		System.out.println(message.get("acctId") + ", " + 
				message.get("side") + ", " + 
				message.get("symbol") + ", " + 
				message.get("shares"));
	}

	public void receiveString(String message) {
		System.out.println("Received Message: " + message);
	}

}




