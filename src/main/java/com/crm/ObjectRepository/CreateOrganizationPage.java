package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class CreateOrganizationPage extends WebDriverUtility {
	
	//step-1: declaration
	@FindBy(name="accountname")
	private WebElement OrgNameEdt;
	
	@FindBy(name="industry")
	private WebElement industryDropDown;

	@FindBy(name="accounttype")
	private WebElement typeDropDown;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	//step-2: initialization
	
	public CreateOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//step-3: utilization

	public WebElement getOrgNameEdt() {
		return OrgNameEdt;
	}

	public WebElement getIndustryDropDown() {
		return industryDropDown;
	}

	public WebElement getTypeDropDown() {
		return typeDropDown;
	}

	public WebElement getTypeDropDownsaveBtn() {
		return saveBtn;
	}
	
	//business libarary
	public void CreateNewOrg(String orgName)
	{
		OrgNameEdt.sendKeys(orgName);
		saveBtn.click();
	}
	
	public void CreateNewOrg(String orgName, String indType)
	{
		OrgNameEdt.sendKeys(orgName);
		select(indType, industryDropDown);
		saveBtn.click();
		
	}
	
	public void CreateNewOrg(String orgName, String indType, String type)
	{
		OrgNameEdt.sendKeys(orgName);
		select(indType, industryDropDown);
		select(type, typeDropDown);
		saveBtn.click();
		
	}
	
	
}
