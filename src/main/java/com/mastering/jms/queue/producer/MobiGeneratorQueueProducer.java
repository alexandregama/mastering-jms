package com.mastering.jms.queue.producer;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

@Stateless
public class MobiGeneratorQueueProducer {

	@Resource(mappedName = "java:jboss/exported/jms/QUEUE.MOBI")
	private Queue queue;
	
	@Inject
	private JMSContext context;
	
	public void send() {
		System.out.println("Sending a message to Mobi Generator Queue");
		
		context.createProducer().send(queue, "My Text");
	}
	
}
