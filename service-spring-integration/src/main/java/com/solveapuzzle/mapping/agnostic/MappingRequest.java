package com.solveapuzzle.mapping.agnostic;

/** Interface to represent a Request to perform a mapping / transformation.
 * 
 * @author s28109
 *
 */
public interface MappingRequest {
	
	public MappingConfiguration getMappingConfiguration();
	
	public String getBody();

}
