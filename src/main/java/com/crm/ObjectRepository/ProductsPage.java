package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
	
	//declaration
	
	@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement createproductslink;
	
	
	//initialization
	public ProductsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//utilization
	public WebElement getCreateproductslink() {
		return createproductslink;
	}
	
	
// business library
	public void CreateProductLink() {
		createproductslink.click();
	}
	
}
