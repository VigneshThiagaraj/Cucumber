package com.driver.setup;

import java.util.HashMap;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class SeleniumDriver {
	String url = null;
	public static ThreadLocal<ExtentReports>  extent=new ThreadLocal<>(); 
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	public static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<>();

	private SeleniumDriver() {
	}
	private static SeleniumDriver instance = new SeleniumDriver();

	public static SeleniumDriver getInstance() {

		return instance;
	}

	ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>() {
		@Override
		protected WebDriver initialValue() {    	  
			return setDriver();
		}
	};   

	private WebDriver setDriver(){
		return  getDriverbyBrowser(System.getProperty("browser.name"+ Thread.currentThread().getId()));	
	}

	public WebDriver getDriver() {       	
		try{
			driver.get().getCurrentUrl();
		} catch (Exception e){
			if (driver.get().toString().contains("null")) {
				driver.set(setDriver());
			}
		}
		return driver.get();
	}    

	public void removeDriver() {
		if(driver.get() != null){
			driver.get().quit();
			driver.remove();
		}        
	}    

	private static ChromeOptions createChromeCapabilities() {

		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
		logPrefs.enable(LogType.BROWSER, Level.ALL);  
		
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox");
		options.addArguments("--dns-prefetch-disable");	
		options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
		options.setExperimentalOption("w3c", false); 
		options.setExperimentalOption("prefs", chromePrefs);	
		return options;			

	}

	private static FirefoxOptions createFirefoxCapabilities() {
		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
		logPrefs.enable(LogType.BROWSER, Level.ALL);

		FirefoxOptions options = new FirefoxOptions();
		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
		options.addArguments("--no-sandbox");
		options.addArguments("--dns-prefetch-disable");	
		options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

		return options;						
	}

	private static InternetExplorerOptions createIECapabilities() {

		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
		logPrefs.enable(LogType.BROWSER, Level.ALL);

		InternetExplorerOptions options = new InternetExplorerOptions();		
		options.setCapability("ignoreZoomSetting", true);		
		options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		options.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
		options.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, "ignore");	
		options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
		return options;			

	} 

	private WebDriver getDriverbyBrowser(String browser) {
		WebDriver driver = null;
		switch(browser.toUpperCase()) {
		case "CHROME":
			driver = new ChromeDriver(createChromeCapabilities());
			break;				
		case "FIREFOX":
			driver = new FirefoxDriver(createFirefoxCapabilities());
			break;								
		case "INTERNET_EXPLORER":
			driver = new InternetExplorerDriver(createIECapabilities());	
			break;				
		default:
			throw new FrameworkException("Unhandled browser!");
		}	
		return driver;   	

	}    
}
