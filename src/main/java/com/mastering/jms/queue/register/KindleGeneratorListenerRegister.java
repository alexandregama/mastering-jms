package com.mastering.jms.queue.register;

import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mastering.jms.properties.JndiPropertiesConfiguration;
import com.mastering.jms.queue.consumer.KindleGeneratorListener;

public class KindleGeneratorListenerRegister {

	public static void main(String[] args) throws NamingException {
		InitialContext ic = new InitialContext(JndiPropertiesConfiguration.configure());
		
		ConnectionFactory factory = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		
		Destination queue = (Destination) ic.lookup("jms/QUEUE.KINDLE");
		
		try (JMSContext context = factory.createContext("jms", "jms2")) {
			JMSConsumer consumer = context.createConsumer(queue);
			consumer.setMessageListener(new KindleGeneratorListener());
			context.start();
			
			Scanner scanner = new Scanner(System.in);
			scanner.nextLine(); //Enter to stop watching
			scanner.close();
			
			context.stop();
			System.out.println("KindleGeneratorListener has been stoped!");
		}
	}
	
}
