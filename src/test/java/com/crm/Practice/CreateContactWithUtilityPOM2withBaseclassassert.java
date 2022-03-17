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
public class CreateContactWithUtilityPOM2withBaseclassassert extends BaseClass {
	@Test(groups = "smokeSuite")
	public void CreateContactWithUtilityTest() throws Throwable
	{
		
		String lastName=eLib.readDataFromExcel("Contact", 1, 2)+"_"+jLib.getRandomNumber();
		
		/*Step 4: Navigate to Contacts Link*/
		HomePage hp= new HomePage(driver);
		hp.ClickOnContactLink();
		
		
		/*Step 5: click on create contact btn*/
		ContactsPage cp= new ContactsPage(driver);
		cp.clickOnCreateContactImg();
		Assert.fail();
		
		/*Step 6: enter mandatory fields and save*/
		CreateContactPage ccp= new CreateContactPage(driver);
		ccp.CreateNewContact(lastName);
		
		
		ContactInfoPage cip= new ContactInfoPage(driver);
		String actConName=cip.contactNameInfo();
		if(actConName.contains(lastName)) {
			System.out.println(actConName+"----->data verified=== Only Contact created");
		}
		else
		{
			System.out.println("data invalid");
		}
		
	}
}


