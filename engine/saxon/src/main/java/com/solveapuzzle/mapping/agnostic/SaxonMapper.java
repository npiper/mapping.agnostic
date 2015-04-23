package com.solveapuzzle.mapping.agnostic;

import java.io.OutputStream;
import java.io.StringReader;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;



public class SaxonMapper extends AbstractJAXPMapper implements
		Mapper<javax.xml.transform.Source,OutputStream> {

	@Override
	public TransformerFactory createTransformerFactory() {
		// TODO Auto-generated method stub
		return TransformerFactory.newInstance("net.sf.saxon.TransformerFactoryImpl",null);
	}

	

}
