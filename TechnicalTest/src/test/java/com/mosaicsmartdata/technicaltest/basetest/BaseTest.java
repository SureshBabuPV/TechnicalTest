package com.mosaicsmartdata.technicaltest.basetest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.mosaicsmartdata.technicaltest.excelutils.Xls_Reader;
import com.mosaicsmartdata.technicaltest.extentreports.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseTest {
	public Properties config_prop = null;
	public Xls_Reader test_data = null;
	public String testcase_name;
	public Object dataHashTable[][] = null;
	public WebDriver driver;
	public SoftAssert softAssertion;
	public ExtentReports extReport = ExtentManager.getInstance();
	public ExtentTest testLog;
	public ITestResult result;
	public String strTestName;

	@BeforeTest
	public void startUpTest() throws Exception {

		getConfigProperties(); // Initialising Configuration properties
		readTestDatFromXLS(); // Initialising Test Case Data using xls
		softAssertion = new SoftAssert();
	}

	public void getConfigProperties() throws IOException {
		// Reading Property files
		File file = new File(System.getProperty("user.dir")
				+ "\\config\\Configuration.properties");
		FileInputStream fileis = new FileInputStream(file);
		config_prop = new Properties();
		config_prop.load(fileis);
		fileis.close();
	}

	public void readTestDatFromXLS() {
		int len, colcount, rowcount, i, j;

		// Reading data from excel file
		testcase_name = this.getClass().getName().toString();
		String pathstring[] = testcase_name
				.split("com.mosaicsmartdata.technicaltest.tests.");
		len = (pathstring.length);
		testcase_name = pathstring[len - 1];
		test_data = new Xls_Reader(System.getProperty("user.dir")
				+ config_prop.getProperty("testdat_path") + "\\"
				+ testcase_name + ".xlsx");
		colcount = test_data.getColumnCount("Data");
		rowcount = test_data.getRowCount("Data");

		dataHashTable = new Object[rowcount - 1][1];
		for (i = 2; i <= rowcount; i++) {
			Hashtable<String, String> tempHashTable = new Hashtable<String, String>();
			for (j = 0; j < colcount; j++) {
				tempHashTable.put(test_data.getCellData("Data", j, 1),
						test_data.getCellData("Data", j, i));
			}
			dataHashTable[i - 2][0] = tempHashTable;
		}
	}

	public void openBrowser() throws Exception {
		if (config_prop.getProperty("browser").equals("chrome")) {
			System.setProperty(
					"webdriver.chrome.driver",
					System.getProperty("user.dir")
							+ config_prop.getProperty("drivers_path")
							+ "\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} else {
			throw new Exception("This Browser Type Not Implemented");
		}

	}

}
