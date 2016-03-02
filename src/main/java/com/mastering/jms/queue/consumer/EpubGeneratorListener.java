package com.mastering.jms.queue.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

public class EpubGeneratorListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		String text;
		try {
			text = message.getBody(String.class);
			
			System.out.println("Message received to generate a new Epub: " + text);
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}

}
