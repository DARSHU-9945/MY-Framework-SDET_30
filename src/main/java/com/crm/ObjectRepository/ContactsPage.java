package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {
	
	//declaration
	
	@FindBy(xpath="//img[@alt='Create Contact...']")
	private WebElement createContactLookUpImg;

	
	//initialization
	public ContactsPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	//utilization
	public WebElement getCreateContctLookUpImg() 
	{
		return createContactLookUpImg;
		
	}
	
	public WebElement getCreateContactLookUpImg() {
		return createContactLookUpImg;
	}

	

	//business LIbrary
	public void clickOnCreateContactImg()
	{
		createContactLookUpImg.click();
	}
	
	
}


