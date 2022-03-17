package com.crm.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.GenericLibrary.WebDriverUtility;

public class CreateContactPage extends WebDriverUtility {
	
	//step-1: declaration
	@FindBy(name="lastname")
	private WebElement lastNameEdt;
	
	@FindBy(xpath="//input[@name='account_name']/following-sibling::img[@src='themes/softed/images/select.gif']")
	private WebElement orgNameLookUpImg;
	
	@FindBy(name="leadsource")
	private WebElement leadsourceDropdown;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="search_text")
	private WebElement searchEdt;
	
	@FindBy(name="search")
	private WebElement searchBtn;
	
	//initialization
	public CreateContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	//utilization

	public WebElement getLastNameEdt() {
		return lastNameEdt;
	}

	public WebElement getOrgNameLookUpImg() {
		return orgNameLookUpImg;
	}

	public WebElement getSearchEdt() {
		return searchEdt;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getLeadsourceDropdown() {
		return leadsourceDropdown;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	

	//business Library
	public void CreateNewContact(String lastName)
	{
		
		lastNameEdt.sendKeys(lastName);
		saveBtn.click();
	}
	
	//create contact with last name and organization type
	public void CreateNewContact(WebDriver driver, String lastName, String orgName)
	{
		
		lastNameEdt.sendKeys(lastName);
		orgNameLookUpImg.click();
		switchToWindow(driver, "Accounts");
		searchEdt.sendKeys(orgName);
		searchBtn.click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		switchToWindow(driver, "Contacts");
		saveBtn.click();
	}
	
	//* create contact with lastname and organization type and lead src
	public void CreateNewContact(WebDriver driver, String lastName, String orgName, String LeadSrc)
	{
		
		lastNameEdt.sendKeys(lastName);
		orgNameLookUpImg.click();
		switchToWindow(driver, "Accounts");
		searchEdt.sendKeys(orgName);
		searchBtn.click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		switchToWindow(driver, "Contacts");
		select(leadsourceDropdown,LeadSrc);
		saveBtn.click();
	}
	
}
