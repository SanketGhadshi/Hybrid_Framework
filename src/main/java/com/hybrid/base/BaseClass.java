package com.hybrid.base;


import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.hybrid.utilities.ReadConfig;

public class BaseClass {
	
	ReadConfig readConfig = new ReadConfig();

	String url = readConfig.getBaseUrl();
	String browser = readConfig.getBrowser();

	public String username = readConfig.getUsername();
	String password = readConfig.getPassword();
	
	public static Logger logger;
//    public static final Logger logger  = LogManager.getLogger(BaseClass.class); 
    
	public WebDriver driver;
	                                                                                                 
	@BeforeClass
	public void setup() throws InterruptedException
	{
		
		//launch browser
		switch(browser.toLowerCase())
		{
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "msedge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			driver = null;
			break;

		}

		driver.manage().window().maximize();
		
		//implicit wait of 10 secs
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		
		logger = LogManager.getLogger("Hybrid_Framework");
		
		//open url
		Thread.sleep(5000);
		driver.get(url);
		logger.info("URL Opened Successfully : "+ url);

	}

	@AfterClass
	public void tearDown()
	{
		driver.close();
		driver.quit();
	}
	
		
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
			 Date d = new Date();
			 String date1 = d.toString();	
			 String date2 = date1.replaceAll(":", "_");
	        
			 String screenshotPath = System.getProperty("user.dir") + "//ScreenShots//"+ testCaseName + date2 + ".png";
	        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        File dest = new File(screenshotPath);
	        dest.getParentFile().mkdirs();

	        try 
	        {
	            FileHandler.copy(src, dest);
	        }
	        catch (IOException e) {	           
	        	e.printStackTrace();
	        }

	        return screenshotPath;
	    }
	
}
