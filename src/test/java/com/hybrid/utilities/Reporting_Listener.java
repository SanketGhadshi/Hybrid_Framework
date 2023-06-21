package com.hybrid.utilities;

//Listener class used to generate external reports

import java.io.File;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
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


public class Reporting_Listener extends TestListenerAdapter {
	
//	public ExtentHtmlReporter htmlreporter;
//	public ExtentReports extent;  //specify the location of reports
	public ExtentTest logger;     // what details should be populated in the report
	
	public ExtentReports extent = new ExtentReports(); //specify location
    ExtentSparkReporter spark;

	
	public void onTestStart(ITestContext testContext) 
	{	    
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time stamp
		String repName ="Test-Report-"+timeStamp+".html";
		System.out.println(repName);
		spark = new ExtentSparkReporter("//home//base/git//Hybrid_Framework//Reports//"+repName);
		
        extent.attachReporter(spark);
		
        extent.createTest("ParentWithChild")
        .createNode("Child")
        .pass("This test is created as a toggle as part of a child test of 'ParentWithChild'");
        
		extent.setSystemInfo("Hostname","LoacalHost");
		extent.setSystemInfo("OS","Windows11");
		extent.setSystemInfo("Tester Name","Sanket");
		extent.setSystemInfo("Browser Name","Chrome");
		
		spark.config().setDocumentTitle("Hybrid Framework Project"); //Title of the report
		spark.config().setReportName("Funtional Test Report"); //Name of report
//		htmlreporter.config().setTestViewChartLocation(chartLocation.TOP); // location of chart
		spark.config().setTheme(Theme.DARK);
		
		extent.flush();
	}
	
	public void onTestSuccess(ITestResult tr) 
	{
		logger = extent.createTest(tr.getName()); // create new entry in the report
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN)); //send the passed information

		extent.flush();
	}
	
	public void onTestFailure(ITestResult tr) 
	{
		logger = extent.createTest(tr.getName()); // create new entry in the report
		logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED)); //send the failed information
		
		String screenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		
		File f = new File(screenshotPath);
		
		if(f.exists()) 
		{
			try {
				logger.fail("Screenshot is below :"+logger.addScreenCaptureFromPath(screenshotPath));
			}
			catch (Exception e) {
				System.out.println("Please check screen shot method");
			}
		}
		
		extent.flush();
	
	}
	
	public void onTestSkipped(ITestResult tr) 
	{
		logger = extent.createTest(tr.getName()); // create new entry in the report
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE)); //send the skipped information
		
		extent.flush();
	}
	
	public void onTestFinish(ITestContext testContext) 
	{
		extent.flush();
	}

	
}
