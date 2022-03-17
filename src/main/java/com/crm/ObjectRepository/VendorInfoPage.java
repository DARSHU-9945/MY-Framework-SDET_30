package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VendorInfoPage {
	
	@FindBy(xpath="//span[@class='lvtHeaderText']")
	private WebElement VendorHeaderText;
	
	public VendorInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getVendorHeaderText() {
		return VendorHeaderText;
	}
	
	
	//Business library
	public String VendorHeaderInfo()
	{
		String Header = VendorHeaderText.getText();
		System.out.println(Header);
		return Header;
	}
	

}
