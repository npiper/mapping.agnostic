package com.solveapuzzle.mapping.agnostic;

import javax.jms.ConnectionFactory;

public interface IXMLMessageConsumer {

	public abstract void run();

	/**
	 * @param factory the factory to set
	 */
	public abstract void setFactory(ConnectionFactory factory);

	/**
	 * @param transactionMode the transactionMode to set
	 */
	public abstract void setTransactionMode(int transactionMode);

	/**
	 * @param queueName the queueName to set
	 */
	public abstract void setQueueName(String queueName);

}