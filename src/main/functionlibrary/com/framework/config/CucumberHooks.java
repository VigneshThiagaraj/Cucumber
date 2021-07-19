package com.framework.config;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.aventstack.extentreports.ExtentTest;
import com.driver.setup.PropertiesLoader;
import com.driver.setup.SeleniumDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class CucumberHooks {

	@Before
	public void beforeCheck(Scenario scenario)  {
		PropertiesLoader.loadProperty();
		try {
			FileUtils.cleanDirectory(new File(ReportManager.resultsPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} 
		ReportManager.testExecutionReportTitle = scenario.getName() + "_" + Thread.currentThread().getId() + ".html";
		
		SeleniumDriver.extent.set(ReportManager.createInstance());
		SeleniumDriver.parentTest.set(SeleniumDriver.extent.get().createTest(scenario.getName()+ "_" + Thread.currentThread().getId()));
		ExtentTest child = SeleniumDriver.parentTest.get().createNode(scenario.getName()+ "_" + Thread.currentThread().getId());
		SeleniumDriver.test.set(child);
	}
	
	@After
	public void tearDown() {
		System.out.println("before Suite Current Thread " + Thread.currentThread().getId());
		SeleniumDriver.extent.get().flush();
		SeleniumDriver.getInstance().removeDriver();
	}
}
