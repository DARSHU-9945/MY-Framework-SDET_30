package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class HomePage extends WebDriverUtility{
	
	//step 1: declaration
	@FindBy(linkText="Organizations")
	private WebElement organizationlink;
	
	@FindBy(linkText="Contacts")
	private WebElement Contactslink;
	
	
	@FindBy(linkText="Opportunities")
	private WebElement Opportunitieslink;
	
	@FindBy(linkText="Products")
	private WebElement Productslink;
	
	@FindBy(linkText="More")
	private WebElement Morelink;
	
	@FindBy(linkText="Campaigns")
	private WebElement Campaignslink;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement administartorImg;
	
	@FindBy(name="Purchase Order")
	private WebElement PurchaseorderLinkText;
	
	@FindBy(name="Vendors")
	private WebElement VendorsLinkText;
	
	@FindBy(linkText="Sign Out")
	private WebElement SignOutlink;
	
	//step-2 : initialization
	
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	
	//step-3: generate getters
	
	public WebElement getOrganizationlink() {
		return organizationlink;
	}

	public WebElement getContactslink() {
		return Contactslink;
	}
	

	public WebElement getCampaignslink() {
		return Campaignslink;
	}


	public WebElement getVendorsLinkText() {
		return VendorsLinkText;
	}


	public WebElement getOpportunitieslink() {
		return Opportunitieslink;
	}

	public WebElement getProductslink() {
		return Productslink;
	}

	public WebElement getMorelink() {
		return Morelink;
	}

	public WebElement getCamapaignslink() {
		return Campaignslink;
	}

	public WebElement getAdministartorImg() {
		return administartorImg;
	}

	public WebElement getPurchaseorderLinkText() {
		return PurchaseorderLinkText;
	}


	public WebElement getSignOutlink() {
		return SignOutlink;
	}
	
	// Business library
	
	public void ClickOnOrgLink()
	{
		organizationlink.click();
	}
	
	public void ClickOnContactLink() 
	{
		Contactslink.click();
	}
	
	public void ClickOnProductsLink() 
	{
		Productslink.click();
	}
	
	public void ClickOnMOreLink() 
	{
		Morelink.click();
	}
	
	public void ClickcampaignLink() 
	{
		Campaignslink.click();
	}
	
	public void ClickPurchaseorderlinktext()
	{
		
		PurchaseorderLinkText.click();
		
	}
	
	public void VendorLinkText() {
		VendorsLinkText.click();
	}
	
	public void OpportunitiesLink() {
		Opportunitieslink.click();
	}
	
	public void signOutOfApp(WebDriver driver)
	{
		mouseHover(driver, administartorImg);
		SignOutlink.click();
	}
	
	
	

	
	
	
	
	
	
}
