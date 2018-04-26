/**
 * 
 */
package com.mosaicsmartdata.technicaltest.tests;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
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

		openBrowser(); //open chrome browser
		strTestName = "LoginTest";
		testLog = extReport.startTest(strTestName);
		testLog.log(LogStatus.INFO, "Login Test Started");
		
		loginpage = new LoginPage(driver,testLog);
		homepage = new HomePage(driver,testLog);


		driver.get(config_prop.getProperty("url"));

		loginpage
				.login_User(testData.get("UserName"), testData.get("Password"));

		if (testData.get("LoginStatus").equals("LoginSuccessful")) {

			if (!(homepage.is_HomePage())) {
				softAssertion.assertTrue(
						false,
						"\n Login is UnSuccessful for user :"
								+ testData.get("UserName")
								+ " ; Expecting Successful login");
				testLog.log(
						LogStatus.FAIL,
						"\n Login is UnSuccessful for user :"
								+ testData.get("UserName")
								+ " ; Expecting Successful login");
			} else {
				testLog.log(
						LogStatus.PASS,
						"\n Login is Successful for user : "
								+ testData.get("UserName"));
				homepage.user_SignOut();
				if (!(loginpage.is_LoginPage())) {
					softAssertion.assertTrue(
							false,
							"\n Logout is UnSuccessful for user : "
									+ testData.get("UserName")
									+ " ; Expecting to logout");
					testLog.log(
							LogStatus.FAIL,
							"\n Logout is UnSuccessful for user : "
									+ testData.get("UserName")
									+ " ; Expecting to logout");
				} else {
					testLog.log(
							LogStatus.FAIL,
							"\n Logout is Successful for user : "
									+ testData.get("UserName"));
				}

			}
		} else if (testData.get("LoginStatus").equals("LoginUnSuccessful")) {
			if (homepage.is_HomePage()) {
				softAssertion.assertTrue(
						false,
						"\n Login is Successful for user : "
								+ testData.get("UserName")
								+ " ; Expecting login Error");
				testLog.log(
						LogStatus.FAIL,
						"\n Login is Successful for user : "
								+ testData.get("UserName")
								+ " ; Expecting login Error");
			}
		}

	}

	@AfterMethod
	public void aftermethod(Method result) throws Exception {
		if(extReport!=null){
			extReport.endTest(testLog);
			extReport.flush();
		}
		if(driver!=null)
			driver.quit();
	}
	
	@DataProvider(name = "getData")
	public Object[][] getData() {

		return dataHashTable;
	}

}