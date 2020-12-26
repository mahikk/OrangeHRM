package com.qa.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.orangehrm.utils.Constants;
import com.qa.orangehrm.utils.ElementUtil;

public class DashboardPage {

	
	private WebDriver driver;
	ElementUtil elementUtil;
	
	//constructor
	public DashboardPage(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
		this.driver=driver;
	}
	
	//By locators
	private By header = By.tagName("h1");
	private By marketPlaceBtn = By.id("MP_link");
	private By welcomLink = By.id("welcome");
	private By notificationIcon = By.cssSelector("div.notification");
	private By pimMenu	= By.linkText("PIM");
	private By addEmployee = By.xpath("//a[@id='menu_pim_addEmployee']");
		//below By locator is to get all sub menu options from PIM menu
	//private By subMenuList = By.xpath("//a[@id='menu_pim_viewPimModule']//following-sibling::ul/li");
	private By employeeList = By.id("menu_pim_viewEmployeeList");
	
	
	//Actions
	
	public String getDashboardPageTitle() {
		//return driver.getTitle();
		return elementUtil.doWaitForTitlePresent(Constants.DASHBOARD_PAGE_TITLE, 1);
	}
	
	public String getDashboardPageUrl() {
		//return driver.getCurrentUrl();
		return elementUtil.doGetCurrentUrl();
		
	}
	
	public String getDashboardPageHeaderValue() {
		//return driver.findElement(header).getText();
		return elementUtil.doGetText(header);
	}
	
	public boolean isMarketplaceButtonExist() {
		//return driver.findElement(marketPlaceBtn).isDisplayed();
		return elementUtil.doIsDisplayed(marketPlaceBtn);
	}
	
	public String getAccountName() {
//		if(driver.findElement(welcomLink).isDisplayed()) {
//			return driver.findElement(welcomLink).getText();
//		}
//		return null;
		
		if(elementUtil.doIsDisplayed(welcomLink)) {
			return elementUtil.doGetText(welcomLink);
		}
		return null;
	}
	
	public boolean isNotificationIconExist() {
		//return driver.findElement(notificationIcon).isDisplayed();
		return elementUtil.doIsDisplayed(notificationIcon);
	}
	
	
//	private void clickAddEmployee(String value) {
//		elementUtil.moveToMainMenu(pimMenu);
//		elementUtil.doClickOnSubmenuOption(subMenuList, value);
//	}
	
	public AddEmployeePage goToAddEmployeePage() {
		elementUtil.moveToMainMenu(pimMenu);
		elementUtil.doClick(addEmployee);
		
		return new AddEmployeePage(driver);
	}
	
	public EmployeeListPage goToEmployeeListPage() {
		elementUtil.moveToMainMenu(pimMenu);
		elementUtil.doClick(employeeList);
		
		return new EmployeeListPage(driver);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
