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
import com.crm.ObjectRepository.CamapaignInfoPage;
import com.crm.ObjectRepository.CamapignsPage;
import com.crm.ObjectRepository.ContactInfoPage;
import com.crm.ObjectRepository.ContactsPage;
import com.crm.ObjectRepository.CreateCampaignpage;
import com.crm.ObjectRepository.CreateContactPage;
import com.crm.ObjectRepository.CreateOpportunitiesPage;
import com.crm.ObjectRepository.CreateOrganizationPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;
import com.crm.ObjectRepository.OpportunitiesInfoPage;
import com.crm.ObjectRepository.OpportunitiesPage;
import com.crm.ObjectRepository.OrganizationInfoPage;
import com.crm.ObjectRepository.OrganizationsPage;

import io.github.bonigarcia.wdm.WebDriverManager;
import okhttp3.internal.http2.Header;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class CreateOpertuniyTypeLeadcampaignTestPOM {
	

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
		
		String OpprtunityName = eLib.readDataFromExcel("Opportunities", 1, 2)+"_"+jLib.getRandomNumber();
		String RelatedTo= eLib.readDataFromExcel("Opportunities", 1, 3);
		String Orgname=eLib.readDataFromExcel("Org", 1, 2)+"_"+jLib.getRandomNumber();
		String Type = eLib.readDataFromExcel("Opportunities", 1, 6);
		String LeadSource = eLib.readDataFromExcel("Opportunities", 1, 4);
		String CampaignSource=eLib.readDataFromExcel("Opportunities", 1, 5)+"_"+jLib.getRandomNumber();
				
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
				LoginPage lp= new LoginPage(driver);
				lp.loginToApp(USERNAME, PASSWORD);
				
				/*Step 4: Navigate to Contacts Link*/
				HomePage hp= new HomePage(driver);
				hp.ClickOnOrgLink();
				
				/*Step 5: click on create org swanization btn*/
				OrganizationsPage op= new OrganizationsPage(driver);
				op.clickOnCreateOrgImg();
				
				/*Step 6: enter mandatory fields and save*/
				CreateOrganizationPage cop= new CreateOrganizationPage(driver);
				cop.CreateNewOrg(Orgname);
				
				//verification
				OrganizationInfoPage oip= new OrganizationInfoPage(driver);
				String actOrgName=oip.OrgNameInfo();
				if(actOrgName.contains(Orgname)) {
					
					System.out.println(actOrgName+"----->data verified");
				}
				else
				{
					System.out.println("data invalid");
				}
				
				hp.ClickOnContactLink();
				
				/*Step 7: click on create contact btn*/
				ContactsPage cp= new ContactsPage(driver);
				cp.clickOnCreateContactImg();
				
				/*Step 8: enter mandatory fields and save*/
				CreateContactPage ccp= new CreateContactPage(driver);
				ccp.CreateNewContact(Orgname);
				
				//verification
				ContactInfoPage cip= new ContactInfoPage(driver);
				String actConName=cip.contactNameInfo();
				if(actConName.contains(Orgname)) {
					System.out.println(actConName+"----->data verified");
				}
				else
				{
					System.out.println("data invalid");
				}
				
				hp.ClickOnMOreLink();
				
				hp.ClickcampaignLink();
				
				CamapignsPage cp1 = new CamapignsPage(driver);
				cp1.createCampaignLink();
				
				CreateCampaignpage ccp1= new CreateCampaignpage(driver);
				ccp1.createCampaign(CampaignSource);
				
				
				//verification
				CamapaignInfoPage cip1= new CamapaignInfoPage(driver);
				String Header = cip1.CampaignHeaderTextInfo();
				if(Header.contains(CampaignSource)) {
					System.out.println(Header+"----->data verified");
				}
				else
				{
					System.out.println("data invalid");
				}
				
				hp.OpportunitiesLink();
				
				OpportunitiesPage op1 = new OpportunitiesPage(driver);
				op1.CreateOpportunity();
				
				CreateOpportunitiesPage cop1= new CreateOpportunitiesPage(driver);
				cop1.CreatingNewOpportunity(driver, OpprtunityName, RelatedTo, Orgname, Type, LeadSource, CampaignSource);
				
				OpportunitiesInfoPage oip1= new OpportunitiesInfoPage(driver);
				
				//verification
				String actHeader = oip1.verificationOpportunityPage();
				if(actHeader.contains(OpprtunityName)) {
					
					System.out.println(actHeader+"----->data verified");
				}
				else
				{
					System.out.println("data invalid");
				}
				
				hp.signOutOfApp(driver);
				
				driver.quit();

				
	}
}
				