package com.solveapuzzle.mapping.agnostic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

public interface IXMLMessageProducer {

	/* (non-Javadoc)
	 * @see com.solveapuzzle.mapping.agnostic.IXMLMessageProducer#run()
	 */
	public void run() throws JMSException;

	/* (non-Javadoc)
	 * @see com.solveapuzzle.mapping.agnostic.IXMLMessageProducer#close()
	 */
	public void close() throws JMSException;

	/* (non-Javadoc)
	 * @see com.solveapuzzle.mapping.agnostic.IXMLMessageProducer#setFactory(javax.jms.ConnectionFactory)
	 */
	public void setFactory(ConnectionFactory factory);

	/* (non-Javadoc)
	 * @see com.solveapuzzle.mapping.agnostic.IXMLMessageProducer#setConnection(javax.jms.Connection)
	 */
	public void setConnection(Connection connection);

	/* (non-Javadoc)
	 * @see com.solveapuzzle.mapping.agnostic.IXMLMessageProducer#setSession(javax.jms.Session)
	 */
	public void setSession(Session session);

	/* (non-Javadoc)
	 * @see com.solveapuzzle.mapping.agnostic.IXMLMessageProducer#setProducer(javax.jms.MessageProducer)
	 */
	public void setProducer(MessageProducer producer);

}