package com.solveapuzzle.mapping.agnostic;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import junit.framework.Assert;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class MappingServiceTest {

	private static final String TEST_TRANSFORM_VIA_SAXON = "testTransformViaSaxon";

	@Autowired
	MappingRepositoryInternal repository;

	@Autowired
	MappingService service;

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
	public void testMapping() throws IOException {

		// Load the XML
		InputStream inXML = this.getClass().getClassLoader()
				.getResourceAsStream("inXML.xml");
		String xml = IOUtils.toString(inXML);

		MappingConfiguration config = Mockito.mock(MappingConfiguration.class);
		Mockito.when(config.getCharacterEncoding()).thenReturn(
				Charset.defaultCharset());
		Mockito.when(config.getMappingIdentifer()).thenReturn(TEST_TRANSFORM_VIA_SAXON);
		Mockito.when(config.getMappingType()).thenReturn("xercesMapper");

		MappingRequest request = Mockito.mock(MappingRequest.class);
		Mockito.when(request.getMappingConfiguration()).thenReturn(config);
		Mockito.when(request.getBody()).thenReturn(xml);

		MappingResponse response = service.executeMapping(request);

		
		Assert.assertEquals("Transformation result incorrect.", "Yep, it worked!", response.getResult());
		
        System.out.println("Time " + response.getTransformationTimeMilliseconds());

	}

	@Test
	public void testMappingConfigFault() {
		;
	}

	@Test
	public void testMappingFault() {
		;
	}
}
