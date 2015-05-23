package com.solveapuzzle.mapping.agnostic;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
import java.nio.charset.Charset;

import javax.xml.transform.Source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MappingEngineImpl implements MappingEngine {

	@Autowired
	MappingRepository repository;
	
	MapperFactory<Source,OutputStream> factory;
	
	public void setMapperFactory(MapperFactory<Source,OutputStream> factory)
	{
		this.factory = factory;
	}
	
	public void setMappingRepository(MappingRepository repository)
	{
		this.repository = repository;
	}
	
	public String onTransformEvent(MappingConfiguration config, String Body, Charset encoding) throws MappingException, ConfigurationException {
		
		if (config == null) { throw new RuntimeException("No configuration object provided - null exception");}
		
		if (repository == null) { throw new RuntimeException("Repository not injected - null pointer"); }

		Mapper<Source,OutputStream> mapper = factory.createMapper(config.getMappingType());
		
		
		java.lang.String mappingToApply = repository.getMappingSource(config.getMappingIdentifer());
		
		// XSLT Source - apply the XSLT file
		javax.xml.transform.Source xmlSource = new javax.xml.transform.stream.StreamSource(
				new StringReader(Body));
		
		if (mappingToApply == null)
		{
			throw new UnexpectedException();
		}
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		Writer out
		   = new BufferedWriter(new OutputStreamWriter(bos));
		
		
		mapper.map(xmlSource,bos,config);
		
		try
		{
		   String toReturn = new String( bos.toByteArray(), encoding );
			
			return toReturn;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new MappingException("Could not transform String to encoded representation ");
		}

		
	}

	
	
}
