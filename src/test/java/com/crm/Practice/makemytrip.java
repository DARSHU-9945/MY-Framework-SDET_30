package com.crm.Practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class makemytrip {
	
	@Test
	
	public void Calenderhandle() {
		
		String dateString="20";
		
		String monthyearString= "January 2023";
		
		WebDriver driver= new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get("https://www.makemytrip.com/");
		
		Actions actions= new Actions(driver);
		
		actions.moveByOffset(10, 20).click().perform();
		
		driver.findElement(By.xpath("//span[text()='DEPARTURE']")).click();
		
		for(;;) {
		
		try {
			
			driver.findElement(By.xpath("//div[text()='"+monthyearString+"']/ancestor::div[@class='DayPicker-Month']//p[text()='"+dateString+"']")).click();
			break;
		}
		
		catch (Exception e) {
		
			driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
		}
		
		
		
	}

}
}
