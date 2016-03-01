package com.mastering.jms.topic.producer;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mastering.jms.properties.JndiPropertiesConfiguration;

public class TaxInvoiceTopicProducer {

	public static void main(String[] args) throws NamingException {
		InitialContext ic = new InitialContext(JndiPropertiesConfiguration.configure());
		
		ConnectionFactory factory = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		
		Topic topic = (Topic) ic.lookup("jms/TOPIC.INVOICE");
		
		try (JMSContext context = factory.createContext("jms", "jms2")) {
			JMSProducer producer = context.createProducer();
			producer.send(topic, "{\"id\": \"1\", \"value\": \"39.90\"}");
			
			System.out.println("Message sent to TaxInvoice Topic!");
		}
	}
	
}
