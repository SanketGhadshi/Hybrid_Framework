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
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class Reporting_Listener extends TestListenerAdapter {
	
	public ExtentHtmlReporter htmlreporter;
	public ExtentReports extent;  //specify the location of reports
	public ExtentTest logger;     // what details should be populated in the report
		
	public void onTestStart(ITestContext testContext) 
	{	    
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time stamp
		String repName ="Test-Report-"+timeStamp+".html";
		htmlreporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/repName.html"); //specify location
		htmlreporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		
		extent = new ExtentReports();
		extent.attachReporter(htmlreporter);
		
		extent.setSystemInfo("Hostname","LoacalHost");
		extent.setSystemInfo("OS","Windows11");
		extent.setSystemInfo("Tester Name","Sanket");
		extent.setSystemInfo("Browser Name","Chrome");
		
		htmlreporter.config().setDocumentTitle("Hybrid Framework Project"); //Title of the report
		htmlreporter.config().setReportName("Funtional Test Report"); //Name of report
//		htmlreporter.config().setTestViewChartLocation(chartLocation.TOP); // location of chart
		htmlreporter.config().setTheme(Theme.DARK);
	}
	
	public void onTestSuccess(ITestResult tr) 
	{
		logger = extent.createTest(tr.getName()); // create new entry in the report
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN)); //send the passed information

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
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	}
	
	public void onTestSkipped(ITestResult tr) 
	{
		logger = extent.createTest(tr.getName()); // create new entry in the report
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE)); //send the skipped information
		
	}
	
	public void onTestFinish(ITestContext testContext) 
	{
		extent.flush();
	}

	
}
