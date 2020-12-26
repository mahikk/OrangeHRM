package com.qa.orangehrm.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.orangehrm.base.BasePage;

public class ElementUtil {

	WebDriver driver;
	JavaScriptUtil jsUtil;
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}

	/**
	 * this method is used to create an WebElement
	 * @param locator
	 * @return WebElement
	 */
	public WebElement getElement(By locator) {
		WebElement element =driver.findElement(locator);
		if(Boolean.parseBoolean(BasePage.flashElement)) jsUtil.flash(element);
		return element;
	}

	/**
	 * this method is used to create WEbElement and perform an sendkeys action on WebElement
	 * @param locator
	 * @param value
	 */
	public void doSendkeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}
	
	/**
	 * this method is used to perform create WebElement and perform click action on WebElement
	 * @param locator
	 */
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	/**
	 * this method is used to enter values using Actions Class Sendkeys method
	 * @param locator
	 * @param value
	 */
	public void doActionsSendkeys(By locator, String value) {
		Actions actions = new Actions(driver);
		actions.sendKeys(getElement(locator), value).build().perform();
	}
	
	/**
	 * this method is used to click on element using Actions Class Click method
	 * @param locator
	 */
	public void doActionsClick(By locator) {
		Actions actions =new Actions(driver);
		actions.click(getElement(locator)).build().perform();
	}
		
	/**
	 * this method is used to Create WebElement and get text from WebElement
	 * @param locator
	 * @return String
	 */
	public String doGetText(By locator) {
		return getElement(locator).getText();	
	}
	
	/**
	 * this method is used to get current url of the page
	 * @return it returns string
	 */
	public String doGetCurrentUrl() {
		return driver.getCurrentUrl();
	}
	/**
	 * this method is used to verify Element is displayed or not
	 * @param locator
	 * @return boolean (true/false)
	 */
	public Boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	/**
	 * this method is used to click on link from list of links 
	 * @param linkList
	 * @param value
	 */
	public void doLinkClick(List<WebElement> linkList, String value) {
		System.out.println(linkList.size());
		for (WebElement lang:linkList) {
			String langName = lang.getText();
			System.out.println(langName);
			if(langName.equals(value)) {
				lang.click();
				break;
			}
		}
	}
	
	
	//********************Select Class for DropDown utils **********************************************
	
	/**
	 * this method is used to select value from drop down
	 * @param locator
	 * @param value
	 */
	public void selectDropdownValueByVisibleText(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);
	}
	/**
	 * this method is used to select value from drop down
	 * @param locator
	 * @param index
	 */
	public void selectDropdownValueByIndext(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}
	/**
	 * this method is used to select value from dropdown
	 * @param locator
	 * @param value
	 */
	public void selectDropdownValueByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}
	
	/**
	 * this method is used to get the drop down options count
	 * @param locator
	 * @return int
	 */
	public int getDropdownOptionsCount(By locator) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList=select.getOptions();
		return optionsList.size();
	}
	/**
	 * this method is used to get drop down options values
	 * @param locator
	 * @return ArrayList
	 */
	public List<String> getDropdownOptionsValue(By locator) {
		List<String> textList = new ArrayList<String>();
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		for(WebElement ele : optionsList) {
			String text =ele.getText();
			textList.add(text);
		}
		return textList;
	}
	
	
	//***********************************Dropdown values without Select Class****************************
	
	
	/**
	 * this method is used to get values from drop down without using select class
	 * @param locator
	 * @param value
	 */
	public void selectValuesFromDropdownWithoutSelect(By locator,String value) {
		List<WebElement> itemsList = driver.findElements(locator);
		System.out.println("Count is: "+itemsList.size());
		for(WebElement ele: itemsList) {
			String itemText =ele.getText();
			System.out.println(itemText);
			if(itemText.equals(value)) {
				ele.click();
				break;
			}
		}
	}
	
	//**********************************Wait Utils*********************************************************
	
	/**
	 * this method is used to wait for WebElement Present and return WebElement
	 * @param locator
	 * @param timeOut
	 * @return WebElement
	 */
	public WebElement doWaitForElementPresent(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	/**
	 * this method is used to wait for WebElement Visible. Means not only present in DOM also visible with height and width > 0
	 * @param locator
	 * @param timeOut
	 * @return WebElement
	 */
	public WebElement doWaitForElementVisible(By locator, long timeOut) {
		WebElement element = getElement(locator);
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	/**
	 * this method is used to wait for Title (Non-WebElement) present
	 * @param titleValue
	 * @param timeOut
	 * @return String
	 */
	public String doWaitForTitlePresent(String titleValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.titleIs(titleValue));
		return driver.getTitle();
	}
	
	/**
	 * this method is used to wait for Url 
	 * @param url
	 * @param timeOut
	 * @return boolean
	 */
	public boolean doWaitForUrl(String url, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.urlContains(url));
	}
	
	/**
	 * this method is used to wait for Alert PopUp
	 * @param timeOut
	 * @return Alert
	 */
	public Alert doWaitForAlertToBePresent(long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	/**
	 * this method is used to wait for Element to be Visible and make it Clickable
	 * @param locator
	 * @param timeOut
	 * @return WebEelement
	 */
	public WebElement doWaitForElementToBeClickable(By locator, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	/**
	 * this method is used to wait for Element to be Visible and enabled. Then click
	 * @param locator
	 * @param timeOut
	 */
	public void doWaitForElementClickableAndClickWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}
	
	/**
	 * this method is used to wait for All Elements Visible that matches the locator
	 * @param locator
	 * @param timeOut
	 * @return List of WebElements
	 */
	public List<WebElement> doWaitForVisibilityOfAllElements(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	/**
	 * this method is used to get text of links on page that matches given loctor
	 * @param locator
	 * @param timeOut
	 */
	public void doGetPageLinksText(By locator, int timeOut) {
		doWaitForVisibilityOfAllElements(locator, timeOut).stream().forEach(ele -> System.out.println(ele.getText()));
	}
	
	/**
	 * this method is used to wait for Element using FluentWait 
	 * @param locator
	 * @param timeOut
	 * @param interval
	 * @return WebElement
	 */
	public WebElement doWaitForElementWithFluentWait(By locator, int timeOut, int interval) {
		 Wait<WebDriver> wait =new FluentWait<WebDriver>(driver)
				 						.withTimeout(Duration.ofSeconds(timeOut))
				 						.pollingEvery(Duration.ofSeconds(interval))
				 						.ignoring(NoSuchElementException.class);
				 return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
//***********************************Browser Window PopUps Handle*************************************
	/**
	 * this method is used to get Browser Window PopUp  Ids with List collection
	 * @return String List
	 */
	public List<String> getWindowHandleId() {
		Set<String> handles =driver.getWindowHandles();
		List<String> list=new ArrayList<String>(handles);
		return list;
	}
	
	/**
	 * this method is used to get Browser Window PopUps Ids with Set collection
	 * @return
	 */
	public List<String> getWindowHandlesIdWithSet() {
		List<String> list =new ArrayList<String>();
		Set<String> handles=driver.getWindowHandles();
		Iterator<String> it =handles.iterator();
		for(int i=0; i<handles.size(); i++) {
			String windowId = it.next();
			list.add(windowId);
		}
		
		return list;
	}
	
	
//***************************Actions class methods*************************************	
	
	/**
	 * this method is used to mouse over on menu 
	 * @param locator
	 */
	public void moveToMainMenu(By locator) {
		Actions actions = new Actions(driver);
		actions.moveToElement(getElement(locator)).build().perform();
	}
	
	/**
	 * this method is used to collect list of elements from sub menu and perform click on selected element
	 * @param locator
	 * @param value
	 */
	public void doClickOnSubmenuOption(By locator, String value) {
		List<WebElement> submenuList = driver.findElements(locator);
		for(WebElement ele: submenuList) {
			String text=ele.getText();
			System.out.println(text);
			if(text.equals(value)) {
				ele.click();
				break;
			}
		}
	}

}
