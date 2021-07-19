package com.framework.config;

import org.openqa.selenium.WebDriver;

import com.driver.setup.SeleniumDriver;

public class TestConfig {
	public WebDriver selenium() {
		return SeleniumDriver.getInstance().getDriver();
	}
}
