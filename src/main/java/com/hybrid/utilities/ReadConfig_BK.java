package com.hybrid.utilities;

import java.io.File;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig_BK {

	Properties pro;
	
	public ReadConfig_BK() 
	{
		File src= new File("./Configuration/config.properties");
		
		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		}
		catch(Exception e)
		{
			System.out.println("Exception is : " + e.getMessage());
			throw new RuntimeException("ReadConfig class failed : ", e);
		}
	}
	
	public String getApplicationURL() 
	{
		String url = pro.getProperty("baseURL");
		return url;	
	}
	
	public String getUsername() 
	{
		String username = pro.getProperty("username");
		return username;	
	}
	
	public String getPassword() 
	{
		String password = pro.getProperty("password");
		return password;	
	}
	
}
