package com.qa.orangehrm.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.orangehrm.base.BaseTest;
import com.qa.orangehrm.utils.Constants;
import com.qa.orangehrm.utils.ExcelUtil;

public class AddEmployeePageTest extends BaseTest{
	
	@BeforeClass
	public void addEmployeePageSetUP() {
		dashboardPage =loginPage.doLogin(prop.getProperty("userid").trim(), prop.getProperty("password").trim());
		addEmployeePage = dashboardPage.goToAddEmployeePage();
	}

	@Test(priority=1)
	public void verifyAddEmployeePageTitleTest() {
		String title =addEmployeePage.getAddEmployeePageTitle();
		System.out.println("AddEmployee Page title is: "+ title);
		Assert.assertEquals(title, Constants.ADDEMPLOYEE_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void verifyAddEmployeePageHeaderTest() {
		String header = addEmployeePage.getAddEmployeePageHeader();
		System.out.println("AddEmployee Page Header is: "+header);
		Assert.assertEquals(header, Constants.ADDEMPLOYEE_HEADERTEXT);
	}
	
	@DataProvider()
	public Object[][] getAddEmployeeTestData() {
		Object [][] data =ExcelUtil.getTestData(Constants.ADDEMPLOYEE_SHEET_NAME);
		return data;
	}
		
	@Test(dataProvider="getAddEmployeeTestData", priority=3)
	public void addEmployeeTest(String firstName, String middleName,String lastName,String photograph,String userName,String password,String confirmPassword) {
		Assert.assertTrue(addEmployeePage.addEmployee(firstName,middleName,
				lastName,photograph,userName,password,confirmPassword));
		System.out.println("Employee Created Successfully");
	}
	
	
	
	
	
	
	
	
	
	
	
}
