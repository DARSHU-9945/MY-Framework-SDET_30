package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;
import okhttp3.internal.http2.Header;

public class ContactInfoPage extends WebDriverUtility {
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement contactHeaderText;
	
	public ContactInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getContactHeaderText() {
		return contactHeaderText;
	}
	
	
	//Business library
	public String contactNameInfo()
	{
		String Header = contactHeaderText.getText();
		System.out.println(Header);
		return Header;
	}
	
	

}
