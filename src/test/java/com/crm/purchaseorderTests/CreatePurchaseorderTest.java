package com.crm.purchaseorderTests;

import java.io.FileInputStream;
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
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class CreatePurchaseorderTest{
	
	@Test
	public void CreatePurchaseorderTest() throws Throwable
	{
		/*generate random number*/
		Random ran = new Random();
		int random = ran.nextInt(500);
		
		/*Step 1: read all neccessary data*/
		//read data from property file
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String BROWSER = pObj.getProperty("browser");
		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("username");
		String PASSWORD = pObj.getProperty("password");
		
		//read data from excel sheet
				FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\Test2.xlsx");
				Workbook wb = WorkbookFactory.create(fi);
				Sheet sh = wb.getSheet("purchaseorder");
				Row ro = sh.getRow(1);
				Cell cel = ro.getCell(4);
				String SubjectName = cel.getStringCellValue();
				String SubjectNameRan=SubjectName+" "+random;
				
				//Reading Billing address from excell sheet
				
				Cell cel1 = ro.getCell(5);
				String BillingAdress=cel1.getStringCellValue();
				String BillingAdressRan=BillingAdress+" "+random;
				
				//Reading Shipping address from excell sheet
				
				Cell cel11 = ro.getCell(6);
				String ShippingAdress=cel11.getStringCellValue();
				String ShippingAdressRan=ShippingAdress+" "+random;
				
				//Reading item name address from excell sheet
				
				Cell cel111 = ro.getCell(7);
				String item=cel111.getStringCellValue();
				String itemRan=item+" "+random;
				
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
				
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.get(URL);
				
			/*Step 3: login to application*/
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();
				
			/*Step 4: navigating to More link*/
				
				WebElement mouse = driver.findElement(By.linkText("More"));
				
				Actions act = new Actions(driver);
				
				act.moveToElement(mouse).perform();
				
			//*Step 5: Clicking on purchase order link and Create purchase Order*//
				
				driver.findElement(By.linkText("Purchase Order")).click();
				
				driver.findElement(By.xpath("//img[@title='Create Purchase Order...']")).click();
				
				driver.findElement(By.name("subject")).sendKeys(SubjectNameRan);
				
				//Selecting Vendor Name
				
				driver.findElement(By.xpath("//input[@name='vendor_name']/following-sibling::img[@title='Select']")).click();
				
				Set<String> window = driver.getWindowHandles();
				
				for(String WID : window) {
					
					driver.switchTo().window(WID);
				}
				
				// Selecting Vendor name from list//
				
				driver.findElement(By.id("6")).click();
				
				
				Set<String> window1 = driver.getWindowHandles();
				
				for(String WID : window1) {
					
					driver.switchTo().window(WID);
				}
				
				
				// Reading Billing and Shipping Address from excell sheet//
				
				driver.findElement(By.name("bill_street")).sendKeys(BillingAdressRan);
				
				driver.findElement(By.name("ship_street")).sendKeys(ShippingAdressRan);
				
				driver.findElement(By.id("searchIcon1")).click();
				
				
				Set<String> window11 = driver.getWindowHandles();
				
				for(String WID : window11) {
					
					driver.switchTo().window(WID);
				}
				
				
				// Selecting the Item from the List//
				
				driver.findElement(By.id("popup_product_30")).click();
				
				
				
				Set<String> window111 = driver.getWindowHandles();
				
				for(String WID : window111) {
					
					driver.switchTo().window(WID);
				}
				
				
				
				//selecting the number of quantity for Item//
				
				driver.findElement(By.id("qty1")).sendKeys("1");
				
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
			/*Step 6:** Signout of the application**/
				
				WebElement element = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				Actions act1 = new Actions(driver);
				act1.moveToElement(element).perform();
				
				driver.findElement(By.linkText("Sign Out")).click();
				driver.quit();
				
	}
}