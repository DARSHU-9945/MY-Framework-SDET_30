package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PurchaseOrderInfoPage {
	
	@FindBy(xpath="//span[@class='lvtHeaderText']")
	private WebElement purchaseorderHeader;
	
	public PurchaseOrderInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getPurchaseorderHeader() {
		return purchaseorderHeader;
	}
	
	public String PurchaseOrderTitle()
	{
		String Header = purchaseorderHeader.getText();
		System.out.println(Header);
		return Header;
	}

}
