package com.mastering.jms.queue.register;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;

@Stateless
public class MobiGeneratorListenerRegister {

	@Resource(mappedName = "java:jboss/exported/jms/QUEUE.MOBI")
	private Queue queue;
	
	@Inject
	private JMSContext context;
	
	public void receive() {
		JMSConsumer consumer = context.createConsumer(queue);
		
		String text = consumer.receiveBody(String.class);
		
		System.out.println("Mobi message received: " + text);
	}
	
}
