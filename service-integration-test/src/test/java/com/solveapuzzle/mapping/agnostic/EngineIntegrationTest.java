package com.solveapuzzle.mapping.agnostic;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import static org.junit.Assert.assertEquals;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

public class EngineIntegrationTest {

	
	private static final String SAXON = "Saxon";
	private static final String TEST_TRANSFORM_VIA_SAXON = "testTransformViaSaxon";

	MappingEngineImpl meng;
	MappingConfiguration mconfig;
	MapperFactory mfact;
	MappingRepository mrepo;
	
	@Before
	public void setUp()
	{
		mrepo = createMappingRepository();
		mfact = createMapperFactory(mrepo);
		
		mconfig = createMappingConfig();
		
		meng = new MappingEngineImpl();
		meng.setMapperFactory(mfact);
		meng.setMappingRepository(mrepo);
		
	}

	@Test
	public void testSaxonTransformation() throws MappingException, ConfigurationException, IOException
	{
		// Load the XML
		InputStream inXML = this.getClass().getClassLoader()
				.getResourceAsStream("inXML.xml");
        String xml = IOUtils.toString(inXML);
		
		String response = meng.onTransformEvent(mconfig, xml, Charset.defaultCharset() );
		
		
		assertEquals("Yep, it worked!", response);
		
	}
	
	private MappingConfiguration createMappingConfig() {
		// TODO Auto-generated method stub
		return new MappingConfiguration() {
			
			public String getMappingType() {
				// TODO Auto-generated method stub
				return SAXON;
			}
			
			public String getMappingIdentifer() {
				// TODO Auto-generated method stub
				return TEST_TRANSFORM_VIA_SAXON;
			}

			@Override
			public Charset getCharacterEncoding() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Priority getPriority() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

	private MappingRepository createMappingRepository() {
		// TODO Auto-generated method stub
		return new MappingRepository(){

			public String getMappingSource(String key) {

                if (key.equals(TEST_TRANSFORM_VIA_SAXON))
                {
            		InputStream is = this.getClass().getClassLoader().getResourceAsStream("Stylesheet.xslt");
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
			
			
		};
	}

	private MapperFactory createMapperFactory(final MappingRepository mrepo) {
		
		return new MapperFactory<javax.xml.transform.Source, OutputStream>() {

			public Mapper<javax.xml.transform.Source, OutputStream> createMapper(String mappingEngineKey) {

				// Right now it's just Saxon
				SaxonMapper sm = new SaxonMapper();
				sm.setMappingRepository(mrepo);
				return sm;
			}
			
			
		};
	}
	
}
