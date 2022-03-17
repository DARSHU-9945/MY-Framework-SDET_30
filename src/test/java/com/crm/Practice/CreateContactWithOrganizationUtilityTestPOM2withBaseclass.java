package com.crm.Practice;

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
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.GenericLibrary.BaseClass;
import com.crm.GenericLibrary.ExcellFileUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.GenericLibrary.javaUtility;
import com.crm.ObjectRepository.ContactInfoPage;
import com.crm.ObjectRepository.ContactsPage;
import com.crm.ObjectRepository.CreateContactPage;
import com.crm.ObjectRepository.CreateOrganizationPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;
import com.crm.ObjectRepository.OrganizationInfoPage;
import com.crm.ObjectRepository.OrganizationsPage;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class CreateContactWithOrganizationUtilityTestPOM2withBaseclass extends BaseClass{
	//@Test(retryAnalyzer = com.crm.GenericLibrary.RetryAnalyserImplementation.class)
	@Test
	public void CreateContactWithUtilityTest() throws Throwable
	{
		
		String lastName=eLib.readDataFromExcel("Contact", 4, 2)+"_"+jLib.getRandomNumber();
		String OrgName=eLib.readDataFromExcel("Contact", 4, 3)+"_"+jLib.getRandomNumber();
		SoftAssert sAssert=new SoftAssert();
		
		/*Step 4: Navigate to Contacts Link*/
		HomePage hp= new HomePage(driver);
		hp.ClickOnOrgLink();
		
		String expdata= "Organizations";
		String actdata= driver.findElement(By.linkText("Organizations")).getText();
		Assert.assertEquals(actdata, expdata);
		
		//step-5 : click on create organization button
				OrganizationsPage op= new OrganizationsPage(driver);
				op.clickOnCreateOrgImg();
				
				String expheaderString= "Creating New Organizations";
				String actheaderString=driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
				sAssert.assertEquals( actheaderString,expheaderString);
				System.out.println("org creation passed");
				
		//step-6 : enter mandatory fields and save
				CreateOrganizationPage cop= new CreateOrganizationPage(driver);
				cop.CreateNewOrg(OrgName);
				
				
		//step-7 : verification
				OrganizationInfoPage oip= new OrganizationInfoPage(driver);
				String actOrgName=oip.OrgNameInfo();
				Reporter.log(actOrgName+" " +" org created",true);
				Assert.assertTrue(actOrgName.contains("OrgName"));
				System.out.println("info passed");
				sAssert.assertAll("All ok");
				
		/*Step 8: click on create contact btn*/
		hp.ClickOnContactLink();
		ContactsPage cp= new ContactsPage(driver);
		cp.clickOnCreateContactImg();
	
		
		/*Step 9: enter mandatory fields and save*/
		CreateContactPage ccp= new CreateContactPage(driver);
		ccp.CreateNewContact(driver, lastName, OrgName);
		
		
		// verification
		
		ContactInfoPage cip= new ContactInfoPage(driver);
		String actConName=cip.contactNameInfo();
		sAssert.assertTrue(actConName.contains(lastName));
		System.out.println("contact passed");
		
	}
}


