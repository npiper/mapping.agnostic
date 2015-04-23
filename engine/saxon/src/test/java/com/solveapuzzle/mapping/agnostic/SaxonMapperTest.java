package com.solveapuzzle.mapping.agnostic;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

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
			ConfigurationException, UnsupportedEncodingException {

		// Incoming Mapping config
		MappingConfiguration config = Mockito.mock(MappingConfiguration.class);
		Mockito.when(config.getMappingIdentifer()).thenReturn("KEY");
  
ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		Writer out
		   = new BufferedWriter(new OutputStreamWriter(bos));
		
		
		mapper.map(this.xmlSource, bos, config);
		
		String result = new String(bos.toByteArray(),"UTF-8");

		System.out.println(result);
	}

}
