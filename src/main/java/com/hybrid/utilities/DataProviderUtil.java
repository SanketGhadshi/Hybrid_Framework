package com.hybrid.utilities;

import org.testng.annotations.DataProvider;

public class DataProviderUtil {
	
	@DataProvider(name = "loginData")
    public Object[][] getLoginData() 
    {
        return new Object[][] 
        {
        	{"NDSBI123", "passwd", true},
            {"user", "wrongpass", false},
//            {"", "", false}
        	
        };
  }
}
