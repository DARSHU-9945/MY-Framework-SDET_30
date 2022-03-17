package com.crm.ContactsTests;

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

import com.crm.GenericLibrary.BaseClass;
import com.crm.GenericLibrary.ExcellFileUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.GenericLibrary.javaUtility;
import com.crm.ObjectRepository.ContactInfoPage;
import com.crm.ObjectRepository.ContactsPage;
import com.crm.ObjectRepository.CreateContactPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class CreateContactWithUtilityPOM {
	@Test
	public void CreateContactWithUtilityTest() throws Throwable
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
		
		String lastName=elib.readDataFromExcel("Contact", 1, 2)+"_"+jlib.getRandomNumber();
		
		/*Step 2: launch the browser*/
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
		
		/*Step 4: Navigate to Contacts Link*/
		HomePage hp= new HomePage(driver);
		hp.ClickOnContactLink();
		
		
		/*Step 5: click on create contact btn*/
		ContactsPage cp= new ContactsPage(driver);
		cp.clickOnCreateContactImg();
		
		/*Step 6: enter mandatory fields and save*/
		CreateContactPage ccp= new CreateContactPage(driver);
		ccp.CreateNewContact(lastName);
		
		
		ContactInfoPage cip= new ContactInfoPage(driver);
		String actConName=cip.contactNameInfo();
		if(actConName.contains(lastName)) {
			System.out.println(actConName+"----->data verified");
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


