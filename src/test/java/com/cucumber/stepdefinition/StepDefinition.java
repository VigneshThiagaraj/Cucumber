package com.cucumber.stepdefinition;

import java.util.List;
import java.util.Map;

import com.cucumber.pages.Cart;
import com.cucumber.pages.Login;
import com.cucumber.pages.Products;
import com.framework.config.TestConfig;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends TestConfig{

	@Given("^Login SauceDemo with valid username and password$")
	public void loginSauceDemo(DataTable table) throws Throwable {
		List<Map<String,String>> data = table.asMaps(String.class, String.class);
		Login login = new Login(selenium());
		login.launchUrl();
		login.login(data);

	}
	@When("^Add Product to the cart")
	public void addProduct(DataTable table) throws Throwable {
		List<Map<String,String>> data = table.asMaps(String.class, String.class);
		Products product = new Products(selenium());
		product.addProductToCart(data);

	}
	
	@And("CheckOut Product with given shipment details")
	public void checkOutProduct(DataTable table) throws Throwable {
		List<Map<String,String>> data = table.asMaps(String.class, String.class);
		Cart cart = new Cart(selenium());
		cart.navigateToCartPage();
		cart.checkOutProduct();
		cart.enterShippmentAddress(data);
	}
	
	@Then("^Verify Product Details$")
	public void verifyProduct(DataTable table) throws Throwable {
		List<Map<String,String>> data = table.asMaps(String.class, String.class);
		Cart cart = new Cart(selenium());
		cart.verifyProductDetails(data);
	}
	
	@Then("^check Product Availability$")
	public void checkProductAvailability(DataTable table) throws Throwable {
		List<Map<String,String>> data = table.asMaps(String.class, String.class);
		Products product = new Products(selenium());
		product.isProductAvailable(data);
	}
	
	@Then("^Invalid Login Error Message$")
	public void invalidLogin(DataTable table) throws Throwable {
		List<Map<String,String>> data = table.asMaps(String.class, String.class);
		Login login = new Login(selenium());
		login.invalidLogin(data);
	}
	
	@Then("^All Products Price Validation$")
	public void validateAllProductPrice() throws Throwable {
		Products product = new Products(selenium());
		product.validateAllProductPrice();
	}
}
