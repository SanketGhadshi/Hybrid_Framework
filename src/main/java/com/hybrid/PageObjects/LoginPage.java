package com.hybrid.PageObjects;

import org.openqa.selenium.Alert;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hybrid.utilities.CommonMethods;
import com.hybrid.utilities.ReadConfig;

public class LoginPage {

	// Instance variable to store driver reference passed to constructor
    public WebDriver ldriver;

    // Static driver variable (not recommended for parallel execution)
    public static WebDriver driver;

    // Constructor that takes a WebDriver object and initializes elements
    public LoginPage(WebDriver rdriver)
    {
        // Assigning local driver to the instance variable
    	// Saves the driver so we can use it in this page class	
    	// You need driver to interact with browser
        ldriver = rdriver;

        // Initializing all WebElements declared with @FindBy in this class
        // Finds all the @FindBy elements and makes them ready to use	
        // Otherwise your elements will stay null (empty), and your test will crash
        PageFactory.initElements(rdriver, this);
    }

    // Locate the username input field using the name attribute
    @FindBy(name = "uid23")
    WebElement ele_UserName;
    
    @FindBy(name = "uid")
    WebElement UserNameBankProject;

    // Locate the password input field using the name attribute
    @FindBy(name = "password")
    WebElement txtPassWord;

    // Locate the login button and cache it after first use for performance
    @FindBy(name = "btnLogin")
    @CacheLookup
    WebElement LoginBTN;

    // Locate the logout link using its visible text
    @FindBy(xpath = "//a[text()='Log out']")
    @CacheLookup
    WebElement lnkLogout;
    
    ReadConfig rc = new ReadConfig();
    
    // Method to input username into the username field
    public void setUsername(String uname)
    {
//      txtUserName.sendKeys(uname); // Types the given username
    	CommonMethods.enterText(ele_UserName, uname);
    }

    // Method to input password into the password field
    public void setPassword(String pwd)
    {
        txtPassWord.sendKeys(pwd); // Types the given password
    }

    // Method to click on the login button
    public void clickSubmit()
    {
    	LoginBTN.click(); // Clicks the login button
    }

    // Method to click on the logout link
    public void clickLogout()
    {
        lnkLogout.click(); // Clicks the logout link
    }
    
    public void enterTextOnUsernameBankProject() {
    	CommonMethods.enterText(UserNameBankProject, rc.getUsername());
    }
    
    public void enterTextOnPasswordBankProject() {
    	CommonMethods.enterText(txtPassWord, rc.getPassword());
    }
    
    public void enterTextOnPasswordBankProject1() {
    	CommonMethods.enterText(LoginBTN, rc.getPassword());
    }
    
    public void clickOnLoginButton() {
    	CommonMethods.clickElement(ldriver, LoginBTN);
    }
    
//  Data Provider
    public void dpEnterUsername(String username) {
    	UserNameBankProject.click();
    	UserNameBankProject.clear();
    	UserNameBankProject.sendKeys(username);
    }

    public void dpenterPassword(String pass_word) {
    	txtPassWord.click();
    	txtPassWord.clear();
    	txtPassWord.sendKeys(pass_word);
    }

}