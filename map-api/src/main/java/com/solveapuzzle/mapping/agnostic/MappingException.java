package com.solveapuzzle.mapping.agnostic;

import java.util.Date;

public class MappingException extends ExpectedException {

	// What was the XML received as input
	private String mapInput;
	
	// XML applied as output
	private String mapTransformationSource;
	
	// The key lookup for the transformation
	private String mappingKey;
	
	// When it failed
	private Date timeOfTransformation;
	
	public MappingException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
	
}
