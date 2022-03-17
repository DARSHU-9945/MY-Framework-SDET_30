package com.crm.Practice;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PracticeRetryAnalyser {
	
	@Test(retryAnalyzer = com.crm.GenericLibrary.RetryAnalyserImplementation.class)
	
	public void PracticeRetry() 
	{
		System.out.println(" this is test-1");
		Assert.fail();
		System.out.println(" this is passed");
	}

}
