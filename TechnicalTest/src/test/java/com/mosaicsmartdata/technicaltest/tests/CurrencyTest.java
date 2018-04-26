package com.mosaicsmartdata.technicaltest.tests;

import java.lang.reflect.Method;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.mosaicsmartdata.technicaltest.basetest.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

public class CurrencyTest extends BaseTest  {
	@Test
	public void currencyTest() {
		
		strTestName = "Currency Test";
		//Starting Test for reports
		testLog = extReport.startTest(strTestName);
		testLog.log(LogStatus.INFO, "Currency Test Started");
		
		// Creatinf soft assertion object
		softAssertion = new SoftAssert();
		
		String strCurrency="";
		String strBase = "GBP";
		
		//Default base URI; can be red form property file 
		RestAssured.baseURI = "http://api.fixer.io";
		RestAssured.basePath = "/latest";
		//Setting content type as JSON
		RequestSpecification requestSpecification = RestAssured.given()
				.contentType(ContentType.JSON);
		
		//Adding query Parameter
		requestSpecification.queryParam("base", strBase);
		
		//executing get request
		RestAssured.given().spec(requestSpecification)
				.when().get()
				.then().statusCode(200).log().all();
		//getting response body for further processing
		Response response = requestSpecification.get();
		testLog.log(LogStatus.INFO, "Responce Body : " + response.asString());
		
		//Reading currency values using JASON path
		Map<String, Float> mapCurrencyList = response.jsonPath().getMap("rates");
		for (Map.Entry<String, Float> entry: mapCurrencyList.entrySet())
		{
		strCurrency= strCurrency + "<br>" + entry.getKey() + " - " + entry.getValue() ;
		}
		
		testLog.log(LogStatus.INFO,strCurrency);
		
		if (!(response.path("base").equals(strBase)) ) {
			softAssertion.assertTrue(
					false,
					"\n Base Currency is not matching Actual :"
							+ response.path("base")
							+ " ; Expected : " + strBase);
			testLog.log(
					LogStatus.FAIL,
					"\n Base Currency is not matching Actual :"
							+ response.path("base")
							+ " ; Expected : " + strBase);
		} else {
			testLog.log(
					LogStatus.PASS,
					"\n Base Currency is matching Actual : "
							+ response.path("base")
							+ " ; Expected : " + strBase);
		}

	}
	@AfterMethod
	public void aftermethod(Method result) throws Exception {
		
		//Ending Test for reports
		if(extReport!=null){
			extReport.endTest(testLog);
			extReport.flush();
		}
	}
	@AfterClass
	public void afterClass()
	{
		softAssertion.assertAll();
	}

}
