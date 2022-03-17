package com.crm.CreatePurchaseOrderWithVendorTestusingUTILITY;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcellFileUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.GenericLibrary.javaUtility;

import io.github.bonigarcia.wdm.WebDriverManager;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class CreatePurchaseOrderWithVendorName1Test {
	
	@Test
	public void CreatePurchaseOrderWithVendorName1Test() throws Throwable
	{
		WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
		
		/*read data*/
		PropertyFileUtility pLib = new PropertyFileUtility();
		javaUtility jLib = new javaUtility();
		ExcellFileUtility eLib = new ExcellFileUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		
		/*Step 1: read all neccessary data*/
		String BROWSER = pLib.readDataFromPropertyFile("browser");
		String URL = pLib.readDataFromPropertyFile("url");
		String USERNAME = pLib.readDataFromPropertyFile("username");
		String PASSWORD = pLib.readDataFromPropertyFile("password");
		
		String SubjectName = eLib.readDataFromExcel("PurchaseOrder", 1, 4)+"_"+jLib.getRandomNumber();
		String BillingAdress = eLib.readDataFromExcel("PurchaseOrder", 1, 5)+"_"+jLib.getRandomNumber();
		String ShippingAdress = eLib.readDataFromExcel("PurchaseOrder", 1, 6)+"_"+jLib.getRandomNumber();
		String item = eLib.readDataFromExcel("PurchaseOrder", 1, 7);
		String VendorName = eLib.readDataFromExcel("PurchaseOrder", 3, 2);
	
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
			System.out.println("invalid browser name");
		}
		
		wLib.maximizeWindow(driver);
		wLib.waitForPageLoad(driver);
		driver.get(URL);
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		/*Step 4: navigating to More link*/
		
		WebElement mouse = driver.findElement(By.linkText("More"));
		
		wLib.mouseHover(driver, mouse);
		
	//*Step 5: Clicking on purchase order link and Create purchase Order*//
		
		driver.findElement(By.linkText("Purchase Order")).click();
		
		driver.findElement(By.xpath("//img[@title='Create Purchase Order...']")).click();
		
		driver.findElement(By.name("subject")).sendKeys(SubjectName);
		
		//Selecting Vendor Name
		
		driver.findElement(By.xpath("//input[@name='vendor_name']/following-sibling::img[@title='Select']")).click();
		
		wLib.switchToWindow(driver, "Vendors");
		
	//*Step 6: searching the vendor from the vendor list
		
		driver.findElement(By.name("search_text")).sendKeys(VendorName);
		
		driver.findElement(By.name("search")).click();
		
		driver.findElement(By.xpath("//a[text()='"+VendorName+"']")).click();
		
		// switching back to main window//
						
		
		wLib.switchToWindow(driver, "PurchaseOrder");
		
		
		// Reading Billing and Shipping Address from excell sheet//
		
		driver.findElement(By.name("bill_street")).sendKeys(BillingAdress);
		
		driver.findElement(By.name("ship_street")).sendKeys(ShippingAdress);
		
		driver.findElement(By.id("searchIcon1")).click();
		
		
		wLib.switchToWindow(driver, "Products");
		
		
		// Selecting the Item from the List//
		
		driver.findElement(By.linkText(item)).click();
		
		
		
		wLib.switchToWindow(driver, "PurchaseOrder");
		
		
		//selecting the number of quantity for Item//
		
		driver.findElement(By.id("qty1")).sendKeys("1");
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
	/*Step 7:** Signout of the application**/
		
		WebElement element = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wLib.mouseHover(driver, element);
		
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
		
	}
}
	
	