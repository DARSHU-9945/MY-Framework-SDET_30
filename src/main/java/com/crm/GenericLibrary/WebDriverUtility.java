package com.crm.GenericLibrary;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

/**
 * this class consists of all generic methods related to webdriver actions
 * @author Deepthi
 *
 */

public class WebDriverUtility {
	
	/**
	 * this method will maxiize the window
	 * @param driver
	 */
	
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	/**
	 * this method will wait for 20 secomds for page to load
	 * @param driver
	 */
	public void waitForPageLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	/**
	 * this method will wait for 20 secomds for element to be clickable
	 * @param driver
	 * @param element
	 */
	
	public void waitForElementToBeClickable(WebDriver driver,WebElement element) {
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
	}
	
	/**
	 * this method will wait for 20sec to elemnt to be visible
	 * @param driver
	 * @param element
	 */
	
	public void waitForElementToBeVisible(WebDriver driver,WebElement element) {
		
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	
	/**
	 * this method will select the data from dropdown using index
	 * @param element
	 * @param index
	 */
	
	public void select(WebElement element, int index) {
		
		Select sel= new Select(element);
		sel.selectByIndex(index);
	}
	
	/**
	 * this method will select the data from dropdown using visible text
	 * @param element
	 * @param Text
	 */
	
	public void select(WebElement element, String Text) {
		
		Select sel= new Select(element);
		sel.selectByVisibleText(Text);
		
	}
	
	/**
	 * ths method will select data from dropdown using visiblevalue
	 * @param value
	 * @param element
	 */
	public void select(String value, WebElement element) {
		
		Select sel= new Select(element);
		sel.selectByValue(value);
		
	}
	
	/**
	 * this method will perform mouse hover action
	 * @param driver
	 * @param element
	 */
	
	public void mouseHover(WebDriver driver,WebElement element ) {
		
		Actions act= new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	/**
	 * this method will perform drag and drop actions
	 * @param driver
	 * @param src
	 * @param target
	 */
	
	public void dragAndDrop(WebDriver driver,WebElement src,WebElement target) {
		
		Actions act= new Actions(driver);
		act.dragAndDrop(src,target).perform();
	}
	
	/**
	 * this method will perfirm doubleclick actions on web element
	 * @param driver
	 * @param element
	 */
	
	public void doubleClickAction(WebDriver driver,WebElement element) {
		
		Actions act= new Actions(driver);
		act.doubleClick(element).perform();
	
	}
	
	/**
	 * this methd will perform double click on webpage
	 * @param driver
	 */
	
	public void doubleClickAction(WebDriver driver) {
		
		Actions act= new Actions(driver);
		act.doubleClick().perform();	
	
	}
	
	/**
	 * this method will perfrom right click on element
	 * @param driver
	 * @param element
	 */
	
	public void rightClick(WebDriver driver,WebElement element) {
		
		Actions act= new Actions(driver);
		act.contextClick(element).perform();	
	
	}
	
	/**
	 * this method will right click on webpage
	 * @param driver
	 */
	public void rightClick(WebDriver driver) {
		
		Actions act= new Actions(driver);
		act.contextClick().perform();	
	
	}
	/**
	 * this method will press enter key
	 * @param driver
	 */
	
	public void enterKeyPress(WebDriver driver) {
		
		Actions act= new Actions(driver);
		act.sendKeys(Keys.ENTER).perform();
	}
	
	/**
	 * this method will press enter key
	 * @throws AWTException
	 */
	
	public void enterKey() throws Throwable  {
		
		Robot rb= new Robot();
		rb.keyPress(KeyEvent.VK_ENTER);	
		
}
	/**
	 * this method will release the enter key
	 * @throws Throwable
	 */
	public void enterKeyRelease() throws Throwable  {
		
		Robot rb= new Robot();
		rb.keyRelease(KeyEvent.VK_ENTER);	
		
}
	/**
	 * this method will switch to frame based on index
	 * @param driver
	 * @param index
	 */
	
	public void switchToFrame(WebDriver driver, int index) {
		
		driver.switchTo().frame(index);
	}
	
	/**
	 * this method will switch to frame based on name or id
	 * @param driver
	 * @param nameorid
	 */
	
	public void switchToFrame(WebDriver driver, String nameorid) {
		
		driver.switchTo().frame(nameorid);
	}
	
	/**
	 * this method will switch to frame based on address of element
	 * @param driver
	 * @param address
	 */
	
	public void switchToFrame(WebDriver driver, WebElement address) {
		
		driver.switchTo().frame(address);
	}
	
	/**
	 * this method will accept the alert popup
	 * @param driver
	 */
	
	public void acceptAlert(WebDriver driver) {
		
		driver.switchTo().alert().accept();
	}
	
	/**
	 * this method will cancel the alert popup
	 * @param driver
	 */
	
	public void cancelAlert(WebDriver driver) {
		
		driver.switchTo().alert().dismiss();
	}
	/**
	 * this method will switch to window deppends on partial window title
	 * @param driver
	 * @param partialWindowTitle
	 */
	
	public void switchToWindow(WebDriver driver, String partialWindowTitle) {
		
		//step-1 : use getwindowhandles to capture all window ids
		Set<String> windows =driver.getWindowHandles();
		
		// step-2 : iterate through windows
		Iterator<String> it = windows.iterator();
		
		//step-3 : check whether there is next window
		while(it.hasNext()){
			
			
			// step-4 : capture current window id
			String winId = it.next();
			
			//step-5 : switch to current window and capturethe title
			String cuurentWinTitle = driver.switchTo().window(winId).getTitle();
			
			//step-6 : check wheteher current window is expected
			if(cuurentWinTitle.contains(partialWindowTitle)) {
				break;
			}
		}
	}
	
/**
 * this method will take screenshot and store it in folder called screenshot
 * @param driver
 * @param ScreenShotname
 * @return 
 * @throws Throwable
 */
	
	public String getScreenShot(WebDriver driver, String ScreenShotname) throws Throwable {
		
		TakesScreenshot ts=(TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path = ".\\ScreenShot\\"+ScreenShotname+".png";
		File dst = new File(path);
		Files.copy(src, dst);
		return path;
	}
	
	/**
	 * this method will perform random scroll
	 * @param driver
	 */
	
	public void scrollAction(WebDriver driver) {
		
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)", "");
	}
	
	/**
	 * this method will scroll until specified element is found
	 * @param driver
	 * @param element
	 */
	
	public void scrollAction(WebDriver driver, WebElement element) {
		
		JavascriptExecutor js=(JavascriptExecutor) driver;
		int y = element.getLocation().getY();
		js.executeScript("window.scrollBy(0,"+y+")", element);
		
		//js.executescript("arguments[0].scrollIntoView()",element);
	
	}
	
	
}
