package com.mastering.jms.producer;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MessageSenderToQueue {

	public static void main(String[] args) throws NamingException {
		Properties properties = new Properties();
		properties.setProperty("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
		properties.setProperty("java.naming.provider.url", "http-remoting://localhost:8080");
		InitialContext ic = new InitialContext(properties);
		
		ConnectionFactory factory = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		
		Queue queue = (Queue) ic.lookup("jms/QUEUE.EBOOK");
		
		try (JMSContext context = factory.createContext("jms", "jms2")) {
			JMSProducer producer = context.createProducer();
			
			producer.send(queue, "{\"id\": \"1\", \"code\": \"SOA\", \"name\": \"SOA Architecture\"}");
			
			System.out.println("Message sent!");
		}
		
	}
	
}
