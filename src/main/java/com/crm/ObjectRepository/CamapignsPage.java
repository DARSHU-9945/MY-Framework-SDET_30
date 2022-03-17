package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CamapignsPage {
	
	
	@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement CreateCampaignlink;
	
	public CamapignsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getCreateCampaignlink() {
		return CreateCampaignlink;
	}
	
	//business library
	
	public void createCampaignLink() {
		
		CreateCampaignlink.click();
	}
}
