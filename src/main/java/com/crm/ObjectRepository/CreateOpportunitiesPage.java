package com.crm.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.GenericLibrary.WebDriverUtility;

public class CreateOpportunitiesPage extends WebDriverUtility {
	
	@FindBy(name="potentialname")
	private WebElement opportunityNameTextFieldElement;
	
	@FindBy(name="related_to_type")
	private WebElement RelatedToDropDown;
	
	@FindBy(xpath ="//input[@name='related_to']/following-sibling::img[@src='themes/softed/images/select.gif']")
	private WebElement RelatedToPlussBtn;
	
	@FindBy(name="search_text")
	private WebElement searchEdt;
	
	@FindBy(name="search")
	private WebElement searchBtn;
	
	@FindBy(name="leadsource")
	private WebElement leadsourceDropDown;
	
	@FindBy(xpath ="//input[@name='campaignname']/following-sibling::img[@src='themes/softed/images/select.gif']")
	private WebElement campaignnamePlussBtn;
	
	@FindBy(name="opportunity_type")
	private WebElement TypeDropDown;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	public  CreateOpportunitiesPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getOpportunityNameTextFieldElement() {
		return opportunityNameTextFieldElement;
	}

	public WebElement getRelatedToDropDown() {
		return RelatedToDropDown;
	}

	public WebElement getRelatedToPlussBtn() {
		return RelatedToPlussBtn;
	}

	public WebElement getSearchEdt() {
		return searchEdt;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getLeadsourceDropDown() {
		return leadsourceDropDown;
	}

	public WebElement getCampaignnamePlussBtn() {
		return campaignnamePlussBtn;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getTypeDropDown() {
		return TypeDropDown;
	}
	
	public void CreatingNewOpportunity(WebDriver driver, String OpprtunityName, String RelatedTo, String Orgname)
	{
		opportunityNameTextFieldElement.sendKeys(OpprtunityName);
		select(RelatedTo,RelatedToDropDown);
		RelatedToPlussBtn.click();
		switchToWindow(driver, "Contacts");
		searchEdt.sendKeys(Orgname);
		searchBtn.click();
		driver.findElement(By.xpath("//a[contains(text(),'"+Orgname+"')]")).click();
		switchToWindow(driver, "Potentials");
		saveBtn.click();
	}

	public void CreatingNewOpportunity(WebDriver driver, String OpprtunityName, String RelatedTo, String Orgname, String Type, String LeadSource, String CampaignSource)
	{
		opportunityNameTextFieldElement.sendKeys(OpprtunityName);
		select(RelatedTo,RelatedToDropDown);
		RelatedToPlussBtn.click();
		switchToWindow(driver, "Contacts");
		searchEdt.sendKeys(Orgname);
		searchBtn.click();
		driver.findElement(By.xpath("//a[contains(text(),'"+Orgname+"')]")).click();
		switchToWindow(driver, "Potentials");
		select(Type, TypeDropDown);
		select(LeadSource, leadsourceDropDown);
		campaignnamePlussBtn.click();
		switchToWindow(driver, "Campaigns");
		searchEdt.sendKeys(CampaignSource);
		searchBtn.click();
		driver.findElement(By.xpath("//a[text()='"+CampaignSource+"']")).click();
		switchToWindow(driver, "Potentials");
		saveBtn.click();
	}
}
