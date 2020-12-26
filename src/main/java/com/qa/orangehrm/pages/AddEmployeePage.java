package com.qa.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.orangehrm.base.BasePage;
import com.qa.orangehrm.utils.Constants;
import com.qa.orangehrm.utils.ElementUtil;

public class AddEmployeePage extends BasePage{
	
	WebDriver driver;
	ElementUtil elementUtil;
	//constructor
	public AddEmployeePage(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
		this.driver=driver;
	}
	
	//By locators
	
	private By header = By.tagName("h1");
	private By firstName = By.id("firstName");
	private By middleName = By.id("middleName");
	private By lastName = By.id("lastName");
	private By photograph	= By.id("photofile");
	private By createLoginChk = By.id("chkLogin");
	private By userName = By.id("user_name");
	private By password = By.id("user_password");
	private By confirmPassword = By.id("re_password");
	private By saveBtn = By.id("btnSave");
	private By addEmployee = By.id("menu_pim_addEmployee");
	
	
	
	//Actions
	
	public String getAddEmployeePageTitle() {
		return elementUtil.doWaitForTitlePresent(Constants.ADDEMPLOYEE_PAGE_TITLE, 10);
	}
	
	public String getAddEmployeePageHeader() {
		elementUtil.doWaitForElementPresent(header, 10);
		return elementUtil.doGetText(header);
	}
	
	public boolean addEmployee(String firstName, String middleName, String lastName,String photograph,String userName, String password, String confirmPassword) {
		elementUtil.doWaitForElementPresent(this.firstName, 10);
		elementUtil.doSendkeys(this.firstName,firstName);
		elementUtil.doSendkeys(this.middleName, middleName);
		elementUtil.doSendkeys(this.lastName, lastName);
		elementUtil.doSendkeys(this.photograph, photograph);
		elementUtil.doClick(createLoginChk);
		elementUtil.doWaitForElementPresent(this.userName, 10);
		elementUtil.doSendkeys(this.userName, userName);
		elementUtil.doSendkeys(this.password, password);
		elementUtil.doSendkeys(this.confirmPassword, confirmPassword);
		elementUtil.doClick(saveBtn);
		
		String fullName=firstName+" "+middleName+" "+lastName;
		elementUtil.doWaitForElementVisible(By.xpath("//h1[text()='"+fullName+"']"), 10);
		boolean flag =elementUtil.doIsDisplayed(By.xpath("//h1[text()='"+fullName+"']"));
		elementUtil.doClick(addEmployee);
		
		return flag;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
