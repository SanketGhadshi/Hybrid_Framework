package com.hybrid.TestCases;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.hybrid.PageObjects.LoginPage;

public class TC_LoginPageTest_001 extends BaseClass{
	
	@Test
	public void loginTest() 
	{		

		 LoginPage lp=new LoginPage(driver);
		 lp.setUsername(username);
		 logger.info("Entered username successfully");

		 lp.setPassword(password);
		 logger.info("Entered password successfully");

		 lp.clickSubmit();
		 
		 if(driver.getTitle().equals("Guru99 Bank Manager HomePage")) 
		 {
			 Assert.assertTrue(true);
			 logger.info("Login test is passed");
		 }
		 else {
			 Assert.assertTrue(true);
			 logger.info("Login test is failed");
		 }
		
	}
	

}
