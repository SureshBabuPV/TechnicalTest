package com.mosaicsmartdata.technicaltest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	@FindBy(xpath = "//input[@placeholder='username']")
	private WebElement txtbx_UserName;
	@FindBy(xpath = "//input[@placeholder='password']")
	private WebElement txtbx_Password;
	@FindBy(xpath = "//input[@type='submit']")
	private WebElement btn_Submit;

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public void enter_UaerName(String username) {
		txtbx_UserName.sendKeys(username);
	}

	public void enter_Password(String password) {
		txtbx_Password.sendKeys(password);
	}

	public void clickOn_Submit() {
		btn_Submit.click();
	}

	public void login_User(String username,String password) {
		enter_UaerName(username);
		enter_Password(password);
		clickOn_Submit();
	}
	
	public boolean is_LoginPage()
	{
		
		try{
			txtbx_UserName.isDisplayed();			
		}
		catch (Exception e){
;
			return false;
		}
		return true;

	}
}
