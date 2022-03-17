package com.crm.Practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.ObjectRepository.HomePage;
import com.crm.ObjectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class POMPracticeForLogin {
	
	@Test
	
	public void pomPractice()
	{
		WebDriverManager.chromedriver();
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://localhost:8888");
		
		LoginPage lp= new LoginPage(driver);
		lp.loginToApp("admin", "admin");
		
		HomePage hp= new HomePage(driver);
		hp.ClickOnContactLink();
		hp.signOutOfApp(driver);
	}

}
