package com.hybrid.TestCases;

import static org.testng.Assert.assertTrue;


import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.hybrid.PageObjects.LoginPage;
import com.hybrid.base.BaseClass;

public class TC_LoginPageBankProject extends BaseClass{
	
	final String expectedHomepage ="GTPL Bank Manager HomePage";
	
	@Test
	public void loginTestWithValidCredentials() throws InterruptedException, IOException
	{		

		 LoginPage lp=new LoginPage(driver);
		 
		 lp.enterTextOnUsernameBankProject();;
		 logger.info("Username is entered");

		 lp.setPassword(password);
		 logger.info("Password is entered");

		 lp.clickOnLoginButton();
		 
		 String actualHomepage = driver.getTitle();
		 logger.info("ActualHomepage title is : "+ actualHomepage);
		 
		 try {
			 
			 Assert.assertEquals(actualHomepage, expectedHomepage, "Home page match!");
			 logger.info("User is successfully logged in : "+ actualHomepage);
		 }
		 catch(Exception e) {
			 logger.info("Bank Project Homepage is different please check : "+e);
			 throw e;
		 }
		 
		
	}
	

}
