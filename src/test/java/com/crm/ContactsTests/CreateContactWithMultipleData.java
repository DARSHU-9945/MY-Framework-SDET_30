package com.crm.ContactsTests;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
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
import com.crm.ObjectRepository.ContactInfoPage;
import com.crm.ObjectRepository.ContactsPage;
import com.crm.ObjectRepository.CreateContactPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class CreateContactWithMultipleData {
	
	PropertyFileUtility plib = new PropertyFileUtility();
	javaUtility jlib = new javaUtility();
	ExcellFileUtility elib = new ExcellFileUtility();
	WebDriverUtility wlib = new WebDriverUtility();

	
	@Test(dataProvider = "ContactData")
	
	public void CreateContactWithMultipleData(String Contactname) throws Throwable
	{
		/*Step :1 read all neccessary data*/
		String BROWSER= plib.readDataFromPropertyFile("browser");
		String URL= plib.readDataFromPropertyFile("url");
		String USERNAME= plib.readDataFromPropertyFile("username");
		String PASSWORD= plib.readDataFromPropertyFile("password");
		
		String RanContactname= Contactname+jlib.getRandomNumber();
		
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
		Reporter.log("login successful",true);
		
		/*Step 4: Navigate to Contacts Link*/
		HomePage hp= new HomePage(driver);
		hp.ClickOnContactLink();
		Reporter.log("navigated to contact link",true);
		
		/*Step 5: click on create contact btn*/
		ContactsPage cp= new ContactsPage(driver);
		cp.clickOnCreateContactImg();
		Reporter.log("click on create contact link",true);
		
		/*Step 6: enter mandatory fields and save*/
		CreateContactPage ccp= new CreateContactPage(driver);
		ccp.CreateNewContact(RanContactname);
		
		
		ContactInfoPage cip= new ContactInfoPage(driver);
		String actConName=cip.contactNameInfo();
		if(actConName.contains(RanContactname)) {
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
	
	@DataProvider(name="ContactData")
	
	public Object getData() throws Throwable
	{
		Object data = elib.readmultipleDataFromExcel("ContactMultipleData", 0);
		return data;
	}
	
		
	}