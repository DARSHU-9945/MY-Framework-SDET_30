package com.crm.GenericLibrary;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementationClass implements ITestListener 
{
	ExtentReports report;
	ExtentTest test;

		public void onTestStart(ITestResult result) {
			
			String MethodName = result.getMethod().getMethodName();
			//Reporter.log(MethodName + "--- testscript execution started");
			test=report.createTest(MethodName);
			
		}

		public void onTestSuccess(ITestResult result) {
			
			String MethodName = result.getMethod().getMethodName();
			//Reporter.log(MethodName + "--- testscript execution sucessfull - PASS");
			test.log(Status.PASS, MethodName+"--------->Passed");
			
		}

		public void onTestFailure(ITestResult result) {
			
			String pathString=null;
			
			String MethodName = result.getMethod().getMethodName()+"-";
			//Reporter.log(MethodName + "--- TestScript Failed",true);
			
			//Step 1: Configure screenshot name
			String screenshotName = MethodName+new javaUtility().getSystemDateInFormat();
			System.out.println(screenshotName);
			
			//Step 2: using screenshot method from webDriver Utility
			try {
				
				pathString=new WebDriverUtility().getScreenShot(BaseClass.sDriver, screenshotName);
				
		
				//EventFiringWebDriver eDriver = new EventFiringWebDriver(BaseClass.sDriver);
				//File src = eDriver.getScreenshotAs(OutputType.FILE);
				//String pa = System.getProperty("user.dir")+"/ScreenShots/"+screenshotName+".PNG";
				//String path = "./Screenshots/"+screenshotName+".png";
				//File dst = new File(pa);
				//Files.copy(src, dst);
				
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			test.log(Status.FAIL, MethodName+"----------->failed");
			//it will capture the exception and log it in report
			test.log(Status.FAIL, result.getThrowable());
			test.addScreenCaptureFromPath(pathString);
		
		}

		public void onTestSkipped(ITestResult result) {
			
			String MethodName = result.getMethod().getMethodName();
			//Reporter.log(MethodName + "--- TestScript Skipped");
			test.log(Status.SKIP,MethodName+"-------------->Skipped" );
			//it will capture exception and log it in the report
			test.log(Status.SKIP, result.getThrowable());
			
		}

		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			
			
		}

		public void onTestFailedWithTimeout(ITestResult result) {
			
			
		}

		public void onStart(ITestContext context) {
			
			//execution will stsrt from here
			//*configure the report*//
			ExtentSparkReporter htmlReport = new ExtentSparkReporter("./ExtentReports/Report"+new javaUtility().getSystemDateInFormat()+".html");
			htmlReport.config().setDocumentTitle("SDET-30-Extent Reports");
			htmlReport.config().setTheme(Theme.DARK);
			htmlReport.config().setReportName("selenium report");
			
			report=new ExtentReports();
			report.attachReporter(htmlReport);
			report.setSystemInfo("Base-Browser","chrome");
			report.setSystemInfo("OS", "windows");
			report.setSystemInfo("Base-Url", "LocalHost:8888");
			report.setSystemInfo("Reporter-Name", "BOSS");
			
			
		}

		public void onFinish(ITestContext context) {
			
			//consolidate all parameters and generte the report
			report.flush();
			
			
		}
	}
