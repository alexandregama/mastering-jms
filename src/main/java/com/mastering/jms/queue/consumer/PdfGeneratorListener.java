package com.mastering.jms.queue.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class PdfGeneratorListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		TextMessage txtMessage = (TextMessage) message;
			
		try {
			System.out.println("Message to pdf generator received: " + txtMessage.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
}
