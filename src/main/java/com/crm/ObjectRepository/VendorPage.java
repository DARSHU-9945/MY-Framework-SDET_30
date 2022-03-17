package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VendorPage {
	
	//declaration
	
		@FindBy(xpath="//img[@alt='Create Vendor...']")
		private WebElement createVendorLookUpImg;
		
		//initialization
		public VendorPage(WebDriver driver) 
		{
			PageFactory.initElements(driver, this);
		}
		
		//utilization
		public WebElement getVendorLookUpImg() 
		{
			return createVendorLookUpImg;
			
		}
		
		//business LIbrary
		public void clickOnCreateVendorLookUpImg()
		{
			createVendorLookUpImg.click();
		}

}
