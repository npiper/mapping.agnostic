package com.solveapuzzle.mapping.agnostic;

public interface MappingRecordDAO {
	
	  void insertMappingRecord(MappingRecord record);
	  
	  MappingRecord getMappingRecordById(int userId);
	  
	  MappingRecord getMappingRecordByKey(String key);


}
