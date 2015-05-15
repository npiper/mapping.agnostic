package com.solveapuzzle.mapping.agnostic;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class MappingRepositoryTestImpl implements MappingRepository {

	public String getMappingSource(String key) {

        if (key.equals("test"))
        {
    		InputStream is = this.getClass().getClassLoader().getResourceAsStream("testxslt/Stylesheet.xslt");
    		String xslt;
			try {
				xslt = IOUtils.toString(is);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
    		return xslt;
        }
		
		throw new RuntimeException("No mapping source found for supplied key "+ key);
	}
}
