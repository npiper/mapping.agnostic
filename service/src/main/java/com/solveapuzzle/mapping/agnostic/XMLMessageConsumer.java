package com.solveapuzzle.mapping.agnostic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

public class XMLMessageConsumer implements MessageListener, IXMLMessageConsumer {

	private ConnectionFactory factory;
	private int transactionMode;
	private Connection connection;
	private Session session;
	private MessageConsumer consumer;
	private String queueName;

	public void onMessage(Message message) {

		try {
			if (message instanceof TextMessage) {
				TextMessage txtMessage = (TextMessage) message;
				System.out.println("Message received: " + txtMessage.getText());
			} else {
				System.out.println("Invalid message received.");
				throw new RuntimeException("INVALID MESSAGE TYPE");
			}
		} catch (JMSException e) {
			System.out.println("Caught:" + e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.solveapuzzle.mapping.agnostic.IXMLMessageConsumer#run()
	 */
	public void run() {
		try {
			ConnectionFactory factory = getFactory();
			connection = factory.createConnection();
			connection.start();
			session = connection.createSession(false, getTransactionMode());
			Destination destination = session.createQueue(getQueueName());
			consumer = session.createConsumer(destination);
			consumer.setMessageListener(this);
		} catch (Exception e) {
			System.out.println("Caught:" + e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * @return the factory
	 */
	public ConnectionFactory getFactory() {
		return factory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.solveapuzzle.mapping.agnostic.IXMLMessageConsumer#setFactory(javax
	 * .jms.ConnectionFactory)
	 */
	public void setFactory(ConnectionFactory factory) {
		this.factory = factory;
	}

	/**
	 * @return the transactionMode
	 */
	public int getTransactionMode() {
		return transactionMode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.solveapuzzle.mapping.agnostic.IXMLMessageConsumer#setTransactionMode
	 * (int)
	 */
	public void setTransactionMode(int transactionMode) {

		switch (transactionMode) {
		case Session.AUTO_ACKNOWLEDGE:
			this.transactionMode = transactionMode;
			return;
		case Session.CLIENT_ACKNOWLEDGE:
			this.transactionMode = transactionMode;
			return;
		case Session.DUPS_OK_ACKNOWLEDGE:
			this.transactionMode = transactionMode;
			return;
		case Session.SESSION_TRANSACTED:
			this.transactionMode = transactionMode;
			return;
		default:
			throw new RuntimeException(
					"Invalid Session Transaction mode specified "
							+ transactionMode);
		}

	}

	/**
	 * @return the queueName
	 */
	public String getQueueName() {
		return queueName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.solveapuzzle.mapping.agnostic.IXMLMessageConsumer#setQueueName(java
	 * .lang.String)
	 */
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

}
