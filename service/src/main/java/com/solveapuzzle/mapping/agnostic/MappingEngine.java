package com.solveapuzzle.mapping.agnostic;

import java.nio.charset.Charset;

public interface MappingEngine {
	
	
	public String onTransformEvent(MappingConfiguration config, String Body, Charset encoding) throws MappingException, ConfigurationException;

}
