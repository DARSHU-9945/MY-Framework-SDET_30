package com.crm.CreateCamapignTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.GenericLibrary.ExcellFileUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.GenericLibrary.javaUtility;
import com.crm.ObjectRepository.CamapaignInfoPage;
import com.crm.ObjectRepository.CamapignsPage;
import com.crm.ObjectRepository.CreateCampaignpage;
import com.crm.ObjectRepository.CreateProductsPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;
import com.crm.ObjectRepository.ProductInfoPage;
import com.crm.ObjectRepository.ProductsPage;

import io.github.bonigarcia.wdm.WebDriverManager;
import okhttp3.internal.http2.Header;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class CreateCamapignWithProductWithAdvertisementUtilityPOM3Baseclass extends BaseClass {
	
	@Test
	public void CreateCamapignWithProductWithUtility() throws Throwable
	{
		
		String campaignName = eLib.readDataFromExcel("Campaign", 7, 2)+"_"+jLib.getRandomNumber();
		String prodName = eLib.readDataFromExcel("Campaign", 7, 3)+"_"+jLib.getRandomNumber();
		String CampType = eLib.readDataFromExcel("Campaign", 7, 5);
	
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
		ccp.createCampaign(driver, campaignName,  CampType, prodName);
		
		CamapaignInfoPage cip = new CamapaignInfoPage(driver);
		String ActHeader = cip.CampaignHeaderTextInfo();
		if(ActHeader.contains(campaignName))
		{
			System.out.println("Campaign is created");
		}
		else
		{
			System.out.println("Campaign not created");
		}
		
	}


}
