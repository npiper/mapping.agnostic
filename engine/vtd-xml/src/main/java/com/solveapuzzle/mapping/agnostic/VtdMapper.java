package com.solveapuzzle.mapping.agnostic;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import com.ximpleware.AutoPilot;
import com.ximpleware.EOFException;
import com.ximpleware.EncodingException;
import com.ximpleware.EntityException;
import com.ximpleware.NavException;
import com.ximpleware.ParseException;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import com.ximpleware.XPathEvalException;
import com.ximpleware.XPathParseException;

public class VtdMapper implements Mapper<InputStream, OutputStream> {

	public void map(InputStream source, OutputStream resultStream,
			MappingConfiguration config) throws MappingException,
			ConfigurationException {
		// TODO Auto-generated method stub

		BufferedInputStream bis = new BufferedInputStream(source);
		byte[] b = new byte[65536];
		try {
			int k = bis.read(b);
			
			AutoPilot ap = new AutoPilot();
			// instantiate the parser
			VTDGen vg = new VTDGen();
			vg.setDoc(b,0,k);
			vg.parse(true);  // set namespace awareness to true 
			VTDNav vn = vg.getNav();
			ap.bind(vn);
			ap.declareXPathNameSpace("ns1","http://www.w3.org/2003/05/soap-envelope");
			// get to the SOAP header
			ap.selectXPath("/ns1:Envelope/ns1:Header/*[@ns1:mustUnderstand]");
			System.out.println("expr string is " + ap.getExprString());
			while(ap.evalXPath()!= -1){
				long l = vn.getElementFragment();
				int len = (int) (l>>32);
				int offset = (int) l;
				resultStream.write(b, offset, len); //write the fragment out into out.txt
				resultStream.write("\n=========\n".getBytes());
			}

			resultStream.close();
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (EncodingException e) {
			throw new RuntimeException(e);
		} catch (EOFException e) {
			throw new RuntimeException(e);
		} catch (EntityException e) {
			throw new RuntimeException(e);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		} catch (XPathParseException e) {
			throw new RuntimeException(e);
		} catch (XPathEvalException e) {
			throw new RuntimeException(e);
		} catch (NavException e) {
			throw new RuntimeException(e);
		}

	}

}
