package com.framework.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.driver.setup.SeleniumDriver;

public class PageConfig {
	public WebDriver driver;
	public ThreadLocal<ExtentTest> test = SeleniumDriver.test;
	public PageConfig(WebDriver driver) {
		this.driver = driver;
	}

	public void initialize(Object o) {
		PageFactory.initElements(driver, o);
	}

	public void reportLog(String status, String description) {
		switch (status.toLowerCase()) {
		case "info": 
			test.get().log(Status.INFO, description);
			break;
		case "pass":
			test.get().log(Status.PASS, description);
			break;
		case "fail":
			Assert.assertEquals("Step failed with the reason as - " + description,"Step expected to pass");
			break;
		case "warning":
			test.get().log(Status.WARNING, description);
			break;
		default:
			test.get().log(Status.INFO, description);
			break;
		}
	}

	public void conditionalReportLog(String status, String description, String expected, String actual) {
		if(expected.equals(actual)) {
			reportLog(status, description);
		}else {
			reportLog("Fail", description);
		}
	}
}
