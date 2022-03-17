package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpportunitiesPage {
	
	@FindBy(xpath = "//img[@alt='Create Opportunity...']")
	private WebElement opportunityPlussBtn;
	
	public OpportunitiesPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}

	public WebElement getOpportunityPlussBtn() {
		return opportunityPlussBtn;
	}
	
	public void CreateOpportunity() {
		opportunityPlussBtn.click();
		
	}

}
