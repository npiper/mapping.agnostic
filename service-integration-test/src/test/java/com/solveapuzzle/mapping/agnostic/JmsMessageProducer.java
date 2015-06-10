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

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 * Generates JMS messages
 * 
 * @author Neil Piper
 */
@Component
public class JmsMessageProducer {

	private static final Logger logger = LoggerFactory
			.getLogger(JmsMessageProducer.class);

	protected static final String MESSAGE_COUNT = "messageCount";
	protected static final String PARSER = "parser";
	protected static final String TRANSFORM = "transformKey";
	private static final String TEST_TRANSFORM_VIA_SAXON = "testTransformViaSaxon";

	@Resource(name = "outdestination")
	private Destination replyToQueue = null;

	@Autowired
	private JmsTemplate template = null;
	private int messageCount = 3;

	/**
	 * Generates JMS messages
	 * 
	 * @throws IOException
	 */
	@PostConstruct
	public void generateMessages() throws JMSException, IOException {
		for (int i = 0; i < messageCount; i++) {
			final int index = i;
			final String text = "Message number is " + i + ".";

			InputStream inXML = this.getClass().getClassLoader()
					.getResourceAsStream("inXML.xml");
			final String xml = IOUtils.toString(inXML);

			String correlationId = UUID.randomUUID().toString();

			if (replyToQueue == null) {
				throw new RuntimeException("No Reply to Queue!");
			}

			MessageCreator creator = new XmlRequestReplyMessageCreator(
					correlationId, xml, index, replyToQueue);

			javax.jms.Queue queue = (javax.jms.Queue) replyToQueue;
			logger.info("ReplyTo Queue name (Producer)= " + queue.getQueueName());
			
			
			template.send(creator);
		}
	}

}