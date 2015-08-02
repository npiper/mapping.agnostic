package com.solveapuzzle.mapping.agnostic;

import org.springframework.integration.annotation.Gateway;

/** A Spring Integration Gateway for executing Mapping / Transformation Requests. 
 * 
 * @author s28109
 *
 */
public interface MappingGateway {

	
    @Gateway(replyChannel = "mappingReplies", requestChannel = "mappingRequests")
    public MappingResponse executeMapping(MappingRequest request) throws MappingException,ConfigurationException;
}
