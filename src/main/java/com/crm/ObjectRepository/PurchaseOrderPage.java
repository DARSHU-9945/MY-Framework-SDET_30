package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PurchaseOrderPage {
	
	@FindBy(xpath="//img[@alt='Create Purchase Order...']")
	private WebElement PurchasePlusButton;
	
	public PurchaseOrderPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getPurchasePlusButton() {
		return PurchasePlusButton;
	}
	
	public void ClickPurchasePlussbutton()
	{
		PurchasePlusButton.click();
	}

}
