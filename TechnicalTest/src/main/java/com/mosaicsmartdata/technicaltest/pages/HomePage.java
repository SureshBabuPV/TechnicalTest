package com.mosaicsmartdata.technicaltest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

public class HomePage {

	SoftAssert softAssertion= new SoftAssert();
	@FindBy(xpath = "//h1[text()='Dashboard']")
	private WebElement txt_Dashboard;
	@FindBy(xpath = "//a[text()='Sign out']")
	private WebElement link_SignOut;
		
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	public void click_SignOut() {
		link_SignOut.click();
	}
	public void user_SignOut()
	{
		click_SignOut();
	}
	public boolean is_HomePage()
	{
		try{
		 txt_Dashboard.isDisplayed();
	}
	catch (Exception e){
		return false;
	}
	return true;
	}
}
