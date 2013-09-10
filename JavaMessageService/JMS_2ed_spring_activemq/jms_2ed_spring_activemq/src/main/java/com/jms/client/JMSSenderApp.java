package com.jms.client;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.StreamMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class JMSSenderApp {

	public static void main(String[] args) {
		
	    try {
			ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("app-context.xml");
			JMSSender jmsSender = (JMSSender)ctx.getBean("jmsSender");
//			JMSSenderReply jmsSender = (JMSSenderReply)ctx.getBean("jmsSenderReply");
//			JMSSenderConvert jmsSender = (JMSSenderConvert)ctx.getBean("jmsSenderConvert");
			jmsSender.sendMessage();
	    	System.exit(0);
	    } catch (Exception exception) {
	        exception.printStackTrace();
	    }
	}	
}
