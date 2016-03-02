package com.mastering.jms.topic.subscriber;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class AuthorNotificationListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		TextMessage txtMessage = (TextMessage) message;
		
		try {
			String text = txtMessage.getText();
			
			System.out.println("Message received to notificate the book author: " + text);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
}
