package com.crm.Practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import net.bytebuddy.dynamic.scaffold.InstrumentedType.Frozen;

public class easymytrip {
	
	@Test
	
	public void easemytrip() {
		
		String dateString="20";
		
		String monthyearString= "Aug 2022";
		
		WebDriver driver= new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get("https://www.easemytrip.com/");
		
		driver.findElement(By.xpath("//input[@placeholder='Departure']")).click();
		
		for(;;) {
			
			try {
				
				driver.findElement(By.xpath("//div[text()='"+monthyearString+"']/ancestor::div[@class='box']/descendant::li[text()='"+dateString+"']")).click();
				break;
			}
			
			catch (Exception e) {
				
				driver.findElement(By.id("img2Nex")).click();
				
				
			}
			
		}
	}

}
