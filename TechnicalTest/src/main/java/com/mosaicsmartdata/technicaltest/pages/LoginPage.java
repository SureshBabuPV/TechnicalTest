package com.mosaicsmartdata.technicaltest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPage {

	WebDriver driver;
	public ExtentTest testLog;
	public String strPageName= "LoginPage : ";
	
	//Declaring all web elements in login page
	@FindBy(xpath = "//input[@placeholder='username']")
	private WebElement txtbx_UserName;
	@FindBy(xpath = "//input[@placeholder='password']")
	private WebElement txtbx_Password;
	@FindBy(xpath = "//input[@type='submit']")
	private WebElement btn_Submit;

	public LoginPage(WebDriver driver,ExtentTest testLog) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.testLog = testLog;
	}

	public void enter_UaerName(String username) {
		txtbx_UserName.sendKeys(username);
		testLog.log(LogStatus.INFO,
				strPageName + "Entered user name as :" + username);
	}

	public void enter_Password(String password) {
		txtbx_Password.sendKeys(password);
		testLog.log(LogStatus.INFO,
				strPageName + "Entered password as :" + password);
	}

	public void clickOn_Submit() {
		btn_Submit.click();
		testLog.log(LogStatus.INFO,
				strPageName + "Clicked on Submit ");
	}

	public void login_User(String username,String password) {
		enter_UaerName(username);
		enter_Password(password);
		clickOn_Submit();
	}
	
	public boolean is_LoginPage()
	{
		testLog.log(LogStatus.INFO,
				strPageName + "Verifying User Name is visible : ");
		try{
			txtbx_UserName.isDisplayed();	
			testLog.log(LogStatus.INFO,
					strPageName + " User Name is visible");
		}
		catch (Exception e){
			testLog.log(LogStatus.INFO,
					strPageName + " User Name is not visible");

			return false;
		}
		return true;

	}
}
