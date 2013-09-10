package com.jms.server;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.TextMessage;

import com.jms.common.Trade2Data;
import com.jms.common.TradeData;

public class JMSReceiverPOJO {

	public void processRequest(Message message) {
		try {
	    	if (message instanceof TextMessage) {
	    		System.out.println(((TextMessage)message).getText());
	    	} else if (message instanceof MapMessage) {
	    		MapMessage mmsg = (MapMessage)message;
	    		System.out.println(
	    				mmsg.getLong("acctId") + ", " + 
	    				mmsg.getString("side") + ", " + 
	    				mmsg.getString("symbol") + ", " + 
	    				mmsg.getDouble("shares"));
	    	} else {
	    		throw new IllegalStateException("Message type not supported");
	    	}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public void processRequest(Map message) {
		System.out.println(message.get("acctId") + ", " + 
				message.get("side") + ", " + 
				message.get("symbol") + ", " + 
				message.get("shares"));
	}

	public void processRequest(String message) {
		System.out.println("Received Message: " + message);
	}

	public void handleMessage(Map message) {
		System.out.println("Received Message A: " + message);
	}

	public void handleMessage(TradeData message) {
		System.out.println("Received a tradedata object: ");
	}
	
	public void handleMessage(Trade2Data message) {
		System.out.println("Received a trade2data object: ");
	}
}




