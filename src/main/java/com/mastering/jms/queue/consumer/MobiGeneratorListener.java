package com.mastering.jms.queue.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

public class MobiGeneratorListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			String text = message.getBody(String.class);
			
			System.out.println("Message receive in to Mobi Generator: " + text);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
