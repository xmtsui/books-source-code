package com.jms.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BootstrapServer {

	public static void main(String[] args) {
		
	    try {
	    	System.out.println("Loading Spring Context...");
			Object obj = new ClassPathXmlApplicationContext("app-context.xml");
	    	System.out.println("Spring Context Loaded");
			synchronized (obj) {
				obj.wait();
			}
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
}
