package com.qa.orangehrm.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.orangehrm.utils.BrowserOptionsUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author mahen
 *
 */
public class BasePage {

	WebDriver driver;
	Properties prop;
	BrowserOptionsUtil browserOptionsUtil;
	public static String flashElement;
		//ThreadLocal<WebDriver> is help to solve issues while running in parallel mode
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	
	/**
	 * this method is used to initialize the WebDriver based on give browser name
	 * @param browserName
	 * @return it returns driver
	 */
	public WebDriver init_driver(Properties prop) {
		
		browserOptionsUtil = new BrowserOptionsUtil(prop);
		
		flashElement =prop.getProperty("highlight");
		String browserName =prop.getProperty("browser");
		System.out.println("Browser Name is: "+ browserName);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteWebDriver(browserName);
			}else {
				//driver = new ChromeDriver(browserOptionsUtil.getChromeBrowserOptions());
				tlDriver.set(new ChromeDriver(browserOptionsUtil.getChromeBrowserOptions()));
			}

		}else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteWebDriver(browserName);
			}else {
				//driver = new FirefoxDriver(browserOptionsUtil.getFirefoxBrowserOptions());
				tlDriver.set(new FirefoxDriver(browserOptionsUtil.getFirefoxBrowserOptions()));
			}
			
		}else if(browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			//driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver());
		}else if(browserName.equalsIgnoreCase("safari")) {
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		}else {
			System.out.println("Please pass correct browser name: " + browserName);
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
	}
	
	/**
	 * getDriver using to get ThreadLocal driver
	 * @return WebDriver
	 * Synchronized keyword is used to synchronize all threads
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	
	/**
	 * This method will define the desired capabilities and initialize the driver with capability
	 * Also, this method is initialize the driver with Selenium Hub/Port
	 */
	
	private void init_remoteWebDriver(String browserName) {
		if(browserName.equalsIgnoreCase("chrome")) {
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(ChromeOptions.CAPABILITY, browserOptionsUtil.getChromeBrowserOptions());
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}else if(browserName.equalsIgnoreCase("firefox")) {
			DesiredCapabilities cap =DesiredCapabilities.firefox();
			cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, browserOptionsUtil.getFirefoxBrowserOptions());
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	/**
	 * this method is used to get properties values from config.properties file
	 * @return it returns prop
	 */
	public Properties init_prop() {
		prop = new Properties(); //Properties class creates an empty properties list with no default values
		
		String path =null;
		String env = null;
		
		env =System.getProperty("env");
		System.out.println("Running on Environment is: "+env);
		
		if(env == null) {
			System.out.println("Running on Environment is: "+ "PROD");
			path = "./src/main/java/com/qa/orangehrm/config/config.properties";
		}else {
			switch (env) {
			case "qa":
				path = "./src/main/java/com/qa/orangehrm/config/config.qa.properties";
				break;
			case "dev":
				path = "./src/main/java/com/qa/orangehrm/config/config.dev.properties";
				break;
			case "stage":
				path = "./src/main/java/com/qa/orangehrm/config/config.stage.properties";
				break;
			default:
				System.out.println("pass correct environment: "+env);
				break;
			}
		}
		
		try {
			FileInputStream ip = new FileInputStream(path);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
	
	public String getScreenshot() {
		
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/"+System.currentTimeMillis() + ".png";
		
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return path;
	}
	
	
	
	
	
	
	
	
	
	
	
}
