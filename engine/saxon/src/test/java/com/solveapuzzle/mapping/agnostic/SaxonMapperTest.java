package com.solveapuzzle.mapping.agnostic;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.io.StringReader;

import javax.xml.transform.Result;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.io.IOUtil;

public class SaxonMapperTest {

	SaxonMapper mapper = new SaxonMapper();

	javax.xml.transform.Source xmlSource;

	@Before
	public void setUp() throws IOException {

		MappingRepository repo = Mockito.mock(MappingRepository.class);

		// Set up XSLT Source
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("Stylesheet.xslt");
		
		String xslt = IOUtils.toString(is);
		
		Mockito.when(repo.getMappingSource(Mockito.eq("KEY"))).thenReturn(xslt);

		mapper.setMappingRepository(repo);

		// Set the SAXON XSLT as the processor
		System.setProperty("javax.xml.transform.TransformerFactory", "net.sf.saxon.TransformerFactoryImpl");

		InputStream inXML = this.getClass().getClassLoader()
				.getResourceAsStream("inXML.xml");

		xmlSource = new javax.xml.transform.stream.StreamSource(inXML);

	}

	@Test
	public void testTransformation() throws MappingException,
			ConfigurationException {

		// Incoming Mapping config
		MappingConfiguration config = Mockito.mock(MappingConfiguration.class);
		Mockito.when(config.getMappingIdentifer()).thenReturn("KEY");

		Result result = mapper.map(this.xmlSource, config);

		result.toString();
	}

}
