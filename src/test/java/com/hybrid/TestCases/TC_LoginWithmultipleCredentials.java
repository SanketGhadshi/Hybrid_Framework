package com.hybrid.TestCases;

import org.testng.Assert
;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hybrid.PageObjects.LoginPage;
import com.hybrid.base.BaseClass;
import com.hybrid.utilities.DataProviderUtil;

public class TC_LoginWithmultipleCredentials extends BaseClass  {
	
	final String expectedHomepage ="GTPL Bank Manager HomePage";

	@Test(dataProvider="getData")
    public void loginTest(String username, String password) {
        
    	LoginPage lp=new LoginPage(driver);
		 
		 lp.dpEnterUsername(username);
		 logger.info("Username is entered");

		 lp.dpenterPassword(password);
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

    @DataProvider
    public Object[][] getData() 
    {
        return new Object[][] 
        		{
        	{"user1@example.com", "password1"},
            {"user2@example.com", "password2"},
            {"user3@example.com", "password3"},
            {"mngr629663","Ehyrudu"}        
                };
    }
}
