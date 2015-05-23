package com.solveapuzzle.mapping.agnostic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MappingRepositoryHSQLInMemoryImpl implements MappingRepositoryInternal {

	
	@Autowired
	private MappingRecordDAO mappingRecordDAO;
	
	  @Transactional
	  public void insertMappingRecord(MappingRecord record) {
	    mappingRecordDAO.insertMappingRecord(record);
	  }
	
	
	@Transactional
	public String getMappingSource(String key) {
		
		MappingRecord record = mappingRecordDAO.getMappingRecordByKey(key);
		if (record == null) {throw new RuntimeException("Could not find record for key = " + key);		}
		
	   String content = record.getXmlContent();
		
	   if (content == null || content.isEmpty()) {throw new RuntimeException("Empty XSLT content for MappingRecord");}
	   
		return content;
	}

}
