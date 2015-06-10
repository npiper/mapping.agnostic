package com.solveapuzzle.mapping.agnostic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class XmlOutJmsMessageListener implements MessageListener {

	private static final Logger logger = LoggerFactory
			.getLogger(XmlOutJmsMessageListener.class);

	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		if (message instanceof TextMessage) {
			TextMessage tm = (TextMessage) message;
			String msg;
			try {
				msg = tm.getText();
				logger.info("Message - CorrID: " + message.getJMSCorrelationID() + " ID: " + message.getJMSMessageID() + " text: " + msg );

			} catch (JMSException e) {
				throw new RuntimeException("Unexpected internal JMS Exception ",e);
			}
		}
	}
}
