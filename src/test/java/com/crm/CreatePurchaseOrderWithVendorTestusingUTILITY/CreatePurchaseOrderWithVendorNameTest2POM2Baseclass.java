package com.crm.CreatePurchaseOrderWithVendorTestusingUTILITY;

import java.util.Properties;
import java.util.Random;
import java.util.Set;
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
import com.crm.ObjectRepository.CreatePurchaseOrderPage;
import com.crm.ObjectRepository.CreateVendorPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;
import com.crm.ObjectRepository.ProductInfoPage;
import com.crm.ObjectRepository.PurchaseOrderInfoPage;
import com.crm.ObjectRepository.PurchaseOrderPage;
import com.crm.ObjectRepository.VendorInfoPage;
import com.crm.ObjectRepository.VendorPage;

import io.github.bonigarcia.wdm.WebDriverManager;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class CreatePurchaseOrderWithVendorNameTest2POM2Baseclass extends BaseClass{
	
	@Test
	public void CreatePurchaseOrderWithVendorNameTest() throws Throwable
	{
		
		String SubjectName = eLib.readDataFromExcel("PurchaseOrder", 1, 4)+"_"+jLib.getRandomNumber();
		String ContactName = eLib.readDataFromExcel("PurchaseOrder", 1, 9)+"_"+jLib.getRandomNumber();
		String VendorName = eLib.readDataFromExcel("PurchaseOrder", 3, 2)+"_"+jLib.getRandomNumber();
		String BillingAdress = eLib.readDataFromExcel("PurchaseOrder", 1, 5)+"_"+jLib.getRandomNumber();
		String ShippingAdress = eLib.readDataFromExcel("PurchaseOrder", 1, 6)+"_"+jLib.getRandomNumber();
		String item = eLib.readDataFromExcel("PurchaseOrder", 1, 7);
		String quantity = eLib.readNumericDataFromExcel("PurchaseOrder", 1, 8);
	
		/*Step 4: navigating to More link*/
				
		HomePage hp= new HomePage(driver);
		hp.ClickOnMOreLink();
		
		hp.VendorLinkText();
		
		VendorPage vPage= new VendorPage(driver);
		vPage.clickOnCreateVendorLookUpImg();
		
		CreateVendorPage cvp = new CreateVendorPage(driver);
		cvp.CreateVendor(driver, VendorName);
		
		VendorInfoPage vip= new VendorInfoPage(driver);
		String ActHeader = vip.VendorHeaderInfo();
		if(ActHeader.contains(VendorName)) {
			System.out.println(ActHeader+"----->data verified");
		}
		else
		{
			System.out.println("data invalid");
		}
		
		hp.ClickOnContactLink();
		
		
		/*Step 5: click on create contact btn*/
		ContactsPage cp= new ContactsPage(driver);
		cp.clickOnCreateContactImg();
		
		/*Step 6: enter mandatory fields and save*/
		CreateContactPage ccp= new CreateContactPage(driver);
		ccp.CreateNewContact(ContactName);
		
		
		ContactInfoPage cip= new ContactInfoPage(driver);
		String actConName=cip.contactNameInfo();
		if(actConName.contains(ContactName)) {
			System.out.println(actConName+"----->data verified");
		}
		else
		{
			System.out.println("data invalid");
		}
		
		hp.ClickOnMOreLink();
		
		hp.ClickPurchaseorderlinktext();
				
		//*Step 5: Clicking on purchase order link and Create purchase Order*//
		
		PurchaseOrderPage pop=new PurchaseOrderPage(driver);
		pop.ClickPurchasePlussbutton();
		
		CreatePurchaseOrderPage cpop= new CreatePurchaseOrderPage(driver);
		cpop.CreatePurchaseorder(driver, SubjectName, VendorName,  BillingAdress, ShippingAdress, item, quantity);
		
		PurchaseOrderInfoPage poip= new PurchaseOrderInfoPage(driver);
		String ActPOHeader=poip.PurchaseOrderTitle();
		if(ActPOHeader.contains(SubjectName))
		{
			System.out.println("Purchase Order is created");
		}
		else
		{
			System.out.println("Purchase Order not created");
		}
		
     }
	
}
