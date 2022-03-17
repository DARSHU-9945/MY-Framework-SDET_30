package com.crm.CreateCamapignTest;

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
public class CreateCampaignProductTest {
	
	@Test
	public void createContactWithOrgTest() throws Throwable
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
				FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\Testdata1.xlsx");
				Workbook wb = WorkbookFactory.create(fi);
				Sheet sh = wb.getSheet("Product");
				Row ro = sh.getRow(1);
				Cell cel = ro.getCell(2);
				String prdName = cel.getStringCellValue();
				String prdNameRan=prdName+" "+random;
				
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
				
				/*Step 4: Navigate to products Link*/

				driver.findElement(By.linkText("Products")).click();
				
				driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
				
				driver.findElement(By.name("productname")).sendKeys(prdNameRan);
				
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				/*Step 5:verify for product*/
				String header = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
				if(header.contains(prdNameRan))
				{
					System.out.println(header);
					System.out.println("product created");
				}
				else
				{
					System.out.println(header);
					System.out.println("product not created");
				}
				
				/*Step 6: navigating to More link*/
				
				WebElement mouse = driver.findElement(By.linkText("More"));
				
				Actions act = new Actions(driver);
				
				act.moveToElement(mouse).perform();
				
				/*Step 7: click on camapign link*/
				
				driver.findElement(By.linkText("Campaigns")).click();
				
				driver.findElement(By.xpath("//img[@title='Create Campaign...']")).click();
				
				//read data from excel sheet
				
				Sheet sh1 = wb.getSheet("Campaighn");
				Row ro1 = sh1.getRow(1);
				Cell cel1 = ro1.getCell(2);
				String camName = cel1.getStringCellValue();
				String camNameRan=camName+" "+random;
				
				driver.findElement(By.name("campaignname")).sendKeys(camNameRan);
				
				driver.findElement(By.xpath("//input[@name='product_name']/following-sibling::img[@title='Select']")).click();
				
				/*Step 8: switching to add product window*/
				
				Set<String> win = driver.getWindowHandles();
				for(String winId:win)
				{
					driver.switchTo().window(winId);
				}
				
				driver.findElement(By.name("search_text")).sendKeys(prdNameRan);
				driver.findElement(By.name("search")).click();
				
				driver.findElement(By.xpath("//a[text()='"+prdNameRan+"']")).click();
				
				/*Step 9: switching back to main window*/
				
				Set<String> win1 = driver.getWindowHandles();
				for(String winId1:win1)
				{
					driver.switchTo().window(winId1);
				}
				
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				/*Step 10: logout and close the browser*/
				
				WebElement element = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				Actions act1 = new Actions(driver);
				act1.moveToElement(element).perform();
				
				driver.findElement(By.linkText("Sign Out")).click();
				driver.quit();
				
				
				
}
}
