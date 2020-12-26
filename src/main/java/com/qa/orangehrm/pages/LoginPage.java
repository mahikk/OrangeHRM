package com.qa.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.orangehrm.base.BasePage;
import com.qa.orangehrm.utils.Constants;
import com.qa.orangehrm.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage extends BasePage{

	
	private WebDriver driver;
	ElementUtil elementUtil;
	//constructor
	
	public LoginPage(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
		this.driver=driver;
	}
	//By locators or Object Repository
	
	private By username=By.id("txtUsername");
	private By password=By.id("txtPassword");
	private By login=By.id("btnLogin");
	private By forgotPassword=By.linkText("Forgot your password?");
	
	
	
	//actions
	
	@Step("getting loginpage title")
	public String getLoginPageTitle() {
		//return driver.getTitle();
		return elementUtil.doWaitForTitlePresent(Constants.LOGIN_PAGE_TITLE, 10);
	}
	
	@Step("checking forgotpassword link exist or not")
	public boolean isForgotPasswordLinkExist() {
		//return driver.findElement(forgotPassword).isDisplayed();
		return elementUtil.doIsDisplayed(forgotPassword);
	}
	
	
	//Page Chaining: when landing from one page to another page, it is the Responsibility of method, which is cause for landing,
			//to return landed page object
	@Step("login in to application with username: {0} and password:{1}")
	public DashboardPage doLogin(String userId, String pwd) {
//		driver.findElement(username).sendKeys(userId);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(login).click();
		
		elementUtil.doSendkeys(username, userId);
		elementUtil.doSendkeys(password, pwd);
		elementUtil.doClick(login);
		
		return new DashboardPage(driver); //returning landed page (Dashboard Page) object
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
