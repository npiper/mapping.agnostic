package com.solveapuzzle.mapping.agnostic;

/** Interface to represent a Repository of mapping transformations and 
 * methods to interact with them.
 * 
 * @author neilpiper
 *
 */
public interface MappingRepository {

	/** Returns a mapping instruction
	 * 
	 * @param key
	 * @return
	 */
	public String getMappingSource(String key);
	
}
