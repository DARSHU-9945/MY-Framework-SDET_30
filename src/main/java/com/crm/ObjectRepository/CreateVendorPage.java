package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class CreateVendorPage extends WebDriverUtility {
	
	//step-1: declaration
		@FindBy(name="vendorname")
		private WebElement vendornameTextField;
		
		@FindBy(xpath="//input[@title='Save [Alt+S]']")
		private WebElement saveBtn;

		public CreateVendorPage(WebDriver driver) 
		{
			PageFactory.initElements(driver, this);
			
		}

		public WebElement getVendornameTextField() {
			return vendornameTextField;
		}
		
		public WebElement getSaveBtn() {
			return saveBtn;
		}

		public void CreateVendor(WebDriver driver, String VendorName) 
		{
			vendornameTextField.sendKeys(VendorName);
			saveBtn.click();
			
		}
		
		

}
