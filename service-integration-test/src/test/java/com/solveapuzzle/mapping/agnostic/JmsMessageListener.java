/*
 * Copyright 2007-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.solveapuzzle.mapping.agnostic;

import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

/**
 * Consumes messages from a JMS queue.
 * 
 * @author David Winterfeldt
 */
@Component
public class JmsMessageListener implements MessageListener {

	private static final Logger logger = LoggerFactory
			.getLogger(JmsMessageListener.class);

	protected static final String PARSER = "parser";
	protected static final String TRANSFORM = "transformKey";

	@Autowired
	private MappingEngine engine;

	@Autowired
	private AtomicInteger counter = null;
	
	@Resource(name="outdestination")
	javax.jms.Destination outDestination = null;

	/**
	 * Implementation of <code>MessageListener</code>.
	 */
	public void onMessage(Message message) {
		try {
			int messageCount = message
					.getIntProperty(JmsMessageProducer.MESSAGE_COUNT);

			logger.info("Processed message '{}'.  value={}", message,
					messageCount);

			counter.incrementAndGet();

			if (message instanceof TextMessage) {
				TextMessage tm = (TextMessage) message;
				String msg = tm.getText();

				logger.info(" Read Transform property "
						+ message.getStringProperty(TRANSFORM));

				try {
					
					String parser = tm.getStringProperty(PARSER);
					String key = tm.getStringProperty(TRANSFORM);
					
					logger.info(" Read Transform propertys "
							+ parser + " " + key);
					
					String response = engine.onTransformEvent(this.createConfig(parser, key),
							tm.getText(), Charset.defaultCharset());

					logger.info(" Response " + response);
					
					logger.info("message ReplyTo ID " + tm.getJMSCorrelationID());

					tm.setJMSDestination(this.outDestination);
					
					javax.jms.Queue queue = (javax.jms.Queue) tm.getJMSDestination();
					logger.info("ReplyTo Queue name = " + queue.getQueueName());
					
					tm.clearBody();
					
					tm.setText(response);
					// Push to response queue
		
					
				} catch (MappingException e) {
					logger.error("Mapping exception from transformation ", e);
					// push to mapping error Queue?
					// May be fixable with input xml change or due to data quality, invalid against schema
					
					throw new RuntimeException(e);
				} catch (ConfigurationException e) {
					logger.error("Configuration exception from transformation",
							e);
					// Can't fix - dead letter queue - but worth tracing what config went missing?
					throw new RuntimeException(e);
				}

			}
		} catch (JMSException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	private MappingConfiguration createConfig(final String parser,
			final String key) {
		// TODO Auto-generated method stub
		return new MappingConfiguration() {

			public String getMappingType() {
				// TODO Auto-generated method stub

				return parser;

			}

			public String getMappingIdentifer() {
				return key;
			}
		};
	}

}
