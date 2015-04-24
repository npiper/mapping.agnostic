package com.solveapuzzle.mapping.agnostic;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class TestVtdMapper {

	@Test
	public void testVtdMapper() throws MappingException, ConfigurationException, UnsupportedEncodingException
	{
		VtdMapper mapper = new VtdMapper();
		
		// Set up XSLT Source
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("vtdSoapSample.xml");
		
        java.io.ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		
		mapper.map(is, bos, null);
		
		String ss = bos.toString("UTF-8");
		
		System.out.println(ss);
		
	}
	
}
