package com.hybrid.TestCases;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.hybrid.PageObjects.AddCustomerPage;

import com.hybrid.PageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass
{
	public void addNewCustomer() throws InterruptedException, IOException {
		
//		Login Page functinality
		LoginPage lp = new LoginPage(driver) ;
		lp.setUsername(username);
		logger.info("Username is Provided");
		lp.setPassword(password);
		logger.info("Password is Provided");
		lp.clickSubmit();
		
		Thread.sleep(3000);
		
//		Add New Customer Funtionality
		AddCustomerPage addcust = new AddCustomerPage(driver) ;
		
//		Click on add new cutomer link
		addcust.clickAddNewCustomer();
		
		logger.info("Providing customer details");
		
//		Enter the details
		addcust.custName("Sanket");
		addcust.custgender("male");
		addcust.custdob("10", "02", "1986");
		Thread.sleep(3000);
		addcust.custaddress("INDIA");
		addcust.custcity("Mumbai");
		addcust.custstate("MH");
		addcust.custpinno("400305");
		addcust.custtelephoneno("0987654321");
		
		String email = randomestring()+"@gmail.com";
		addcust.custemailid(email);
		addcust.custpassword("abcdef");
		addcust.custsubmit();
		
		Thread.sleep(3000);
		
		logger.info("Validation is started.............");
		
		boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");
		
		if(res==true)
		{
			Assert.assertTrue(true);
			logger.info("Test case is passed.......");
		}
		else
		{
			logger.info("Test case is failed.......");
			captureScreen(driver,"addNewCustomer");
			Assert.assertTrue(false);
		}
			
	}
	
	
	
}
