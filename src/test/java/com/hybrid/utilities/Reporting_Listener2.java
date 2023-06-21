package com.hybrid.utilities;

//Listener class used to generate external reports

import java.io.File;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.uncommons.reportng.HTMLReporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.hybrid.TestCases.BaseClass;


public class Reporting_Listener2 extends BaseClass implements ITestListener{
	

	public ExtentTest logger;     // what details should be populated in the report
	public ExtentReports extent = new ExtentReports(); //specify location
	ExtentSparkReporter spark;
	
	public void onTestStart(ITestResult result) 
	{	    
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time stamp
		String repName ="Test-Report-"+timeStamp+".html";
		System.out.println(repName);
		spark = new ExtentSparkReporter("//home//base/git//Hybrid_Framework//Reports//"+repName);
		
		extent.attachReporter(spark);
				
		spark.config().setDocumentTitle("Automation Report"); //Title of the report
		spark.config().setReportName("Funtional Report"); //Name of report
		spark.config().setTheme(Theme.DARK);
		
		extent.setSystemInfo("Hostname","LoacalHost");
		extent.setSystemInfo("OS","Linux");
		extent.setSystemInfo("Tester Name","Sanket");
		extent.setSystemInfo("Browser Name","Chrome");
		
		extent.flush();
		
		extent.flush();
	}
	
	public void onTestSuccess(ITestResult result) 
	{
		logger = extent.createTest(result.getName()); // create new entry in the report
		logger.log(Status.PASS,MarkupHelper.createLabel(result.getName(),ExtentColor.GREEN)); //send the passed information
	    
		extent.flush();
	}
	
	public void onTestFailure(ITestResult result){
		 
		  if (!result.isSuccess()) {
		   String userDirector = System.getProperty("user.dir");
		   System.out.println("User Directory Path---->"+userDirector);
		   String customeLocation = "\\Screenshot\\";
		   System.out.println("Custom Path--->"+customeLocation);
		   String failureImageFileName = userDirector+customeLocation+new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime())+"-"+result.getMethod().getMethodName()+ ".png";
		   File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		   try {
		    FileUtils.copyFile(scrFile, new File(failureImageFileName));
		   } catch (IOException e) {
		   e.printStackTrace();
	   }
   }
  }
	
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
		logger = extent.createTest(result.getName()); // create new entry in the report
		logger.log(Status.SKIP,MarkupHelper.createLabel(result.getName(),ExtentColor.ORANGE)); //send the skipped information
		
		extent.flush();
	}
	
	public void onTestFinish(ITestContext testContext) 
	{
		extent.flush();
	}

	
}
