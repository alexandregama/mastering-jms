package com.mastering.jms.topic.subscriber;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/TOPIC.FISCAL.DATA"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class FiscalDataWithDiscountMessageDrivenListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		TextMessage text = (TextMessage) message;
		try {
			System.out.println("Message received to generate Fiscal Data with Discount: " + text.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
