package com.crm.OrganizationTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

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
public class CreateOrgWithMultipleDataWithTypeTest {
	
	//Create Obj for all utilities
			PropertyFileUtility pLib = new PropertyFileUtility();
			ExcellFileUtility eLib = new ExcellFileUtility();
			WebDriverUtility wLib = new WebDriverUtility();
			javaUtility jLib = new javaUtility();
			
			@Test(dataProvider = "OrgtestData")
			public void createOrgWithMltipleData(String orgName, String indType, String Type) throws Throwable
			{
				
				
				//read data 
				String BROWSER = pLib.readDataFromPropertyFile("browser");
				String URL = pLib.readDataFromPropertyFile("url");
				String USERNAME = pLib.readDataFromPropertyFile("username");
				String PASSWORD = pLib.readDataFromPropertyFile("password");
				
				String Ranorgname = orgName+jLib.getRandomNumber();
			
				//launch the application
				WebDriver driver = null;
				if(BROWSER.equalsIgnoreCase("chrome"))
				{
					driver = new ChromeDriver();
				}
				else if(BROWSER.equalsIgnoreCase("FIREFOX"))
				{
					driver = new FirefoxDriver();
				}
				else
				{
					System.out.println("invalid browser");
				}
				
				wLib.maximizeWindow(driver);
				wLib.waitForPageLoad(driver);
				driver.get(URL);
				
				//login to application
				LoginPage lp = new LoginPage(driver);
				lp.loginToApp(USERNAME, PASSWORD);
				Reporter.log("login successful",true);
				
				//navigate to organization
				HomePage hp = new HomePage(driver);
				hp.ClickOnOrgLink();
				Reporter.log("navigated to org link",true);
				
				//create Org
				OrganizationsPage op = new OrganizationsPage(driver);
				op.clickOnCreateOrgImg();
				Reporter.log("click on create org link",true);
				
				//create new org
				CreateOrganizationPage cop = new CreateOrganizationPage(driver);
				cop.CreateNewOrg(Ranorgname, indType, Type);
				Reporter.log("create org with insustry type",true);
				
				//validate
				OrganizationInfoPage oip = new OrganizationInfoPage(driver);
				String actHeader = oip.OrgNameInfo();
				if (actHeader.contains(Ranorgname)) {
					System.out.println("passed");
				}
				else
				{
					System.out.println("failed");
				}
				Reporter.log("verification successful",true);		
				
				//logout
				hp.signOutOfApp(driver);
				
				driver.quit();
			}
			
			@DataProvider(name = "OrgtestData")
			public Object[][] getData() throws Throwable
			{
				Object[][] data = eLib.readmultipleDataFromExcel("OrganizationMultipleData", 0);
				return data;
			}

	}

