package com.jms.server;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class JMSReceiverAsync1 implements MessageListener {

	public void onMessage(Message message) {
		try {
	    	if (message instanceof TextMessage) {
	    		System.out.println(((TextMessage)message).getText());
	    	} else if (message instanceof MapMessage) {
	    		MapMessage mmsg = (MapMessage)message;
	    		System.out.println(mmsg.getLong("acctId") + ", " + 
	    				mmsg.getString("side") + ", " + 
	    				mmsg.getString("symbol") + ", " + 
	    				mmsg.getDouble("shares"));
	    	} else {
	    		throw new IllegalStateException("Message Type Not Supported");
	    	}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
