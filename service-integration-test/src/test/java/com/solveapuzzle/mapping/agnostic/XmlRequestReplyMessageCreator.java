package com.solveapuzzle.mapping.agnostic;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.MessageCreator;

public class XmlRequestReplyMessageCreator implements MessageCreator {
	
    private static final Logger logger = LoggerFactory.getLogger(XmlRequestReplyMessageCreator.class);

    // TODO - move these to a common class
    protected static final String MESSAGE_COUNT = "messageCount";
    protected static final String PARSER = "parser";
    protected static final String TRANSFORM = "transformKey";
	private static final String TEST_TRANSFORM_VIA_SAXON = "testTransformViaSaxon";
	
    private String correlId = null;
    private String msgText = null;
    private Destination replyToQueue = null;
    private int indexCount;

	public XmlRequestReplyMessageCreator(String id,String text, int index, Destination replyDestination)
	{
		this.msgText = text;
		this.setCorrelIationId(id);
		this.setReplyToQueue(replyDestination);
		indexCount = index;
		
	}
	
	public Message createMessage(Session session) throws JMSException {
		TextMessage message = session.createTextMessage(this.msgText);
		 message.setStringProperty(TRANSFORM, TEST_TRANSFORM_VIA_SAXON);
         message.setStringProperty(PARSER, "xercesMapper");
         message.setIntProperty(MESSAGE_COUNT, indexCount);
         
         // Set up Request/Response parameters
         message.setJMSCorrelationID(getCorrelIationId());
         message.setJMSReplyTo(getReplyToQueue());

         logger.info(" Transform property " +
         	message.getStringProperty(TRANSFORM));
         
         return message;
	}

	private Destination getReplyToQueue() {
		return replyToQueue;
	}

	private void setReplyToQueue(Destination replyToQueue) {
		this.replyToQueue = replyToQueue;
	}

	private String getCorrelIationId() {
		return correlId;
	}

	private void setCorrelIationId(String correlId) {
		this.correlId = correlId;
	}

}
