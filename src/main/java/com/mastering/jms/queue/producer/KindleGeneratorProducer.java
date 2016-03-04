package com.mastering.jms.queue.producer;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mastering.jms.properties.JndiPropertiesConfiguration;

public class KindleGeneratorProducer {

	public static void main(String[] args) throws NamingException {
		InitialContext ic = new InitialContext(JndiPropertiesConfiguration.configure());
		
		ConnectionFactory factory = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		
		Destination queue = (Destination) ic.lookup("jms/QUEUE.KINDLE");
		
		try (JMSContext context = factory.createContext("jms", "jms2")) {
			JMSProducer producer = context.createProducer();
			producer.send(queue, "{\"id\": \"1\", \"code\": \"SOA\", \"name\": \"SOA Architecture\"}");
			
			System.out.println("Message Sent to Kindle Queue");
		}
	}
	
}
