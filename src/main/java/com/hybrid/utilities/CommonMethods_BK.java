package com.hybrid.utilities;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods_BK {

	public static void clickElement(WebElement element) {
	    try {
	        element.click();
	    } catch (Exception e) {
	        System.out.println("Unable to click element: " + e.getMessage());
	        throw e;
	    }
	}

	public static void enterText(WebElement element, String text) {
	    try {
	        element.clear();
	        element.sendKeys(text);
	    } catch (Exception e) {
	        System.out.println("Unable to enter text: " + e.getMessage());
	        throw e;
	    }
	}
    
    public static String getElementText(WebElement element) {
        return element.getText();
    }
    
    public static boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static void waitForVisibility(WebDriver driver, WebElement element, int time) {
        new WebDriverWait(driver, Duration.ofSeconds(time)).until(ExpectedConditions.visibilityOf(element));
    }

    public static void selectDropdown(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByVisibleText(value);
    }
    
    public static void selectOptionFromDropDown(WebElement ele,String value) {
		
		Select drp = new Select(ele);
		
		List<WebElement> allOptions =drp.getOptions();
		  
		  for(WebElement option:allOptions) {
			 
			  if(option.getText().equals(value)) {
				 
				  option.click();
				  break;
			  }
		  }
	}

    
}

