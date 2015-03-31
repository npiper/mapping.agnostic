package com.solveapuzzle.mapping.agnostic;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.MessageProducer;
 


public class XMLMessageProducer implements IXMLMessageProducer {

	private ConnectionFactory factory;
    private Connection connection;
    private Session session;
    private MessageProducer producer;
    
    
    public XMLMessageProducer(ConnectionFactory factory, String queueName) throws JMSException
    {
        this.setFactory(factory);
        setConnection(factory.createConnection());
        getConnection().start();
        setSession(getConnection().createSession(false, Session.AUTO_ACKNOWLEDGE));
        Destination destination = getSession().createQueue(queueName);
        setProducer(getSession().createProducer(destination));
        
    }
 
    /* (non-Javadoc)
	 * @see com.solveapuzzle.mapping.agnostic.IXMLMessageProducer#run()
	 */
    /* (non-Javadoc)
	 * @see com.solveapuzzle.mapping.agnostic.IXMLMessageProducer#run()
	 */
    public void run() throws JMSException
    {
        for (int i = 0; i < 100; i++)
        {
            System.out.println("Creating Message " + i);
            Message message = getSession().createTextMessage("Hello World!");
            getProducer().send(message);
        }
    }
 
    /* (non-Javadoc)
	 * @see com.solveapuzzle.mapping.agnostic.IXMLMessageProducer#close()
	 */
    /* (non-Javadoc)
	 * @see com.solveapuzzle.mapping.agnostic.IXMLMessageProducer#close()
	 */
    public void close() throws JMSException
    {
        if (getConnection() != null)
        {
            getConnection().close();
        }
    }

	/**
	 * @return the factory
	 */
	public ConnectionFactory getFactory() {
		return factory;
	}

	/* (non-Javadoc)
	 * @see com.solveapuzzle.mapping.agnostic.IXMLMessageProducer#setFactory(javax.jms.ConnectionFactory)
	 */
	/* (non-Javadoc)
	 * @see com.solveapuzzle.mapping.agnostic.IXMLMessageProducer#setFactory(javax.jms.ConnectionFactory)
	 */
	public void setFactory(ConnectionFactory factory) {
		this.factory = factory;
	}

	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/* (non-Javadoc)
	 * @see com.solveapuzzle.mapping.agnostic.IXMLMessageProducer#setConnection(javax.jms.Connection)
	 */
	/* (non-Javadoc)
	 * @see com.solveapuzzle.mapping.agnostic.IXMLMessageProducer#setConnection(javax.jms.Connection)
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * @return the session
	 */
	public Session getSession() {
		return session;
	}

	/* (non-Javadoc)
	 * @see com.solveapuzzle.mapping.agnostic.IXMLMessageProducer#setSession(javax.jms.Session)
	 */
	/* (non-Javadoc)
	 * @see com.solveapuzzle.mapping.agnostic.IXMLMessageProducer#setSession(javax.jms.Session)
	 */
	public void setSession(Session session) {
		this.session = session;
	}

	/**
	 * @return the producer
	 */
	public MessageProducer getProducer() {
		return producer;
	}

	/* (non-Javadoc)
	 * @see com.solveapuzzle.mapping.agnostic.IXMLMessageProducer#setProducer(javax.jms.MessageProducer)
	 */
	/* (non-Javadoc)
	 * @see com.solveapuzzle.mapping.agnostic.IXMLMessageProducer#setProducer(javax.jms.MessageProducer)
	 */
	public void setProducer(MessageProducer producer) {
		this.producer = producer;
	}
}
