package com.crm.Practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcellFileUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.GenericLibrary.javaUtility;
import com.crm.ObjectRepository.CreateProductsPage;
import com.crm.ObjectRepository.CreateVendorPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;
import com.crm.ObjectRepository.ProductInfoPage;
import com.crm.ObjectRepository.ProductsPage;
import com.crm.ObjectRepository.VendorInfoPage;
import com.crm.ObjectRepository.VendorPage;

import io.github.bonigarcia.wdm.WebDriverManager;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class CreateProductWithCategoryandVendor {
	
	@Test
	public void CreateProductWithCategoryandVendor() throws Throwable
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
		
		String prodName = eLib.readDataFromExcel("Products", 4, 2)+"_"+jLib.getRandomNumber();
		String prodCategory = eLib.readDataFromExcel("Products", 4, 3);
		String vendorName = eLib.readDataFromExcel("Products", 4,4)+"_"+jLib.getRandomNumber();
	
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
		
		hp.ClickOnMOreLink();
		hp.VendorLinkText();
		
		VendorPage vPage= new VendorPage(driver);
		vPage.clickOnCreateVendorLookUpImg();
		
		CreateVendorPage cvp = new CreateVendorPage(driver);
		cvp.CreateVendor(driver, vendorName);
		
		VendorInfoPage vip= new VendorInfoPage(driver);
		String ActHeader = vip.VendorHeaderInfo();
		if(ActHeader.contains(vendorName)) {
			System.out.println(ActHeader+"----->data verified");
		}
		else
		{
			System.out.println("data invalid");
		}
		
		hp.ClickOnProductsLink();
		
		Assert.fail();
		
		ProductsPage pp= new ProductsPage(driver);
		pp.CreateProductLink();
		
		CreateProductsPage cpp=new CreateProductsPage(driver);
		cpp.createProduct(driver, prodName, prodCategory, vendorName);
		
		ProductInfoPage pInfoPage= new ProductInfoPage(driver);
		String ActProdHeader = pInfoPage.producttitle();
		if(ActProdHeader.contains(prodName)) {
			System.out.println(ActProdHeader+"----->data verified");
		}
		else
		{
			System.out.println("data invalid");
		}
		
		hp.signOutOfApp(driver);
		driver.quit();

}
}
