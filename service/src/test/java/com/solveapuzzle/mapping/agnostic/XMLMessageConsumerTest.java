package com.solveapuzzle.mapping.agnostic;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class XMLMessageConsumerTest {

	XMLMessageConsumer consumer = new XMLMessageConsumer();
	
	@Before
	public void setUp() throws JMSException
	{
		ConnectionFactory factory = Mockito.mock(ConnectionFactory.class);
		Connection conn = Mockito.mock(Connection.class);
		Session session = Mockito.mock(Session.class);
		Queue dest = Mockito.mock(Queue.class);
		
		MessageConsumer msgConsumer = Mockito.mock(MessageConsumer.class);
		
		Mockito.when(factory.createConnection()).thenReturn(conn);
		
		Mockito.when(conn.createSession(Mockito.eq(false), Mockito.eq(Session.AUTO_ACKNOWLEDGE))).thenReturn(session);
		
		Mockito.when(session.createQueue(Mockito.eq("testQueue"))).thenReturn(dest);
		
		Mockito.when(session.createConsumer(Mockito.isA(Queue.class))).thenReturn(msgConsumer);
		
		
		
		consumer.setFactory(factory);
		consumer.setQueueName("testQueue");
		consumer.setTransactionMode(Session.AUTO_ACKNOWLEDGE);
	}
	
	@Test
	public void testRunConsumer()
	{
		// Should not throw exceptions
		consumer.run();
	}
	
	@Test
	public void testMessageConsume() throws JMSException
	{
		TextMessage message = Mockito.mock(TextMessage.class);
		Mockito.when(message.getText()).thenReturn("MESSAGE TEXT");
		
		consumer.onMessage(message);
	}
	
	@Test
	public void testInvalidMessageType()
	{
		BytesMessage bytesMsg = Mockito.mock(BytesMessage.class);
		try
		{
		  consumer.onMessage(bytesMsg);
		}
		catch (RuntimeException re)
		{
			Assert.assertEquals(re.getMessage(),"INVALID MESSAGE TYPE");
		}
	}
	
}
