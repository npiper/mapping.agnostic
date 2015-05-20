package com.solveapuzzle.mapping.agnostic;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class MappingRepositoryTest {
	
	
	@Autowired
	MappingRepositoryInternal mappingRepository;
	
	
	@Test
	public void testStartup()
	{

		
		Assert.assertNotNull(mappingRepository);

	}
	
	@Test
	public void testInsertion()
	{
		
		MappingRecord record = new MappingRecord();
		record.setKey("KEY1");
		record.setXmlContent("<xml/>");
		mappingRepository.insertMappingRecord(record);
		
		String xml = mappingRepository.getMappingSource("KEY1");
		
		
		
	}
	

}
