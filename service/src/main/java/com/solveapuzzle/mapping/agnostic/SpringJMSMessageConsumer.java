package com.solveapuzzle.mapping.agnostic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import javax.jms.ConnectionFactory;

@Component
public class SpringJMSMessageConsumer implements IXMLMessageConsumer {

    /**
     * Get a copy of the application context
     */
    @Autowired
    ConfigurableApplicationContext context;

    /**
     * When you receive a message, print it out, then shut down the application.
     * Finally, clean up any ActiveMQ server stuff.
     */
    @JmsListener(destination = "mailbox-destination", containerFactory = "myJmsContainerFactory")
    public void receiveMessage(String message) {
    	
    }
	
	public void run() {
		// TODO Auto-generated method stub

	}

	public void setFactory(ConnectionFactory factory) {
		// TODO Auto-generated method stub

	}

	public void setTransactionMode(int transactionMode) {
		// TODO Auto-generated method stub

	}

	public void setQueueName(String queueName) {
		// TODO Auto-generated method stub

	}

}
