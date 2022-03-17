package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import okhttp3.internal.http2.Header;

public class OpportunitiesInfoPage {
	
	@FindBy(xpath ="//span[@class='dvHeaderText']")
	private WebElement HeaderText;

	public OpportunitiesInfoPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}

	public WebElement getHeaderText() {
		return HeaderText;
	}
	
	public String verificationOpportunityPage() {
		
		String Header = HeaderText.getText();
		System.out.println(Header);
		return Header;
		
	}
}
