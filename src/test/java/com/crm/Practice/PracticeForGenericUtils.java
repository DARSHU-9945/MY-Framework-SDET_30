package com.crm.Practice;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.javaUtility;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class PracticeForGenericUtils {
	
	@Test
	public void practice() throws Throwable {
		
		javaUtility jlib = new javaUtility();
		int ran= jlib.getRandomNumber();
		String dat= jlib.getSystemDateInFormat();
		String date= jlib.getSystemDate();
		System.out.println(ran + date);
		System.out.println(dat);
		
		PropertyFileUtility plib= new PropertyFileUtility();
		String brows = plib.readDataFromPropertyFile("browser");
		System.out.println(brows);
		
		
	}
	

}
