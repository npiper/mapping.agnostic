package com.solveapuzzle.mapping.agnostic;

public interface MappingEngine {
	
	
	public String onTransformEvent(MappingConfiguration config, String Body) throws MappingException, ConfigurationException;

}
