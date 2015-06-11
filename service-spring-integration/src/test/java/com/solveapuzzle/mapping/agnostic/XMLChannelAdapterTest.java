package com.solveapuzzle.mapping.agnostic;

import org.junit.Test;

public class XMLChannelAdapterTest {

	
	@Test
	public void testChannelAdapterDemo() throws InterruptedException {
		
		//Entry
		
		// Send XML To Channel
		
		
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
		
		// CHANNEL:  MappingFault Channel (No retry)
		// CHANNEL:  Error Channel (Can retry)
		
	}
}
