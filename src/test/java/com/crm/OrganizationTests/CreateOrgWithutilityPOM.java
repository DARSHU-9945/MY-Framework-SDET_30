package com.crm.OrganizationTests;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

import io.github.bonigarcia.wdm.WebDriverManager;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class CreateOrgWithutilityPOM {
	
	@Test
	public void CreateOrgWithutility() throws Throwable
	{
	
	PropertyFileUtility plib = new PropertyFileUtility();
	javaUtility jlib = new javaUtility();
	ExcellFileUtility elib = new ExcellFileUtility();
	WebDriverUtility wlib = new WebDriverUtility();
	
	/*Step :1 read all neccessary data*/
	String BROWSER= plib.readDataFromPropertyFile("browser");
	String URL= plib.readDataFromPropertyFile("url");
	String USERNAME= plib.readDataFromPropertyFile("username");
	String PASSWORD= plib.readDataFromPropertyFile("password");
	
	String OrgName=elib.readDataFromExcel("Org", 4, 2)+"_"+jlib.getRandomNumber();
	
		/*Step 2: launch the browser*/
	WebDriverManager.firefoxdriver().setup();
	WebDriverManager.chromedriver().setup();
	
		WebDriver driver=null;
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else
		{
			System.out.println("invalid browser");
		}
		
		wlib.maximizeWindow(driver);
		wlib.waitForPageLoad(driver);
		driver.get(URL);
		
		
		/*Step 3: login to application*/
		LoginPage lp= new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		
		/*Step 4: Navigate to Organizations Link*/
		HomePage hp= new HomePage(driver);
		hp.ClickOnOrgLink();
		
		/*Step 5: click on create org swanization btn*/
		OrganizationsPage op= new OrganizationsPage(driver);
		op.clickOnCreateOrgImg();
		
		/*Step 6: enter mandatory fields and save*/
		CreateOrganizationPage cop= new CreateOrganizationPage(driver);
		cop.CreateNewOrg(OrgName);
		
		//verification
		OrganizationInfoPage oip= new OrganizationInfoPage(driver);
		String actOrgName=oip.OrgNameInfo();
		if(actOrgName.contains(OrgName)) {
			
			System.out.println(actOrgName+"----->data verified");
		}
		else
		{
			System.out.println("data invalid");
		}
		
		
		/*Step 7: logout of application*/
		hp.signOutOfApp(driver);
		
		/*Step 8: close the browser*/
		driver.quit();
	}

}
