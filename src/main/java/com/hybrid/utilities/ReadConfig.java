package com.hybrid.utilities;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.util.Properties;

public class ReadConfig {

	Properties properties;

//	String path = "C:\\Users\\Sanket Ghadshi\\Git\\Hybrid_Framework\\Configuration\\config.properties";

	//constructor
	public ReadConfig() {
		
		File src= new File("./Configuration/config.properties");
		
		try 
		{
			properties = new Properties();
			FileInputStream  fis = new FileInputStream(src);
			properties.load(fis);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new RuntimeException("ReadConfig class failed : ", e);
		}


	}
	public String getBaseUrl()
	{
		String url = properties.getProperty("baseUrl");

		if(url!=null) {
			return url;
		}
		else
		{
			throw new RuntimeException("URL not specified in config file.");
		}

	}

	public String getBrowser()
	{
		String value = properties.getProperty("browser");

		if(value!=null)
			return value;
		else
			throw new RuntimeException("URL not specified in config file.");

	}
	
	public String getUsername()
	{
		String username = properties.getProperty("username");
		if(username!=null)
			return username;
		else
			throw new RuntimeException("Username not specified in config file.");
		
	}

	public String getPassword()
	{
		String password = properties.getProperty("password");
		if(password!=null)
			return password;
		else
			throw new RuntimeException("Password not specified in config file.");
		
	}

}