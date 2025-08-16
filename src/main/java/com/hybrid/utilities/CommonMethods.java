package com.hybrid.utilities;

import java.time.Duration;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods {

	/**
     * Clicks an element using WebDriverWait. Falls back to JS click if standard click fails.
     */
    public static void clickElement(WebDriver driver, WebElement element) 
    {
        try 
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) 
        {
            System.err.println("Standard click failed, trying JS click: " + e.getMessage());
            try 
            {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", element);
            } 
            catch (Exception jsEx) 
            {
                System.err.println("JS click also failed: " + jsEx.getMessage());
                throw jsEx;
            }
        }
    }

    /**
     * Checks if an element is clickable within a timeout period.
     */
    public static boolean isElementClickable(WebDriver driver, WebElement element, int timeoutInSeconds) 
    {
        try 
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } 
        catch (Exception e) 
        {
            System.err.println("Element is not clickable: " + e.getMessage());
            return false;
        }
    }

    /**
     * Enters text into a text input field.
     */
    public static void enterText(WebElement element, String text) 
    {
        try 
        {
            element.clear();
            element.sendKeys(text);
        } 
        catch (Exception e) 
        {
            System.err.println("Unable to enter text: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Returns the visible text of an element.
     */
    public static String getElementText(WebElement element) 
    {
        try 
        {
            return element.getText();
        } 
        catch (Exception e) 
        {
            System.err.println("Unable to get text: " + e.getMessage());
            return "";
        }
    }

    /**
     * Checks if an element is displayed.
     */
    public static boolean isElementDisplayed(WebElement element) 
    {
        try 
        {
            return element.isDisplayed();
        } 
        catch (Exception e) 
        {
            return false;
        }
    }

    /**
     * Waits for the visibility of an element for the given duration.
     */
    public static void waitForVisibility(WebDriver driver, WebElement element, int timeInSeconds) 
    {
        try 
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) 
        {
            System.err.println("Element not visible after waiting: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Selects a value from dropdown by visible text.
     */
    public static void selectDropdownByVisibleText(WebElement element, String value) 
    {
        try 
        {
            Select select = new Select(element);
            select.selectByVisibleText(value);
        } catch (Exception e) 
        {
            System.err.println("Unable to select dropdown value: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Selects a value from dropdown manually by iterating through options.
     */
    public static void selectOptionFromDropdown(WebElement element, String value) 
    {
        try 
        {
            Select dropdown = new Select(element);
            List<WebElement> options = dropdown.getOptions();
            for (WebElement option : options) {
                if (option.getText().trim().equalsIgnoreCase(value.trim())) {
                    option.click();
                    return;
                }
            }
            System.err.println("Option '" + value + "' not found in dropdown.");
        } 
        catch (Exception e) 
        {
            System.err.println("Failed to select dropdown option: " + e.getMessage());
            throw e;
        }
    }
}