package com.crm.Practice;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class ReadDataFromXMlfileTest {
	
	@Test
	
	public void readDataFromXML(XmlTest xml)
	{
		
		String Browser=xml.getParameter("browser");
		String URL= xml.getParameter("url");
		
		System.out.println(Browser);
		
		System.out.println(URL);
		
	}

}
