package com.crm.OrganizationTests;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class createorganizationtestwithpropertyfiletest {
	
	@Test
	
	public void propertyFile() throws Throwable
	{
		//Step 1: read the file
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\propertyfile.properties");
		
		//Step 2: Create Obj of Properties
		Properties pObj = new Properties();
		
		pObj.load(fis);
		
		//Step 3:read the data
		String URL = pObj.getProperty("username");
		
		//Verification
		System.out.println(URL);
    
	}

}
