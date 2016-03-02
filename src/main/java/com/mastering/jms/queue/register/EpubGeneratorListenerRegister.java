package com.mastering.jms.queue.register;

import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mastering.jms.properties.JndiPropertiesConfiguration;
import com.mastering.jms.queue.consumer.EpubGeneratorListener;

/**
 * 
 * @author alexandregama
 * EpubGeneratorListener will consumer all the message that has been sent even if the server is out.
 * We can create a Queue with Durable configuration pointing to false, but we will receive the message anyway
 *  
 */
public class EpubGeneratorListenerRegister {

	public static void main(String[] args) throws NamingException {
		InitialContext ic = new InitialContext(JndiPropertiesConfiguration.configure());
		
		ConnectionFactory factory = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		
		Queue queue = (Queue) ic.lookup("jms/QUEUE.EPUB");
		
		try (JMSContext context = factory.createContext("jms", "jms2")) {
			JMSConsumer consumer = context.createConsumer(queue);
			consumer.setMessageListener(new EpubGeneratorListener());
			
			context.start();
			
			Scanner scanner = new Scanner(System.in);
			scanner.nextLine();
			scanner.close();
			
			context.stop();
		}
	}
	
}
