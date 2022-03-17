package com.crm.Practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class Dataprovider1 {
	
@Test(dataProvider = "getdata")
	
	public void sampleDataProvider(String Name, int qty) 
	{
		System.out.println(Name+"--------"+ qty);
	}
	
	@DataProvider
	public Object[][] getdata()
	
	{
		
		Object[][] obj = new Object[6][2];
		
		obj[0][0]="Mi";
		obj[0][1]=25;
		
		obj[1][0]="oppo";
		obj[1][1]=30;
		
		obj[2][0]="samsung";
		obj[2][1]=50;
		
		obj[3][0]="vivo";
		obj[3][1]=20;
		
		obj[4][0]="poco";
		obj[4][1]=80;
		
		obj[5][0]="realme";
		obj[5][1]=70;
		
		return obj;
	}


}
