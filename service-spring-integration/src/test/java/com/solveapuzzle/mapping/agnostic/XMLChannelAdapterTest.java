package com.solveapuzzle.mapping.agnostic;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

public class XMLChannelAdapterTest {

	
	private final static String[] configFilesChannelAdapter = {
		"/META-INF/spring/integration/map-engine/common.xml",
		"/META-INF/spring/integration/map-engine/outboundChannelAdapter.xml"
	};
	
	@Test
	public void testChannelAdapterDemo() throws InterruptedException, IOException {

		
		InputStream inXML = this.getClass().getClassLoader()
				.getResourceAsStream("inXML.xml");
		final String xml = IOUtils.toString(inXML);
		
		final GenericXmlApplicationContext applicationContext = new GenericXmlApplicationContext(configFilesChannelAdapter);

		Message<String> xmlMsg = MessageBuilder.withPayload(xml).build();
		

		//Entry
		
		// Send XML To Channel
		final MessageChannel xmlToJmsoutChannel = applicationContext.getBean("xmlToJmsoutChannel", MessageChannel.class);
		xmlToJmsoutChannel.send(xmlMsg);
		
		
		// For the test case -- construct a Spring Integration 'Message'
		// Get the Channel 'XmlInToJMSOutChannel'
		// Send a messsage to the Channel
		
		//ID	java.util.UUID
		//TIMESTAMP	java.lang.Long
		//CORRELATION_ID	java.lang.Object
		//REPLY_CHANNEL	java.lang.Object (can be a String or MessageChannel)
		//ERROR_CHANNEL	java.lang.Object (can be a String or MessageChannel)
		//SEQUENCE_NUMBER	java.lang.Integer
		//SEQUENCE_SIZE	java.lang.Integer
		//EXPIRATION_DATE	java.lang.Long
		//PRIORITY	java.lang.Integer
		
		
		// CHANNEL: XmlInToJMSOutChannel
		//  --> outboundChannelAdapter --> XML.IN Queue
		// Channel 'adapts' to JMS
		
		// [ XML.IN ] Queue
		
		// ChannelAdaptor: message-driven-channel-adapter
		//   --> Destination: XML.IN
		//   --> channel == JMSInToXmlOutChannel
		
		// CHANNEL:  JMSIntoXMLOutChannel
		
		  // Once Here -- TRANSFORM!
		
		// CHANNEL:  ResponseChannel (OK)
		
		// Connect to the Outbound channel - (With correlation ID?)
		
		// CHANNEL:  MappingFault Channel (No retry)
		// CHANNEL:  Error Channel (Can retry)
		
	}
}
