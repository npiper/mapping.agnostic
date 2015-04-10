package com.solveapuzzle.mapping.agnostic;

public interface MapperFactory<A,B> {

	
	public Mapper<A,B> createMapper(String mappingEngineKey);
}
