package com.crm.OrganizationTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.GenericLibrary.ExcellFileUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.GenericLibrary.javaUtility;
import com.crm.ObjectRepository.CreateOrganizationPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;
import com.crm.ObjectRepository.OrganizationInfoPage;
import com.crm.ObjectRepository.OrganizationsPage;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class CreateOrgwithPOMwithBaseClassregionalregression extends BaseClass {
	
	@Test(groups = "smokeSuite")
	public void CreateOrgWithutility() throws Throwable
	{
	
	String OrgName=eLib.readDataFromExcel("Org", 4, 2)+"_"+jLib.getRandomNumber();
		
		/*Step 4: Navigate to Organizations Link*/
		HomePage hp= new HomePage(driver);
		hp.ClickOnOrgLink();
		
		//step-5 : click on create organization button
		OrganizationsPage op= new OrganizationsPage(driver);
		op.clickOnCreateOrgImg();
		
		//step-6 : enter mandatory fields and save
		CreateOrganizationPage cop= new CreateOrganizationPage(driver);
		cop.CreateNewOrg(OrgName);
		
		//step-7 : verification
		OrganizationInfoPage oip= new OrganizationInfoPage(driver);
		String actOrgName=oip.OrgNameInfo();
		if(actOrgName.contains(OrgName)) {
			Reporter.log("======Created and verified succesfully: Only Org=======",true);
		}
		else
		{
			Reporter.log("====== not Created and not verified succesfully=======",true);
		}
		
	}
	
	@Test
	public void testScript1() 
	{
		System.out.println("this is test script-1");
	}
	
	@Test
	
	public void testScript2() 
	{
		System.out.println("this is test script-2");
	}
	
	@Test
	
	public void testScript3() 
	{
		System.out.println("this is test script-3");
	}

}

