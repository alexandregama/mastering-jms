package com.mastering.jms.topic.subscriber;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

public class PointsGeneratorListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			String text = message.getBody(String.class);
			
			System.out.println("Message received to generate more Points: " + text);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
		}
	}
}
