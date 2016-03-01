package com.mastering.jms.register;

import java.util.Properties;
import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mastering.jms.producer.EbookGeneratorListener;

public class EbookGeneratorListenerRegister {

	public static void main(String[] args) throws NamingException {
		Properties properties = new Properties();
		properties.setProperty("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
		properties.setProperty("java.naming.provider.url", "http-remoting://localhost:8080");
		
		InitialContext ic = new InitialContext(properties);
		
		ConnectionFactory factory = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		
		Queue queue = (Queue) ic.lookup("jms/QUEUE.EBOOK");
		
		try (JMSContext context = factory.createContext("jms", "jms2")) {
			JMSConsumer consumer = context.createConsumer(queue);
			consumer.setMessageListener(new EbookGeneratorListener());
			
			context.start();
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("EbookGenerator is up an waiting for messages...");
			
			scanner.nextLine(); //Enter to stop receive messages
			scanner.close();
			context.stop();
		}
	}
}
