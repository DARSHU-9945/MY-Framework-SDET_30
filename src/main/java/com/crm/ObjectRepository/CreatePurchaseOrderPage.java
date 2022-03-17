package com.crm.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class CreatePurchaseOrderPage extends WebDriverUtility{
	
	@FindBy(name="subject")
	private WebElement SubjectTextField;
	
	@FindBy(xpath="//input[@name='vendor_name']/following-sibling::img[@src='themes/softed/images/select.gif']")
	private WebElement Vendorlookup;
	
	@FindBy(xpath="//input[@name='contact_name']/following-sibling::img[@src='themes/softed/images/select.gif']")
	private WebElement Contactlookup;
	
	@FindBy(name="bill_street")
	private WebElement BillingAdressTextField;
	
	@FindBy(name="ship_street")
	private WebElement ShippingTextField;
	
	@FindBy(id="searchIcon1")
	private WebElement Additemlookup;

	@FindBy(name="qty1")
	private WebElement Quantitybox;
	
	@FindBy(name="search_text")
	private WebElement searchtestfield;
	
	@FindBy(name="search")
	private WebElement searchbutton;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	public CreatePurchaseOrderPage(WebDriver driver)

	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getSubjectTextField() {
		return SubjectTextField;
	}

	public WebElement getContactlookup() {
		return Contactlookup;
	}

	public WebElement getVendorlookup() {
		return Vendorlookup;
	}

	public WebElement getBillingAdressTextField() {
		return BillingAdressTextField;
	}

	public WebElement getShippingTextField() {
		return ShippingTextField;
	}

	public WebElement getAdditemlookup() {
		return Additemlookup;
	}

	public WebElement getQuantitybox() {
		return Quantitybox;
	}

	public WebElement getSearchtestfield() {
		return searchtestfield;
	}

	public WebElement getSearchbutton() {
		return searchbutton;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	
	public void CreatePurchaseorder(WebDriver driver, String SubjectName,  String VendorName,  String BillingAdress, String ShippingAdress, String item, String quantity) throws Throwable
	{
		SubjectTextField.sendKeys(SubjectName);
		Vendorlookup.click();
		switchToWindow(driver, "Vendors");
		searchtestfield.sendKeys(VendorName);
		searchbutton.click();
		driver.findElement(By.xpath("//a[text()='"+VendorName+"']")).click();
		switchToWindow(driver, "PurchaseOrder");
		BillingAdressTextField.sendKeys(BillingAdress);
		ShippingTextField.sendKeys(ShippingAdress);
		Additemlookup.click();
		switchToWindow(driver, "Products");
		searchtestfield.sendKeys(item);
		searchbutton.click();
		driver.findElement(By.xpath("//a[text()='"+item+"']")).click();
		Thread.sleep(2000);
		switchToWindow(driver, "PurchaseOrder");
		Quantitybox.sendKeys(quantity);
		saveBtn.click();
	}
	
	public void CreatePurchaseorder(WebDriver driver, String SubjectName,  String VendorName, String ContactName, String BillingAdress, String ShippingAdress, String item, String quantity) throws Throwable
	{
		SubjectTextField.sendKeys(SubjectName);		
		Vendorlookup.click();
		switchToWindow(driver, "Vendors");
		searchtestfield.sendKeys(VendorName);
		searchbutton.click();
		driver.findElement(By.xpath("//a[text()='"+VendorName+"']")).click();
		Thread.sleep(2000);
		switchToWindow(driver, "PurchaseOrder");
		
		Thread.sleep(2000);
		
		Contactlookup.click();
		switchToWindow(driver, "Contacts");
		searchtestfield.sendKeys(ContactName);
		searchbutton.click();
		
		
		driver.findElement(By.xpath("//a[contains(text(),'"+ContactName+"')]")).click();
		Thread.sleep(2000);
		acceptAlert(driver);
		Thread.sleep(2000);
		switchToWindow(driver, "PurchaseOrder");
		
		BillingAdressTextField.sendKeys(BillingAdress);
		ShippingTextField.sendKeys(ShippingAdress);
		Additemlookup.click();
		switchToWindow(driver, "Products");
		searchtestfield.sendKeys(item);
		searchbutton.click();
		driver.findElement(By.xpath("//a[text()='"+item+"']")).click();
		Thread.sleep(2000);
		switchToWindow(driver, "PurchaseOrder");
		Quantitybox.sendKeys(quantity);
		saveBtn.click();
	}
}
