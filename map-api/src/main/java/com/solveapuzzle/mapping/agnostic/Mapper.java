package com.solveapuzzle.mapping.agnostic;

import java.io.OutputStream;

public interface Mapper<A,B extends OutputStream> {

	public void map(A source, B resultStream, MappingConfiguration config)
			throws MappingException, ConfigurationException;

}
