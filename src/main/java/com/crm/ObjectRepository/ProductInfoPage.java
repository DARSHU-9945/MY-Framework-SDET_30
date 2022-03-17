package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductInfoPage {
	
	@FindBy(xpath="//span[@class='lvtHeaderText']")
	private WebElement prodHeader;
	
	public ProductInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getProdHeader() {
		return prodHeader;
	}

	//Business libarary
	
	public String producttitle() {
		String Header = prodHeader.getText();
		System.out.println(Header);
		return Header;
		
	}
	
}
