package com.solveapuzzle.mapping.agnostic;

public class MappingEngineImpl implements MappingEngine {

	MappingRepository repository;
	MapperFactory<String,String> factory;
	
	public void setMapperFactory(MapperFactory<String,String> factory)
	{
		this.factory = factory;
	}
	
	public void setMappingRepository(MappingRepository repository)
	{
		this.repository = repository;
	}
	
	public String onTransformEvent(MappingConfiguration config, String Body) throws MappingException, ConfigurationException {
		
		Mapper<String,String> mapper = factory.createMapper(config.getMappingType());
		
		java.lang.String mappingToApply = repository.getMappingSource(config.getMappingIdentifer());
		
		if (mappingToApply == null)
		{
			throw new UnexpectedException();
		}
		
		return mapper.map(Body,config);
		
		
	}

	
	
}
