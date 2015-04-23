package com.solveapuzzle.mapping.agnostic;

import java.io.OutputStream;

public interface MapperFactory<A,B extends OutputStream> {
	
	public Mapper<A,B> createMapper(String mappingEngineKey);
}
