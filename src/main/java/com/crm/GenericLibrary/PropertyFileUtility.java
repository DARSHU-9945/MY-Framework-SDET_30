package com.crm.GenericLibrary;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * 
 * @author Deepthi
 *
 */

public class PropertyFileUtility {
	/**
	 * this method will read the data from property file for the key given by user and return value to user
	 * @param key
	 * @return
	 * @throws Throwable
	 */
	
	public String readDataFromPropertyFile(String key) throws Throwable 
	{
		
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties plib = new Properties();
		plib.load(fis);
		String value=plib.getProperty(key);
		return value;
	}

}
