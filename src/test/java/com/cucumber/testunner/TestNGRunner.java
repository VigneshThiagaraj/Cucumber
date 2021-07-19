package com.cucumber.testunner;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

@CucumberOptions(
		features = "src/test/resources/Features/SauceDemo.feature",

		/*name = {"Checkout and validate the product details",
				"Check the particular product Sauce Labs Bolt T-Shirt",
				"Validate All Products Price", "Validate invalid login error message"},*/
		name = {"Checkout and validate the product details"},
		glue = {"com.cucumber.stepdefinition","com.framework.config"},
		plugin = { "pretty"})

/*
 * name = {"Checkout and validate the product details",
 * "Check the particular product Sauce Labs Bolt T-Shirt",
 * "Validate All Products Price", "Validate invalid login error message"},
 */
public class TestNGRunner extends AbstractTestNGCucumberTests {	

	private TestNGCucumberRunner testNGCucumberRunner;

	@BeforeClass(alwaysRun = true)
	public void setUpClass() {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	@Test(dataProvider = "features")
	public void scenario(PickleWrapper pickle, FeatureWrapper cucumberFeature) {
		testNGCucumberRunner.runScenario(pickle.getPickle());
	}

	@DataProvider
	public Object[][] features() {
		return testNGCucumberRunner.provideScenarios();
	}
}
