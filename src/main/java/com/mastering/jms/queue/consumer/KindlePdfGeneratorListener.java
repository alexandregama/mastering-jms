package com.mastering.jms.queue.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class KindlePdfGeneratorListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		TextMessage txt = (TextMessage) message;
		
		try {
			System.out.println("Message received to generate Kindle PDF ebook: " + txt.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
