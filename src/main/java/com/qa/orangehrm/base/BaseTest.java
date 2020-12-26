package com.qa.orangehrm.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.orangehrm.pages.AddEmployeePage;
import com.qa.orangehrm.pages.DashboardPage;
import com.qa.orangehrm.pages.LoginPage;

public class BaseTest {

	WebDriver driver;
	public BasePage basePage;
	public Properties prop;
	public LoginPage loginPage;
	public DashboardPage dashboardPage;
	public AddEmployeePage addEmployeePage;

	@Parameters({"browser"}) //browser parameter from testng.xml file
	@BeforeTest
	public void setUp(String browserName) { //this browserName is holding parameter for browser coming from testng.xml file
		System.out.println("Browser Name is: "+browserName);
		basePage = new BasePage();
		prop =basePage.init_prop();
		prop.setProperty("browser", browserName); //during runtime config.properties browser value override with testng.xml file browser value
		driver =basePage.init_driver(prop);
		loginPage = new LoginPage(driver);
	}
	
	
//If we want to run individual test class comment above BeforeTest method and uncomment this and run. 
	//OR create separate testng.xml file for specific class and keep this comment and use it
	
//	@BeforeTest
//	public void setUp() {
//		basePage = new BasePage();
//		prop =basePage.init_prop();
//		driver =basePage.init_driver(prop);
//		loginPage = new LoginPage(driver);
//	}
	
	
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}
}
