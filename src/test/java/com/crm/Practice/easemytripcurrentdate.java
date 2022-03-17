package com.crm.Practice;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class easemytripcurrentdate {
	@Test
	
	public void currentdate() {
		
		
		Date d= new Date();
		String d1 = d.toString();
		String[] date = d1.split(" ");
		
		String mon= date[1];
		String day= date[2];
		//String time= date[3].replace(":", "-");
		String year=date[5];
		
		System.out.println(mon);
		System.out.println(day);
		System.out.println(year);
		
		String DateFormat= mon+" "+year;
		

		WebDriver driver= new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get("https://www.easemytrip.com/");
		
		driver.findElement(By.xpath("//input[@placeholder='Departure']")).click();
		
		driver.findElement(By.xpath("//div[text()='"+DateFormat+"']/ancestor::div[@class='box']/descendant::li[text()='"+day+"']")).click();
		
		
			
		
	}

}
