package com.crm.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class CreateProductsPage extends WebDriverUtility {
	
	//declaration
	
	@FindBy(name="productname")
	private WebElement Productname;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/select.gif']")
	private WebElement VendorLookup;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="search_text")
	private WebElement searchEdt;
	
	@FindBy(name="search")
	private WebElement searchBtn;
	
	@FindBy(name="productcategory")
	private WebElement productcategory;
	

	public CreateProductsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getProductname() {
		return Productname;
	}

	
	public WebElement getVendorLookup() {
		return VendorLookup;
	}
	
	

	public WebElement getSearchEdt() {
		return searchEdt;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getProductcategory() {
		return productcategory;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	//business library
	public void createProduct(WebDriver driver, String prodname) {
		Productname.sendKeys(prodname);
		saveBtn.click();
	}
	
	public void createProduct(WebDriver driver, String prodname, String prodCategory, String vendorName) {
		Productname.sendKeys(prodname);
		select(prodCategory, productcategory);
		VendorLookup.click();
		switchToWindow(driver, "Vendors");
		searchEdt.sendKeys(vendorName);
		searchBtn.click();
		driver.findElement(By.xpath("//a[text()='"+vendorName+"']")).click();
		switchToWindow(driver, "Products");
		saveBtn.click();
	}
}
