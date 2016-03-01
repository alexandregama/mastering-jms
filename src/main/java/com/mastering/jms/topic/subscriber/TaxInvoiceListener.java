package com.mastering.jms.topic.subscriber;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class TaxInvoiceListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		TextMessage txtMessage = (TextMessage) message;
		
		try {
			System.out.println("Message received to generate Tax Invoice: " + txtMessage.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
}
