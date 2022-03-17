package com.crm.CreateCamapignTest;

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
import com.crm.ObjectRepository.CamapignsPage;
import com.crm.ObjectRepository.CreateCampaignpage;
import com.crm.ObjectRepository.CreateProductsPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;
import com.crm.ObjectRepository.ProductInfoPage;
import com.crm.ObjectRepository.ProductsPage;

import io.github.bonigarcia.wdm.WebDriverManager;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class CreateCamapignWithProductWithUtilityPOM {
	
	@Test
	public void CreateCamapignWithProductWithUtility() throws Throwable
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
		
		String campaignName = eLib.readDataFromExcel("Campaign", 1, 2)+"_"+jLib.getRandomNumber();
		String prodName = eLib.readDataFromExcel("Campaign", 4, 3);
		String catType = eLib.readDataFromExcel("Campaign", 7, 4);
	
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
		
		/*Step 3: login to application*/
		LoginPage lp= new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		/*Step 4: Navigate to Contacts Link*/
		HomePage hp= new HomePage(driver);
		hp.ClickOnProductsLink();
		
		ProductsPage pp= new ProductsPage(driver);
		pp.CreateProductLink();
		
		CreateProductsPage cpp=new CreateProductsPage(driver);
		cpp.createProduct(driver, prodName);
		
		ProductInfoPage pip= new ProductInfoPage(driver);
		String ActProdHeader=pip.producttitle();
		if(ActProdHeader.contains(prodName))
		{
			System.out.println("Product is created");
		}
		else
		{
			System.out.println("product not created");
		}
		
		hp.ClickOnMOreLink();
		hp.ClickcampaignLink();
		
		CamapignsPage cp= new CamapignsPage(driver);
		cp.createCampaignLink();
		
		CreateCampaignpage ccp=new CreateCampaignpage(driver);
		ccp.createCampaign(driver, campaignName, prodName);
		
		hp.signOutOfApp(driver);
		
		driver.quit();
		
	}


}
