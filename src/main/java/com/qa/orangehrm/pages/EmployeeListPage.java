package com.qa.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.orangehrm.utils.ElementUtil;

public class EmployeeListPage {
	
	private WebDriver driver;
	ElementUtil elementUtil;
	
	//constructor
	
	public EmployeeListPage(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
		this.driver = driver;
	}

	//By locators
	By header = By.tagName("h1");
	
	
	
	public void setup() {
		System.out.println("EmployeeListPage");
		System.out.println("New line of code");
		System.out.println("Another new line of code added into employee list page");
		
	}
	
	
	
	
}
