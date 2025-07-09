package com.hybrid.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.github.dockerjava.api.model.Driver;
import com.hybrid.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
		
	ReadConfig readconfig = new ReadConfig();
	
		public String baseURL=readconfig.getApplicationURL();
		public String username=readconfig.getUsername();
		public String password=readconfig.getPassword();
		public static WebDriver driver;
		public static Logger logger; //import org.apache.log4j
		
		@Parameters("browser")
		@BeforeClass
		public void BrowSetup(String br) 	{
			
			logger = logger.getLogger("Hybrid_Framework");
			PropertyConfigurator.configure("log4j.properties");
//			DOMConfigurator.configure("Log4l.xml");
			
			if(br.equals("chrome")) {
				WebDriverManager.chromedriver().clearDriverCache().setup();
				ChromeOptions cOptions = new ChromeOptions();
				
				
				cOptions.addArguments("--remote-allow-origins=*");
			    Map<String, Object> prefs = new HashMap<String, Object>();
			    prefs.put("credentials_enable_service", false);
			    prefs.put("profile.password_manager_enabled", false);
			    cOptions.setExperimentalOption("prefs", prefs);
			    cOptions.addArguments("disable-infobars");
			    cOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
			    //cOptions.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");   
			 
			    cOptions.addArguments("test-type");
			    cOptions.addArguments("start-maximized");
			    cOptions.addArguments("--js-flags=--expose-gc");
			    cOptions.addArguments("--enable-precise-memory-info");
			    cOptions.addArguments("--disable-popup-blocking");
			    cOptions.addArguments("--disable-default-apps");
			    cOptions.addArguments("test-type=browser");    
			    cOptions.addArguments("disable-infobars");

			    driver = new ChromeDriver(cOptions);
			    driver.manage().window().maximize();
			}
			
			else if(br.equals("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
			}
			
			else if(br.equals("ie"))
			{
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
			}
			
			driver.get(baseURL);
			logger.info("URL is opened");

			System.out.println("...Browser opened sucessfully...");
			
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.get(baseURL);

		}
		
		
		
		@AfterClass
		public void tearDown()
		{
			driver.quit();
		}
		
		 public void captureScreen(WebDriver driver,String tname) throws IOException{
			  
			 TakesScreenshot ts = (TakesScreenshot) driver ;
			 File source = ts.getScreenshotAs(OutputType.FILE);
			 File target = new File(System.getProperty("user.dir")+ "/screenshot/" + tname + ".png");
			 FileUtils.copyFile(source, target);
			 System.out.println("Screenshot taken");
			
		 }

		
//		 public void captureScreen() throws IOException{
//			  
//			  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//			  String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()); 
////			  File screenshotName = new File ("E:\\Vinayak\\screenshot\\"+System.currentTimeMillis()+"_"+".png");
//			  File screenshotName = new File ("C:\\Users\\Sanket Ghadshi\\Git\\Hybrid_Framework\\screenshot\\"+timeStamp+"_"+".png");
//			  FileUtils.copyFile(scrFile, screenshotName);
//			  Reporter.log("<br><img src='"+screenshotName+"' height='300' width='300'/><br>"); 
//			
//		 }
		 
		 public String randomestring()
			{
				String generatedstring = RandomStringUtils.randomAlphabetic(8);
				return(generatedstring);
			}

			public static String randomeNum()
			{
				String generatedstring2 = RandomStringUtils.randomNumeric(4);
				return(generatedstring2);
			}
	}
	
