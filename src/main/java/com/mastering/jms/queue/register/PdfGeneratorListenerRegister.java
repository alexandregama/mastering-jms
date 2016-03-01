package com.mastering.jms.queue.register;

import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mastering.jms.properties.JndiPropertiesConfiguration;
import com.mastering.jms.queue.consumer.PdfGeneratorListener;

public class PdfGeneratorListenerRegister {

	public static void main(String[] args) throws NamingException {
		InitialContext ic = new InitialContext(JndiPropertiesConfiguration.configure());
		
		ConnectionFactory factory = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		
		Queue queue = (Queue) ic.lookup("jms/QUEUE.PDF");
		
		try (JMSContext context = factory.createContext("jms", "jms2")) {
			JMSConsumer consumer = context.createConsumer(queue);
			consumer.setMessageListener(new PdfGeneratorListener());
			
			context.start();
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("Pdf Generator Listener is up and waiting for messages...");
			
			scanner.nextLine();
			scanner.close(); //Enter to stop
			
			context.stop();
		}
	}
	
}
