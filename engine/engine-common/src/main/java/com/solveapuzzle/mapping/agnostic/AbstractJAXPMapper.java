package com.solveapuzzle.mapping.agnostic;

import java.io.OutputStream;
import java.io.StringReader;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractJAXPMapper implements
		Mapper<javax.xml.transform.Source, OutputStream> {

	@Autowired
	MappingRepository repository;

	private static final Logger logger = LoggerFactory.getLogger(AbstractJAXPMapper.class);

	
	public void setMappingRepository(MappingRepository repo) {
		this.repository = repo;
	}

	public void map(Source xmlSource, OutputStream resultStream,
			MappingConfiguration config) throws MappingException,
			ConfigurationException {
		// TODO Auto-generated method stub

		if (repository == null) {throw new RuntimeException("MappingRepository not injected.");}
		
		if (config == null) {throw new RuntimeException("Null configuration provided for mapping transformation 'MappingConfiguration config' parameter to method");}
		
		if (config.getMappingIdentifer() == null) {throw new RuntimeException("No mapping identifier provided in Config 'MappingConfiguration config' parameter, method getMappingIdentifier()");}
		
		if (xmlSource == null) {throw new RuntimeException("No XML Source input provided - parameter Source xmlSource");}
		
		String mapXSLT = repository.getMappingSource(config
				.getMappingIdentifer());
		
		
		if (mapXSLT == null || mapXSLT.isEmpty()) {
			throw new ConfigurationException(
					"Unable to find Mapping source in repository with identifier "
							+ config.getMappingIdentifer());
		}

		logger.info("Applying XSLT transform " + mapXSLT);
		
		// XSLT Source - apply the XSLT file
		javax.xml.transform.Source xsltSource = new javax.xml.transform.stream.StreamSource(
				new StringReader(mapXSLT));

		// Set up the Result
		javax.xml.transform.Result result = new javax.xml.transform.stream.StreamResult(
				resultStream);

		// create an instance of TransformerFactory
		// The underlying JAXP Implementation is attached
		javax.xml.transform.TransformerFactory transFact = createTransformerFactory();

		// Attempt the transformation & handle errors
		try {
			javax.xml.transform.Transformer trans = transFact
					.newTransformer(xsltSource);

			trans.transform(xmlSource, result);
		} catch (TransformerConfigurationException e) {
			throw new ConfigurationException(e.getMessage());
		} catch (TransformerException e) {
			throw new MappingException(e.getMessage());
		}

	}

	public abstract javax.xml.transform.TransformerFactory createTransformerFactory();

}
