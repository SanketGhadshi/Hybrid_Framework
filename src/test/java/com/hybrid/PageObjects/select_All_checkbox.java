package com.hybrid.PageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class select_All_checkbox {
	
    public static WebDriver driver;
	
	@Test(priority = -1)
	  public void url() throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions cOptions = new ChromeOptions();
		cOptions.addArguments("--remote-allow-origins=*");
		
		driver = new ChromeDriver(cOptions);
	    driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://itera-qa.azurewebsites.net/home/automation");
	}  
	
	@Test(priority = 0)
	  public void select_all_checkbox() throws InterruptedException {
	 
		// xpath aslo write in this way---> //input[@class='form-check-input' and contains(@id,'day')]		
	  List<WebElement> daycheckbox = driver.findElements(By.xpath("//input[contains(@id,'day')]")); 
	  
	  System.out.println("Total No. of check boxes for days :"+daycheckbox.size());
	  
	  // Using for loop
	  for(int i=0;i<daycheckbox.size();i++) {
		daycheckbox.get(i).click();  
	  }
	  
	  Thread.sleep(5000);
	 
	  //Using for-each Loop
	  List<WebElement> autotoolcheckbox = driver.findElements(By.xpath("//div[@class='custom-control custom-checkbox']//label[@class='custom-control-label']")); 
	  
	  System.out.println("Total No. of check boxes for automation tool :"+autotoolcheckbox.size());
	  
	  for(WebElement autocheck:autotoolcheckbox) {
		  autocheck.click();
	  }
	  
	}

}



