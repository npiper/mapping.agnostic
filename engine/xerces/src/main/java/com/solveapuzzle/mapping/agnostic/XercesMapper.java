package com.solveapuzzle.mapping.agnostic;

import java.io.OutputStream;

import javax.xml.transform.TransformerFactory;

public class XercesMapper extends AbstractJAXPMapper implements
Mapper<javax.xml.transform.Source,OutputStream> {

	@Override
	public TransformerFactory createTransformerFactory() {
		// TODO Auto-generated method stub
		return TransformerFactory.newInstance("org.apache.xalan.processor.TransformerFactoryImpl",null);
	}

}
