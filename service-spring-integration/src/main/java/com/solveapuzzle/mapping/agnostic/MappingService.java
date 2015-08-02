package com.solveapuzzle.mapping.agnostic;

import java.net.URL;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
public class MappingService implements MappingGateway {

	private static final Logger logger = LoggerFactory
			.getLogger(MappingService.class);

	protected static final String PARSER = "parser";
	protected static final String TRANSFORM = "transformKey";

	@Autowired
	private MappingEngine engine;

	@ServiceActivator
	public MappingResponse executeMapping(MappingRequest request) {

		try {
			long preTransformTimeMillis = new java.util.Date().getTime();
			
			String body = request.getBody();
			MappingConfiguration config = request.getMappingConfiguration();
			Charset c = request.getMappingConfiguration().getCharacterEncoding();
			
			if (engine == null) {throw new NullPointerException("No engine autowired");}
			
			String result = engine.onTransformEvent(
					request.getMappingConfiguration(), request.getBody(),
					request.getMappingConfiguration().getCharacterEncoding());
			long postTransformTimeMillis = new java.util.Date().getTime();
			long processingTime = postTransformTimeMillis
					- preTransformTimeMillis;

			return createNewMappingResponse(processingTime, result);

		} catch (MappingException e) {
			throw new RuntimeException(e);
		} catch (ConfigurationException e) {
			throw new RuntimeException(e);
		}

	}

	private MappingResponse createNewMappingResponse(final long processingTime,
			final String result) {
		// TODO Auto-generated method stub
		return new MappingResponse() {

			public long getTransformationTimeMilliseconds() {
				// TODO Auto-generated method stub
				return processingTime;
			}

			public URL getResultURL() {
				// TODO Auto-generated method stub
				return null;
			}

			public String getResult() {
				// TODO Auto-generated method stub
				return result;
			}
		};
	}

}
