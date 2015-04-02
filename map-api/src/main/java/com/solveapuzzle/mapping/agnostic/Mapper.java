package com.solveapuzzle.mapping.agnostic;

public interface Mapper<A, B> {

	public B map(A source, MappingConfiguration config)
			throws MappingException, ConfigurationException;

}
