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
public class CreatePurchaseOrderWithVendorNameTest {
	
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
				Sheet sh = wb.getSheet("PurchaseOrder");
				Row ro = sh.getRow(3);
				Cell cel = ro.getCell(2);
				String VendorName = cel.getStringCellValue();
				//String VendorNameRan=VendorName+" "+random;//
				
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
				
			//*Step 6: Selecting Vendor Name from the new vendor list
				
				driver.findElement(By.xpath("//input[@name='vendor_name']/following-sibling::img[@title='Select']")).click();
				
				Set<String> window = driver.getWindowHandles();
				
				for(String WID : window) {
					
					driver.switchTo().window(WID);
				}

			//*Step 7: searching the vendor from the vendor list
				
				driver.findElement(By.name("search_text")).sendKeys(VendorName);
				
				driver.findElement(By.name("search")).click();
				
				driver.findElement(By.xpath("//a[text()='"+VendorName+"']")).click();
				
				// switching back to main window//
				
				
				Set<String> window1 = driver.getWindowHandles();
				
				for(String WID : window1) {
					
					driver.switchTo().window(WID);
				}
				
			/*Step 08: logout and close the browser*/
				
				WebElement element = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				Actions act1 = new Actions(driver);
				act1.moveToElement(element).perform();
				
				driver.findElement(By.linkText("Sign Out")).click();
				driver.quit();
				
     }
	
}
