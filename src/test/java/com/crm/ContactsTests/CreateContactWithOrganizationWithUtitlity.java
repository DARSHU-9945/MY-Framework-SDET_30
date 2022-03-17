package com.crm.ContactsTests;

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

import com.crm.GenericLibrary.ExcellFileUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.GenericLibrary.javaUtility;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class CreateContactWithOrganizationWithUtitlity {
	
	@Test
	public void CreateContactWithOrganizationWithUtitlity() throws Throwable
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
		
		String lastName=elib.readDataFromExcel("Contact", 4, 2)+"_"+jlib.getRandomNumber();
		String OrgName=elib.readDataFromExcel("Contact", 4, 3)+"_"+jlib.getRandomNumber();
		
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
		
		/*Step 4: Navigate to Organizations Link*/
		driver.findElement(By.linkText("Organizations")).click();
		
		/*Step 5: click on create organization btn*/
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		/*Step 6: enter mandatory fields and save*/
		driver.findElement(By.name("accountname")).sendKeys(OrgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		/*Step 7:verify for orgaqnization*/
		String header = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(header.contains(OrgName))
		{
			System.out.println(header);
			System.out.println("org created");
		}
		else
		{
			System.out.println(header);
			System.out.println("Org not created");
		}
		
		/*step 8: navigate to contacts link*/
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img[@alt='Select']")).click();
		
		/*Step 9: choose org */
		Set<String> win = driver.getWindowHandles();
		for(String winId:win)
		{
			driver.switchTo().window(winId);
		}
		
		driver.findElement(By.name("search_text")).sendKeys(OrgName);
		driver.findElement(By.name("search")).click();
		
		driver.findElement(By.xpath("//a[text()='"+OrgName+"']")).click();
		
		wlib.switchToWindow(driver, "Contact");
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		/*Step 10: verfify for contact*/
		String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(contactHeader.contains(lastName))
		{
			System.out.println(contactHeader +" contact created");
		}
		else
		{
			System.out.println("contact not created");
		}
		
		/*Step 11: logout and close the browser*/
		WebElement element = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wlib.mouseHover(driver, element);
		
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();

}

}
