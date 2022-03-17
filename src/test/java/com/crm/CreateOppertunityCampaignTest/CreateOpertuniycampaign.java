package com.crm.CreateOppertunityCampaignTest;

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
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcellFileUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.GenericLibrary.javaUtility;

import io.github.bonigarcia.wdm.WebDriverManager;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class CreateOpertuniycampaign {
	

	@Test
	
	public void CreateOpertuniycampaignTest() throws Throwable
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
		
		String opertunityname = eLib.readDataFromExcel("opertunities", 1, 2)+"_"+jLib.getRandomNumber();
		String Orgname= eLib.readDataFromExcel("opertunities", 1, 3);
		String Organizationname=eLib.readDataFromExcel("Org", 1, 2);
		String leadsourcename=eLib.readDataFromExcel("opertunities", 1, 4);
		String Campname=eLib.readDataFromExcel("Campaign", 1, 2);
		String lastName=eLib.readDataFromExcel("Contact", 1, 2);
				
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
				
			// srtep4: creating camapaign	
				WebElement mouse = driver.findElement(By.linkText("More"));
				
				wLib.mouseHover(driver, mouse);
				
				
				//: click on camapign link*/
				
				driver.findElement(By.linkText("Campaigns")).click();
				
				driver.findElement(By.xpath("//img[@title='Create Campaign...']")).click();
				
				driver.findElement(By.name("campaignname")).sendKeys(Campname);
				
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				//: verfify for campaign*/
				String campaignHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(campaignHeader.contains(Campname))
				{
					System.out.println(campaignHeader +" campaign created");
				}
				else
				{
					System.out.println("campaign not created");
				}
				
			// srtep5: creating contact
				
				driver.findElement(By.linkText("Contacts")).click();
				driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
				driver.findElement(By.name("lastname")).sendKeys(lastName);
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
			// verifying contact creation
				
				String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(contactHeader.contains(lastName))
				{
					System.out.println(contactHeader +" contact created");
				}
				else
				{
					System.out.println("contact not created");
				}
			
			// srtep6: creating opertunities
				driver.findElement(By.linkText("Opportunities")).click();
				driver.findElement(By.xpath("//img[@title='Create Opportunity...']")).click();
				
				driver.findElement(By.xpath("//input[@name='potentialname']")).sendKeys(opertunityname);
				WebElement orgCat = driver.findElement(By.id("related_to_type"));
				wLib.select(Orgname, orgCat);
				
				driver.findElement(By.xpath("//input[@id='related_to']/following-sibling::img[@src='themes/softed/images/select.gif']")).click();
				wLib.switchToWindow(driver, "Accounts");
				
				driver.findElement(By.name("search_text")).sendKeys(lastName);
				driver.findElement(By.name("search")).click();
				driver.findElement(By.linkText(lastName)).click();
				
				wLib.switchToWindow(driver, "Potentials");
				
				WebElement leadsource = driver.findElement(By.name("leadsource"));
				wLib.select(leadsourcename, leadsource);
				
				driver.findElement(By.xpath("//input[@name='campaignname']/following-sibling::img[@src='themes/softed/images/select.gif']")).click();
				wLib.switchToWindow(driver, "Campaigns");
				driver.findElement(By.name("search_text")).sendKeys(Campname);
				driver.findElement(By.name("search")).click();
				driver.findElement(By.linkText(Campname)).click();
				
				
				
				wLib.switchToWindow(driver, "Potentials");
				
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				//Thread.sleep(5000);
				
			/*Step 07: logout and close the browser*/
				WebElement element = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				wLib.mouseHover(driver, element);
				
				driver.findElement(By.linkText("Sign Out")).click();
				driver.quit();

				
	}
}
				