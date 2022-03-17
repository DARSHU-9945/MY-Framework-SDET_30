package com.crm.Practice;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.ObjectRepository.HomePage;

public class checkbox5dlelte extends BaseClass {
	
	@Test
	 public void delete5checkobx() {
		
		HomePage hPage= new HomePage(driver);
		hPage.ClickOnContactLink();
		
		
	List<WebElement> elements = driver.findElements(By.xpath("//table/tbody/tr[*]/td/input[@name='selected_id']"));
	
	ArrayList<WebElement> eles = new ArrayList<WebElement>(elements);
	elements.get(5).click();
	driver.findElement(By.xpath("//table/tbody/tr[7]/td/a[.='del']")).click();
	wLib.acceptAlert(driver);
	}	

}
