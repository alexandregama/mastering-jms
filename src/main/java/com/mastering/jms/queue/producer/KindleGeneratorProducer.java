package com.mastering.jms.queue.producer;

import java.util.Scanner;

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
		
		try (JMSContext context = factory.createContext("jms", "jms2");  Scanner scanner = new Scanner(System.in)) {
			JMSProducer producer = context.createProducer();
			
			System.out.println("Send your message: ");
			while (scanner.hasNext()) {
				String content = scanner.nextLine();
				producer.send(queue, content);
				
				System.out.println("Content has been sent: " + content);
			}
		}
	}
	
}
