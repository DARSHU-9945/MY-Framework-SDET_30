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
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.GenericLibrary.ExcellFileUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.GenericLibrary.javaUtility;
import com.crm.ObjectRepository.ContactInfoPage;
import com.crm.ObjectRepository.ContactsPage;
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
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class CreateOpertuniycampaignTestPOM2withBaseclass extends BaseClass {
	

	@Test
	
	public void CreateOpertuniycampaignTest() throws Throwable
	{
		
		String OpprtunityName = eLib.readDataFromExcel("Opportunities", 1, 2)+"_"+jLib.getRandomNumber();
		String RelatedTo= eLib.readDataFromExcel("Opportunities", 1, 3);
		String Orgname=eLib.readDataFromExcel("Org", 1, 2)+"_"+jLib.getRandomNumber();
				
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
				
				
				ContactInfoPage cip= new ContactInfoPage(driver);
				String actConName=cip.contactNameInfo();
				if(actConName.contains(Orgname)) {
					
					Reporter.log(actConName+"======Created and verified succesfully=======",true);
				}
				else
				{
					Reporter.log("======Created and verified succesfully=======",true);
				}
				
				
				hp.OpportunitiesLink();
				
				OpportunitiesPage op1 = new OpportunitiesPage(driver);
				op1.CreateOpportunity();
				
				CreateOpportunitiesPage cop1= new CreateOpportunitiesPage(driver);
				cop1.CreatingNewOpportunity(driver, OpprtunityName, RelatedTo, Orgname);
				
				OpportunitiesInfoPage oip1= new OpportunitiesInfoPage(driver);
				String actHeader = oip1.verificationOpportunityPage();
				if(actHeader.contains(Orgname)) {
					
					Reporter.log(actHeader+"======Created and verified succesfully=======",true);
				}
				else
				{
					Reporter.log("======NOt Created and NOt verified succesfully=======",true);
				}
				
	}
}
				