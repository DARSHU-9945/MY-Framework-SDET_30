package com.crm.GenericLibrary;

import static org.testng.Assert.assertNotNull;

import javax.crypto.interfaces.DHPublicKey;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	//Create Obj for all utilities
			public DataBaseUtility dbLib = new DataBaseUtility();
			public	PropertyFileUtility pLib = new PropertyFileUtility();
			public	ExcellFileUtility eLib = new ExcellFileUtility();
			public	WebDriverUtility wLib = new WebDriverUtility();
			public	javaUtility jLib = new javaUtility();
			public WebDriver driver;
			public static WebDriver sDriver;
			  
			
	//===============================================================================================//		
			
			
			@BeforeSuite(groups = {"regressionSuite","smokeSuite"})
			public void connectdatabase()
			{
				//dbLib.connectToDB();
				Reporter.log("========db connection established+++++", true);
				
			}
    //===============================================================================================//			
			@BeforeTest
			public void beforeTest() {
				
				System.out.println("parallel execution");
				
			}
			
			
			
			@Parameters("browser")
			@BeforeClass(groups = {"regressionSuite","smokeSuite"})
			
			
			
			
			public void launchtheBrowser(String BROWSER) throws Throwable
			{
				//read data 
				//String BROWSER = pLib.readDataFromPropertyFile("browser");
				String URL = pLib.readDataFromPropertyFile("url");
				
				// create runtime polymorphism
				
				if(BROWSER.equalsIgnoreCase("chrome"))
				{
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
				}
				else if(BROWSER.equalsIgnoreCase("FIREFOX"))
				{
					WebDriverManager.firefoxdriver().setup();
					driver = new FirefoxDriver();
				}
				else
				{
					System.out.println("invalid browser");
				}
				
				sDriver = driver; 
				
				wLib.maximizeWindow(driver);
				wLib.waitForPageLoad(driver);
				driver.get(URL);
				Reporter.log("================Browser launch succesfull=====",true);
			}
	
	//===============================================================================================//	
			
			
			@BeforeMethod(groups = {"regressionSuite","smokeSuite"})
			public void login() throws Throwable 
			{
				String USERNAME = pLib.readDataFromPropertyFile("username");
				String PASSWORD = pLib.readDataFromPropertyFile("password");
				LoginPage lp = new LoginPage(driver);
				lp.loginToApp(USERNAME, PASSWORD);
				Reporter.log("login successful",true);
				
			}
			
   //===============================================================================================//	
			
			
			@AfterMethod(groups = {"regressionSuite","smokeSuite"})
			public void logout()
			{
				HomePage hp = new HomePage(driver);
				hp.signOutOfApp(driver);
				Reporter.log("========logout Succesfull=======",true);
				
			}
			
   //===============================================================================================//	
			
			
			@AfterClass(groups = {"regressionSuite","smokeSuite"})
			public void closeBrowser()
			{
				driver.quit();
				Reporter.log("========Browser close Succesfull=======",true);
			}
			
			@AfterTest
			public void afterTest() {
				
				System.out.println("closed- para exec");
				
			}
			
			
			
  //===============================================================================================//	
			
			
			@AfterSuite(groups = {"regressionSuite","smokeSuite"})
			public void closeDb()
			{
				//dbLib.closeDB();
				Reporter.log("========Database close Succesfull=======",true);
			}

}
