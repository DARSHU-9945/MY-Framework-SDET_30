package com.crm.Practice;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class PracticeTestNg {
	
	@Test(invocationCount = 4)
	
	public void CreateOrg() {
		System.out.println("Organization Created");
		Assert.fail();
		
	}
	
	@Test(priority = 1)
	public void modifyOrg() {
		System.out.println("Organization is modified");

}
	
	@Test
	public void deleteOrg() {
		System.out.println("Organization is deleted");
	}
}
