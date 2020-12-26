package com.qa.orangehrm.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.orangehrm.base.BaseTest;
import com.qa.orangehrm.utils.Constants;

public class DashboardPageTest extends BaseTest{
	
	
	@BeforeClass
	public void homePageSetup() {
		dashboardPage =loginPage.doLogin(prop.getProperty("userid").trim(), prop.getProperty("password").trim());
	}


	@Test(priority=1)
	public void verifyDashboardPageTitleTest() {
		String title =dashboardPage.getDashboardPageTitle();
		System.out.println("Dashboard Page Title is: "+title);
		Assert.assertEquals(title, Constants.DASHBOARD_PAGE_TITLE);
	}
	@Test(priority=2)
	public void verifyDashboardPageHeaderTextTest() {
		String headerText =dashboardPage.getDashboardPageHeaderValue();
		System.out.println("Dashboard Page Header is: "+headerText);
		Assert.assertEquals(headerText, Constants.DASHBOARD_PAGE_HEADERTEXT);
	}
	@Test(priority=3)
	public void verifyDashboardPageUrlTest() {
		String url =dashboardPage.getDashboardPageUrl();
		System.out.println("Dashboard Page Url is: "+url);
		Assert.assertEquals(url, Constants.DASHBOARD_PAGE_URL);
	}
	@Test(priority=4)
	public void verifyMarketplaceButtonExistTest() {
		Assert.assertTrue(dashboardPage.isMarketplaceButtonExist());
	}
	@Test(priority=5)
	public void verifyNotificationIconExistTest() {
		Assert.assertTrue(dashboardPage.isNotificationIconExist());
	}
	@Test(priority=6)
	public void verifyAccountName() {
		String accountName = dashboardPage.getAccountName();
		System.out.println("Account Name is: "+accountName);
		Assert.assertEquals(accountName, prop.getProperty("accountname").trim());
	}

	@Test (priority=7)
	public void clickAddEmployeeTest() {
		dashboardPage.goToAddEmployeePage();
		
	}
	
	
}
