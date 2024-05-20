package com.hybrid.PageObjects;

import org.apache.velocity.runtime.parser.node.ASTSubtractNode;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {

	WebDriver ldriver;
	
	public AddCustomerPage(WebDriver rdriver) 
	{
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(how = How.XPATH, using="//a[text()='New Customer']")
	@CacheLookup
	WebElement lnkAddNewCutomerName;
	
	@FindBy(how = How.XPATH, using="//span[text()='Close']")
	@CacheLookup
	WebElement closeAdd;
	
	@FindBy(how = How.NAME, using="name")
	@CacheLookup
	WebElement txtCutomerName;
	
	@FindBy(how = How.NAME, using="rad1")
	@CacheLookup
	WebElement rdGender;
	
	@CacheLookup
	@FindBy(how = How.ID_OR_NAME, using="dob")
	WebElement txtdob;
	
	@CacheLookup
	@FindBy(how = How.NAME, using="addr")
	WebElement txtaddress;
	
	@CacheLookup
	@FindBy(how = How.NAME, using="city")
	WebElement txtcity;
	
	@CacheLookup
	@FindBy(how = How.NAME, using="state")
	WebElement txtstate;
	
	@CacheLookup
	@FindBy(how = How.NAME, using="pinno")
	WebElement txtpinno;
	
	@CacheLookup
	@FindBy(how = How.NAME, using="telephoneno")
	WebElement txttelephoneno;
	
	@CacheLookup
	@FindBy(how = How.NAME, using="emalid")
	WebElement txtemailid;
	
	@CacheLookup
	@FindBy(how = How.NAME, using="password")
	WebElement txtpassword;
	
	@CacheLookup
	@FindBy(how = How.NAME, using="sub")
	WebElement btnSubmit;
	
	public void clickAddNewCustomer() {
		lnkAddNewCutomerName.click();
		
	}
	
	public void custName(String cname) {
		txtCutomerName.sendKeys(cname);
		
	}
	
	public void custgender(String cgrnder) {
		rdGender.click();
		
	}
	
	public void custdob(String i, String j, String k) {
		txtdob.sendKeys(i);
		txtdob.sendKeys(j);
		txtdob.sendKeys(k);
		
	}
	
	public void custaddress(String caddress) {
		txtaddress.sendKeys(caddress);
		
	}
	
	public void custcity(String ccity) {
		txtcity.sendKeys(ccity);
		
	}
	
	public void custstate(String cstate) {
		txtstate.sendKeys(cstate);
		
	}
	
	public void custpinno(String cpinno) {
		txtpinno.sendKeys(String.valueOf(cpinno));
		
	}
	
	public void custtelephoneno(String ctelephoneno) {
		txttelephoneno.sendKeys(ctelephoneno);
		
	}
	
	public void custemailid(String cemailid) {
		txtemailid.sendKeys(cemailid);
		
	}
	
	public void custpassword(String cpassword) {
		txtpassword.sendKeys(cpassword);
		
	}
	
	public void custsubmit() {
		btnSubmit.click();
		
	}

	
}
