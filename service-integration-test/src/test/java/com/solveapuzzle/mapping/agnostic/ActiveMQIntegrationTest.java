package com.solveapuzzle.mapping.agnostic;

// Test is referenced from Spring by Example -- http://www.springbyexample.org/examples/simple-spring-jms.html
// Starts up a local ActiveMQ container with a XML.IN Queue
// Starts up a JMS Listener & JMS Producer
// Sends 100 messages
// Confirms Listener receives 100 messages

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.JMSException;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ActiveMQIntegrationTest {

	final Logger logger = LoggerFactory
			.getLogger(ActiveMQIntegrationTest.class);

	@Autowired
	private AtomicInteger counter;

	@Autowired
	private JmsMessageProducer producer;

	@Autowired
	JmsMessageListener listener;

	@Autowired
	MappingEngine engine;

	@Autowired
	MappingRepositoryInternal repository;

	private static final String TEST_TRANSFORM_VIA_SAXON = "testTransformViaSaxon";

	@Before
	public void setUp() throws IOException {

		MappingRecord record = new MappingRecord();
		record.setKey(TEST_TRANSFORM_VIA_SAXON);

		InputStream is = this.getClass().getClassLoader()
				.getResourceAsStream("Stylesheet.xslt");
		String xslt = IOUtils.toString(is);

		record.setXmlContent(xslt);
		repository.insertMappingRecord(record);

	}

	@Test
	public void testMessageOne() throws InterruptedException, JMSException {

		String source = repository.getMappingSource(TEST_TRANSFORM_VIA_SAXON);

		if (source == null || source.isEmpty()) {
			throw new RuntimeException(
					"Did not set up repository correctly - no XSLT record");
		}

		System.out.println("XSLT returned " + source);

		assertNotNull("Counter is null.", counter);

		int expectedCount = 3;

		logger.info("Testing...");

		// give listener a chance to process messages
		Thread.sleep(2 * 1000);

		assertEquals("Message is not '" + expectedCount + "'.", expectedCount,
				counter.get());
	}

}
