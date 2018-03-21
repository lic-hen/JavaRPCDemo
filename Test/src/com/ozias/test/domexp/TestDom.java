package com.ozias.test.domexp;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class TestDom {

	public static void main(String[] args) {
		DocumentBuilderFactory docBuilder = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = docBuilder.newDocumentBuilder();
//			builder.parse();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
