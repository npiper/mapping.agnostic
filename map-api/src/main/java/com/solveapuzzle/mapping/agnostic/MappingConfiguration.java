package com.solveapuzzle.mapping.agnostic;

import java.nio.charset.Charset;


public interface MappingConfiguration {

    public enum Priority {
        low,
        medium,
        high,
        emergency;
    }
    

    public Priority getPriority();
    
	public String getMappingIdentifer();
	
	public String getMappingType();
	
	
	/** Get's the character encoding to apply to the transformation result, defaults to UTF-8.
	 * 
	 * @return
	 */
	public Charset getCharacterEncoding();
}
