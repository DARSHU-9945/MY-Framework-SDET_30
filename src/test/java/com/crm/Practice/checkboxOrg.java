package com.crm.Practice;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.ObjectRepository.HomePage;

public class checkboxOrg extends BaseClass {
	
	@Test
	
	public void checkboxOrg() {
		
		HomePage hPage=new HomePage(driver);
		hPage.ClickOnOrgLink();
		
List<WebElement> elements = driver.findElements(By.xpath("//table/tbody/tr[*]/td/input[@name='selected_id']"));
		
		ArrayList<WebElement> eles = new ArrayList<WebElement>(elements);
		for(WebElement element: eles) {
			element.click();
		}	
		
	}

}
