package com.mastering.jms.topic.register;

import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mastering.jms.properties.JndiPropertiesConfiguration;
import com.mastering.jms.topic.subscriber.AuthorNotificationListener;

public class AuthorNotificationRegister {

	public static void main(String[] args) throws NamingException {
		InitialContext ic = new InitialContext(JndiPropertiesConfiguration.configure());
		
		ConnectionFactory factory = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		
		Topic topic = (Topic) ic.lookup("jms/TOPIC.AUTHOR");
		
		try (JMSContext context = factory.createContext("jms", "jms2")) {
			context.setClientID("Author");
			JMSConsumer consumer = context.createDurableConsumer(topic, "AuthorNotification");
			consumer.setMessageListener(new AuthorNotificationListener());
			
			context.start();
			
			Scanner scanner = new Scanner(System.in);
			scanner.nextLine();
			scanner.close();
			
			context.stop();
		}
	}
	
}
