package com.crm.Practice;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class calender1 {

	public static void main(String[] args) {
		
		//LocalDateTime localDateTime= LocalDateTime.now().plusYears(1);
		
		//int day= localDateTime.getDayOfMonth();
		
		//String month= localDateTime.getMonth().name();
		
		//int year= localDateTime.getYear();
		
		//month= month.substring(0, 1).toUpperCase()+month.substring(1, 3).toLowerCase();
		
		String month= "March";
		
		int day=25;
		
		int year = 2023;
		
		String expectedMonthyearString=month+" "+ year;
		
		WebDriverManager.chromedriver().setup();
		
		ChromeDriver driver= new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://www.goibibo.com/");
		
		driver.findElement(By.id("departureCalendar")).click();
		
		outerloop:
			
			for(;;) 
			{
				List<WebElement> monthyearslist = driver.findElements(By.className("DayPicker-Caption"));
				
		
				for(WebElement element : monthyearslist)
				{
					
					if(element.getText().equalsIgnoreCase(expectedMonthyearString)) 
					{
						
						driver.findElement(By.xpath("//div[text()='"+month+" "+ year+"']/../..//div[text()='"+day+"']")).click();
						
						
						
						break outerloop;
						
					}
					
				}
				
				driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();

	}
		
		driver.quit();
	}
	

}

