package com.crm.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class CreateCampaignpage extends WebDriverUtility{

	@FindBy(name="campaignname")
	private WebElement CampaignNameText;
	
	@FindBy(xpath="//img[@src='themes/softed/images/select.gif']")
	private WebElement Productlookup;
	
	@FindBy(name="campaigntype")
	private WebElement campaigntypeDropDown;

	@FindBy(name="search_text")
	private WebElement searchtestfield;
	
	@FindBy(name="search")
	private WebElement searchbutton;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	public CreateCampaignpage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getCampaignNameText() {
		return CampaignNameText;
	}

	public WebElement getProductlookup() {
		return Productlookup;
	}

	public WebElement getSearchtestfield() {
		return searchtestfield;
	}
	
	public WebElement getCampaigntypeDropDown() {
		return campaigntypeDropDown;
	}

	public WebElement getSearchbutton() {
		return searchbutton;
	}
	
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	//business library

	public void createCampaign(String campName)
	{
		CampaignNameText.sendKeys(campName);
		saveBtn.click();
	}
	
	public void createCampaign(WebDriver driver, String campName, String prodName) {
		
		CampaignNameText.sendKeys(campName);
		Productlookup.click();
		switchToWindow(driver, "Products");
		searchtestfield.sendKeys(prodName);
		searchbutton.click();
		driver.findElement(By.xpath("//a[text()='"+prodName+"']")).click();
		switchToWindow(driver, "Campaigns");
		saveBtn.click();
	}
	
public void createCampaign(WebDriver driver, String campName,String CampType, String prodName) {
		
		CampaignNameText.sendKeys(campName);
		select(CampType, campaigntypeDropDown);
		Productlookup.click();
		switchToWindow(driver, "Products");
		searchtestfield.sendKeys(prodName);
		searchbutton.click();
		driver.findElement(By.xpath("//a[text()='"+prodName+"']")).click();
		switchToWindow(driver, "Campaigns");
		saveBtn.click();
	}
}
