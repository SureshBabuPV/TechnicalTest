/**
 * 
 */
package com.mosaicsmartdata.technicaltest.tests;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.mosaicsmartdata.technicaltest.basetest.BaseTest;
import com.mosaicsmartdata.technicaltest.pages.HomePage;
import com.mosaicsmartdata.technicaltest.pages.LoginPage;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author sbabu
 *
 */
public class LoginTest extends BaseTest {
	LoginPage loginpage;
	HomePage homepage;

	@Test(dataProvider = "getData")
	public void logintest(Hashtable<String, String> testData) throws Exception {

		loginpage =new LoginPage(driver);
		homepage = new HomePage(driver);
		
		testLog = extReport.startTest("logintest");
		testLog.log(LogStatus.INFO, "Login Test Started");

		driver.get(config_prop.getProperty("url"));
		
		loginpage.login_User(testData.get("UserName"), testData.get("Password"));
		testLog.log(LogStatus.INFO, "Login Test Entered user name and password ");

		if (testData.get("LoginStatus").equals("LoginSuccessful"))
		{
			System.out.println("LoginSuccessful");
			if(!(homepage.is_HomePage()))
			{
				softAssertion.assertTrue(false, "\n Login is UnSuccessful for user :" + testData.get("UserName") + " ; Expecting Successful login");
				testLog.log(LogStatus.FAIL, "\n Login is UnSuccessful for user :" + testData.get("UserName") + " ; Expecting Successful login");
			}
			else
			{	testLog.log(LogStatus.PASS, "\n Login is Successful for user : " + testData.get("UserName"));
				homepage.user_SignOut();
				if (!(loginpage.is_LoginPage()))
				{
					softAssertion.assertTrue(false, "\n Logout is UnSuccessful for user : " + testData.get("UserName") + " ; Expecting to logout");
					testLog.log(LogStatus.FAIL, "\n Logout is UnSuccessful for user : " + testData.get("UserName") + " ; Expecting to logout");
				}
				else {
					testLog.log(LogStatus.FAIL, "\n Logout is Successful for user : " + testData.get("UserName"));
				}
					
				
			}
		}
		else if (testData.get("LoginStatus").equals("LoginUnSuccessful"))
		{
			homepage = new HomePage(driver);
			if(homepage.is_HomePage())
			{
				softAssertion.assertTrue(false, "\n Login is Successful for user : " + testData.get("UserName") + " ; Expecting login Error");
				testLog.log(LogStatus.FAIL, "\n Login is Successful for user : " + testData.get("UserName") + " ; Expecting login Error");
			}
		}

}
	@AfterMethod
	public void aftermethod(Method result) throws Exception {
	extReport.endTest(testLog); //Closing driver
	extReport.flush();
	//driver.quit();
 	}

}