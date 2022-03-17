package com.crm.Practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class DataProviderConcept {
	
	@Test(dataProvider = "getdata")
	
	public void sampleDataProvider(String Name, String model, int qty) 
	{
		System.out.println(Name+"--------"+ model+"-----------"+qty);
	}
	
	@DataProvider
	public Object[][] getdata()
	
	{
		
		Object[][] obj = new Object[4][3];
		
		obj[0][0]="Mi";
		obj[0][1]="pro max";
		obj[0][2]=25;
		
		obj[1][0]="oppo";
		obj[1][1]="min-25";
		obj[1][2]=30;
		
		obj[2][0]="samsung";
		obj[2][1]="galaxy";
		obj[2][2]=50;
		
		obj[3][0]="vivo";
		obj[3][1]="vivo jing";
		obj[3][2]=20;
		
		return obj;
	}

}
