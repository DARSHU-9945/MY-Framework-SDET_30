package com.crm.Practice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.ObjectRepository.ContactsPage;
import com.crm.ObjectRepository.HomePage;

public class Printlastname extends BaseClass{
	
	@Test
	public void Printlastname() {
		
		HomePage hPage= new HomePage(driver);
		hPage.ClickOnContactLink();
		
		List<WebElement> elements = driver.findElements(By.xpath("//table/tbody/tr[*]/td/a[@title='Contacts']"));
		
		ArrayList<WebElement> eles = new ArrayList<WebElement>(elements);
		for(WebElement element: eles) {
			String nameString= element.getText();
			System.out.println(nameString);
		}	
		
	}
}
	


