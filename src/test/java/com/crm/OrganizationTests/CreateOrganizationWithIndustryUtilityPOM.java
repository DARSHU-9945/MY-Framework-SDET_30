package com.crm.OrganizationTests;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.GenericLibrary.ExcellFileUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.GenericLibrary.javaUtility;
import com.crm.ObjectRepository.CreateOrganizationPage;
import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;
import com.crm.ObjectRepository.OrganizationInfoPage;
import com.crm.ObjectRepository.OrganizationsPage;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class CreateOrganizationWithIndustryUtilityPOM extends BaseClass {
	
	@Test
	public void CreateOrganizationWithIndustryUtility() throws Throwable
	{
		
		String OrgName=eLib.readDataFromExcel("Org", 4, 2)+"_"+jLib.getRandomNumber();
		String indType=eLib.readDataFromExcel("Org", 4, 3);
		String type= eLib.readDataFromExcel("Org", 4, 4);
		
		/*Step 4: Navigate to Organizations Link*/
		HomePage hp= new HomePage(driver);
		hp.ClickOnOrgLink();
		
		/*Step 5: click on create org swanization btn*/
		OrganizationsPage op= new OrganizationsPage(driver);
		op.clickOnCreateOrgImg();
		
		/*Step 6: enter mandatory fields and save*/
		CreateOrganizationPage cop= new CreateOrganizationPage(driver);
		cop.CreateNewOrg(OrgName, indType, type);
		
		//verification
		
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actHeader=oip.OrgNameInfo();
		if(actHeader.contains(OrgName))
		{
			System.out.println(actHeader+"-------------> organization created");
		}
		else
		{
			System.out.println("organization not created");
		}
		
	}

}
