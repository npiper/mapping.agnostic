package com.solveapuzzle.mapping.agnostic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.nio.charset.Charset;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SpringServiceIntegrationTest {

	@Autowired
	private MappingEngine mappingEngine;
	
	String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
"<message>Yep, it worked!</message>";
	
	@Test
	public void testOne() throws MappingException, ConfigurationException
	{
		MappingConfiguration config = new MappingConfiguration() {
			
			public String getMappingType() {
				// TODO Auto-generated method stub
				return "saxonMapper";
			}
			
			public String getMappingIdentifer() {
				// TODO Auto-generated method stub
				return "test";
			}
		};
		
		String output = mappingEngine.onTransformEvent(config, xml, Charset.defaultCharset());
		
		Assert.assertFalse(output.isEmpty());
		Assert.assertEquals("Yep, it worked!", output);
	}
	
}
