package com.solveapuzzle.mapping.agnostic;

import java.io.StringReader;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

public class SaxonMapper implements
		Mapper<javax.xml.transform.Source, javax.xml.transform.Result> {

	MappingRepository repository;

	public void setMappingRepository(MappingRepository repo) {
		this.repository = repo;
	}

	public Result map(Source xmlSource, MappingConfiguration config)
			throws MappingException, ConfigurationException {
		// TODO Auto-generated method stub

		String mapXSLT = repository.getMappingSource(config
				.getMappingIdentifer());

		if (mapXSLT == null) {
			throw new ConfigurationException(
					"Unable to find Mapping source in repository with identifier "
							+ config.getMappingIdentifer());
		}

		// XSLT Source - apply the XSLT file
		javax.xml.transform.Source xsltSource = new javax.xml.transform.stream.StreamSource(
				new StringReader(mapXSLT));

		// Set up the Result
		javax.xml.transform.Result result = new javax.xml.transform.stream.StreamResult(
				System.out);

		// create an instance of TransformerFactory
		// The underlying JAXP Implementation is attached
		javax.xml.transform.TransformerFactory transFact = javax.xml.transform.TransformerFactory
				.newInstance();

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

		return result;
	}

}
