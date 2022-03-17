package com.crm.ContactsTests;

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
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcellFileUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.GenericLibrary.javaUtility;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class CreateContactWithUtilityTest {
	@Test
	public void CreateContactWithUtilityTest() throws Throwable
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
		
		String lastName=elib.readDataFromExcel("Contact", 1, 2)+"_"+jlib.getRandomNumber();
		
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
		
		/*Step 4: Navigate to Contacts Link*/
		driver.findElement(By.linkText("Contacts")).click();
		
		/*Step 5: click on create contact btn*/
		driver.findElement(By.cssSelector("img[alt='Create Contact...']")).click();
		
		/*Step 6: enter mandatory fields and save*/
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		
		/*Step 7: logout of application*/
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wlib.mouseHover(driver, ele);
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
		/*Step 8: close the browser*/
		driver.quit();
	}
}


