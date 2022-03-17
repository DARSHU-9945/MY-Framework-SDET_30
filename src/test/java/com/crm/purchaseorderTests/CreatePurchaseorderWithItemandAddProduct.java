package com.crm.purchaseorderTests;

import java.io.FileInputStream;
import java.sql.Driver;
import java.time.Duration;
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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcellFileUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.GenericLibrary.javaUtility;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class CreatePurchaseorderWithItemandAddProduct {
	
	@Test
	
	public void CreatePurchaseorderWithItemandAddProduct() throws Throwable
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
		
		String subject= elib.readDataFromExcel("PurchaseOrder", 1, 1);
		String BillingAdress= elib.readDataFromExcel("PurchaseOrder", 1, 5);
		String ShippingAdress= elib.readDataFromExcel("PurchaseOrder", 1, 6);
		String item= elib.readDataFromExcel("PurchaseOrder", 1, 7);
		String VendorName= elib.readDataFromExcel("PurchaseOrder", 3, 2);
		String prdName= elib.readDataFromExcel("PurchaseOrder", 7, 3);		
				
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
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();
				
			/*Step 4: navigating to More link*/
				
				WebElement mouse = driver.findElement(By.linkText("More"));
				
				wlib.mouseHover(driver, mouse);
				
			//*Step 5: Clicking on purchase order link and Create purchase Order*//
				
				driver.findElement(By.linkText("Purchase Order")).click();
				
				driver.findElement(By.xpath("//img[@title='Create Purchase Order...']")).click();
				
				driver.findElement(By.name("subject")).sendKeys(subject);
				
				//Selecting Vendor Name
				
				driver.findElement(By.xpath("//input[@name='vendor_name']/following-sibling::img[@title='Select']")).click();
				
				wlib.switchToWindow(driver, "Vendors");
				
			//*Step 6: searching the vendor from the vendor list
				
				driver.findElement(By.name("search_text")).sendKeys(VendorName);
				
				driver.findElement(By.name("search")).click();
				
				driver.findElement(By.xpath("//a[text()='"+VendorName+"']")).click();
				
				// switching back to main window//
								
				
				wlib.switchToWindow(driver, "PurchaseOrder");
				
				
				// Reading Billing and Shipping Address from excell sheet//
				
				driver.findElement(By.name("bill_street")).sendKeys(BillingAdress);
				
				driver.findElement(By.name("ship_street")).sendKeys(ShippingAdress);
				
				
			//*Step 7: creating new item//
			
				driver.findElement(By.id("searchIcon1")).click();
				
				
				wlib.switchToWindow(driver, "Products");
				
			//*Step 8: searching the item from the item list
				
				driver.findElement(By.name("search_text")).sendKeys(item);
				
				driver.findElement(By.name("search")).click();
				
				driver.findElement(By.xpath("//a[text()='"+item+"']")).click();
				
				
				wlib.switchToWindow(driver, "PurchaseOrder");
				
				//Thread.sleep(5000);
				
				driver.findElement(By.xpath("//input[@value='Add Product']")).click();
								
				driver.findElement(By.xpath("//input[@id='productName2']/following-sibling::img[@title='Products']")).click();
				
				wlib.switchToWindow(driver, "Products");
				
				driver.findElement(By.id("search_txt")).sendKeys(item);
				
				driver.findElement(By.name("search")).click();
				
				driver.findElement(By.xpath("//a[text()='"+item+"']")).click();
				
				
				wlib.switchToWindow(driver, "PurchaseOrder");
						
				}
			
}
