package com.solveapuzzle.mapping.agnostic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MappingRepositoryHSQLInMemoryImpl implements MappingRepositoryInternal {

	
	@Autowired
	private MappingRecordDAO mappingRecordDAO;
	
	  @Transactional
	  public void insertMappingRecord(MappingRecord record) {
	    mappingRecordDAO.insertMappingRecord(record);
	  }
	
	
	@Transactional
	public String getMappingSource(String key) {
		
		return mappingRecordDAO.getMappingRecordByKey(key).getXmlContent();
	}

}
