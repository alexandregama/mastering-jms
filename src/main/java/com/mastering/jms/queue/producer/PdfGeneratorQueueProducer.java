package com.mastering.jms.queue.producer;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mastering.jms.properties.JndiPropertiesConfiguration;

public class PdfGeneratorQueueProducer {

	public static void main(String[] args) throws NamingException {
		InitialContext ic = new InitialContext(JndiPropertiesConfiguration.configure());
		
		ConnectionFactory factory = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		
		Queue queue = (Queue) ic.lookup("jms/QUEUE.PDF");
		
		try (JMSContext context = factory.createContext("jms", "jms2")) {
			JMSProducer producer = context.createProducer();
			producer.send(queue, "{\"id\": \"1\", \"code\": \"SOA\", \"name\": \"SOA Architecture\"}");
				
			System.out.println("Message to Queue PDF was sent");
		}
	}
}
