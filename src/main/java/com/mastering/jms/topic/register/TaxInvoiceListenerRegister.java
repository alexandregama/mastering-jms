package com.mastering.jms.topic.register;

import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mastering.jms.properties.JndiPropertiesConfiguration;
import com.mastering.jms.topic.subscriber.TaxInvoiceListener;

public class TaxInvoiceListenerRegister {

	public static void main(String[] args) throws NamingException {
		InitialContext ic = new InitialContext(JndiPropertiesConfiguration.configure());
		
		ConnectionFactory factory = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		
		Topic topic = (Topic) ic.lookup("jms/TOPIC.INVOICE");
		
		try (JMSContext context = factory.createContext("jms", "jms2")) {
			JMSConsumer consumer = context.createConsumer(topic);
			
			consumer.setMessageListener(new TaxInvoiceListener());
			
			context.start();
			System.out.println("TaxInvoice Topic Consumer is up and waiting for messages");
			
			Scanner scanner = new Scanner(System.in);
			scanner.nextLine();
			
			scanner.close();
			context.stop();
		}
	}
	
}
