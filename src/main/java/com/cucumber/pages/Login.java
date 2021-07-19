package com.cucumber.pages;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.framework.config.PageConfig;

public class Login extends PageConfig{
	
	@FindBy(id ="user-name")
	public WebElement txt_UserName;
	
	@FindBy(name ="password")
	public WebElement txt_Password;
	
	@FindBy(id ="login-button")
	public WebElement btn_Login;
	
	@FindBy(xpath ="//h3[@data-test='error']")
	public WebElement lbl_InvalidLoginErrorMsg;
	
	public Login(WebDriver driver) {
		super(driver);
		initialize(this);
	}
	
	public void launchUrl() {
		String url = System.getProperty("launch.url");
		driver.get(url);
		driver.manage().window().maximize();
		reportLog("info", "SauceDemo is succcessfully launched with url " + url);
	}
	
	public void login(List<Map<String,String>> data) {
		String userName = data.get(0).get("UserName");
		txt_UserName.sendKeys(userName);
		reportLog("info", "User Name is entered as " + userName);
		String password = data.get(0).get("Password");
		txt_Password.sendKeys(password);
		reportLog("info", "password is entered as " + password);
		btn_Login.click();
		reportLog("info", "Login button is clicked");
	}

	public void invalidLogin(List<Map<String,String>> data) {
		if(lbl_InvalidLoginErrorMsg.getText().trim().equals(data.get(0).get("InvalidLoginErrorMsg").trim())) {
			reportLog("Pass", "Invalid Login Error Message " + data.get(0).get("InvalidLoginErrorMsg").trim() + " is displayed as expected");
		}else {
			reportLog("Fail", "Invalid Login Error Message " + data.get(0).get("InvalidLoginErrorMsg").trim() + " not is displayed as expected");
		}
	}
}
