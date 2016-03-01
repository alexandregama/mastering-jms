package com.mastering.jms.producer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class EbookGeneratorListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		TextMessage txtMessage = (TextMessage) message;
		
		try {
			System.out.println("Received message: " + txtMessage.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
}
