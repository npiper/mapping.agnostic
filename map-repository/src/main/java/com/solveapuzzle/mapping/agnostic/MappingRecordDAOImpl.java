package com.solveapuzzle.mapping.agnostic;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MappingRecordDAOImpl implements MappingRecordDAO
{

	
	@Autowired
	  private SessionFactory sessionFactory;
	
		
	public void insertMappingRecord(MappingRecord record) {
		sessionFactory.getCurrentSession().save(record);
		sessionFactory.getCurrentSession().flush();

	}

	public MappingRecord getMappingRecordById(int recordId) {
		 return (MappingRecord) sessionFactory.
			      getCurrentSession().
			      get(MappingRecord.class, recordId);
	}

	public MappingRecord getMappingRecordByKey(String key) {
		Query query = sessionFactory.
			      getCurrentSession().
			      createQuery("from MappingRecord where key = :key");
			    query.setParameter("key", key);
			    return (MappingRecord) query.list().get(0);
	}
	
}
