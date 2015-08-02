package com.solveapuzzle.mapping.agnostic;

import java.net.URL;

/** Interface to represent a result of a transformation from a mapping.
 * 
 * @author s28109
 *
 */
public interface MappingResponse {

	public String getResult();
	
	public URL getResultURL();
	
	public long getTransformationTimeMilliseconds();
	
}
