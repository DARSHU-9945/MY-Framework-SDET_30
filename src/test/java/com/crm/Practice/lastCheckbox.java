package com.crm.Practice;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.ObjectRepository.HomePage;

public class lastCheckbox extends BaseClass {
	
	@Test
	
	public void clicklastcheckbox() {
		
		HomePage hPage= new HomePage(driver);
		hPage.ClickOnContactLink();
		
		WebElement elements = driver.findElement(By.xpath("//table/tbody/tr[*]/td/input[@id='64']"));
		wLib.scrollAction(driver);
		elements.click();
		
		
	}
	
	
	
	

}
