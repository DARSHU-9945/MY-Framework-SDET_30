package com.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CamapaignInfoPage {
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement CampaignHeaderText;
	
	public CamapaignInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getCampaignHeaderText() {
		return CampaignHeaderText;
	}
	
	
	//Business library
	public String CampaignHeaderTextInfo()
	{
		String Header = CampaignHeaderText.getText();
		System.out.println(Header);
		return Header;
	}
	
	

}
