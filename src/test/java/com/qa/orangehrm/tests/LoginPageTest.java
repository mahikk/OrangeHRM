package com.qa.orangehrm.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.orangehrm.base.BaseTest;
import com.qa.orangehrm.listerners.TestAllureListener;
import com.qa.orangehrm.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


//sometimes for failed test cases screenshot may not attached in allure report. 
//to solve this problem need add @Listeners(listenerName.class) annotation at test level in test.java class
@Listeners(TestAllureListener.class)
@Epic("Epic-100: Design loginPage moudle for OrangeHRM application")
@Story("US-101: Design all features of login page")
public class LoginPageTest extends BaseTest{
	
	
	
	@Description("verifying login page title")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=1)
	public void verifyLoginPageTitleTest() {
		String title =loginPage.getLoginPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Description("verifying ForgotPassword Link exit")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=2)
	public void verifyForgotPasswordLinkTest() {
		Assert.assertTrue(loginPage.isForgotPasswordLinkExist());
	}
	
	@Description("verify login")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=3)
	public void verifyLoginTest() {
		loginPage.doLogin(prop.getProperty("userid"),prop.getProperty("password"));
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
}

