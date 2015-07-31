package com.solveapuzzle.mapping.agnostic;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class MappingServiceTest {

	private static final String TEST_TRANSFORM_VIA_SAXON = "testTransformViaSaxon";

	@Autowired
	MappingRepositoryInternal repository;

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
	public void testMapping() {

		MappingService service = new MappingService();

		MappingRequest request = null;

		MappingResponse response = service.executeMapping(request);

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
