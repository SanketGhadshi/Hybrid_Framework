package com.hybrid.PageObjects;

import org.openqa.selenium.Alert;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	public WebDriver ldriver;
	public static WebDriver driver;
	
	public LoginPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver,this);
	}
	
	@FindBy(name="uid")
	WebElement txtUserName;
	
	@FindBy(name="password")
	WebElement txtPassWord;
	
	@FindBy(name="btnLogin")
	@CacheLookup
	WebElement btnLogin;
	
	@FindBy(xpath="//a[text()='Log out']")
	@CacheLookup
	WebElement lnkLogout;
	
	public void setUsername(String uname) 
	{
		txtUserName.sendKeys(uname);
		
	}
	
	public void setPassword(String pwd) 
	{
		txtPassWord.sendKeys(pwd);
		
	}
	
	public void clickSubmit() 
	{
		btnLogin.click();
		
	}	
	
	public void clickLogout() 
	{
		lnkLogout.click();
		
	}	
	
}
