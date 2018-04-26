package com.mosaicsmartdata.technicaltest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HomePage {


	WebDriver driver;
	public ExtentTest testLog;
	public String strPageName= "HomePage : ";
	

	@FindBy(xpath = "//h1[text()='Dashboard']")
	private WebElement txt_Dashboard;
	@FindBy(xpath = "//a[text()='Sign out']")
	private WebElement link_SignOut;
		
	public HomePage(WebDriver driver,ExtentTest testLog) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.testLog = testLog;
	}
	public void click_SignOut() {
		link_SignOut.click();
		testLog.log(LogStatus.INFO,
				strPageName + "Clicked on Signout link ");
	}
	public void user_SignOut()
	{
		click_SignOut();
	}
	public boolean is_HomePage()
	{
		testLog.log(LogStatus.INFO,
				strPageName + "Verifying Dashboard text is displayed : ");
		try{
		 txt_Dashboard.isDisplayed();
			testLog.log(LogStatus.INFO,
					strPageName + " Dashboard text is displayed. ");
	}
	catch (Exception e){
		testLog.log(LogStatus.INFO,
				strPageName + " Dashboard text is not displayed. ");
		return false;
	}
	return true;
	}
}
