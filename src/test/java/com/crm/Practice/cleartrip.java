package com.crm.Practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class cleartrip {
	
	@Test
	
	public void cleartrip() {
		
		String dateString="20";
		
		String monthyearString= "Aug 2022";
		
		WebDriver driver= new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get("https://www.cleartrip.com/");
		
		driver.findElement(By.xpath("//div[@class='flex flex-middle flex-between p-relative homeCalender']")).click();
		
		//for(;;) {
			
		//	try {
				
		//		driver.findElement(By.xpath("//div[text()='"+monthyearString+"']/ancestor::div[@class='DayPicker-Month']/descendant::div[text()='"+dateString+"']")).click();
			//	break;
		//	}
			
			//catch (Exception e) {
				
				//driver.findElement(By.id("img2Nex")).click();
				
				
			//}
			
		//}
	//}

		
		
	}

}
