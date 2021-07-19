package com.cucumber.pages;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import com.framework.config.PageConfig;

public class Cart extends PageConfig {

	@FindBy(className ="shopping_cart_link")
	public WebElement lnk_ShoppingCart;
	
	@FindBy(name ="checkout")
	public WebElement btn_CheckOutproducts;
	
	@FindBy(css = "input[id='first-name']")
	public WebElement txt_FirstName;

	@FindBy(css = "input[id='last-name']")
	public WebElement txt_LastName;

	@FindBy(css = "input[id='postal-code']")
	public WebElement txt_PostalCode;

	@FindBy(css = "input[id='continue']")
	public WebElement btn_Continue;
	
	@FindBy(xpath = "//div[@class = 'inventory_item_name']")
	public WebElement lbl_ProductName;

	@FindBy(xpath = "//div[@class = 'inventory_item_desc']")
	public WebElement lbl_ProductDesc;

	@FindBy(xpath = "//div[@class = 'inventory_item_price']")
	public WebElement lbl_ProductPrice;

	@FindBy(xpath = "//div[@class = 'summary_subtotal_label']")
	public WebElement lbl_ItemSubTotal;
	
	@FindBy(xpath = "//div[@class = 'summary_tax_label']")
	public WebElement lbl_TaxPrice;
	
	@FindBy(xpath = "//div[@class = 'summary_total_label']")
	public WebElement lbl_TotalPrice;

	@FindBy(xpath = "//button[@name='finish']")
	public WebElement btn_Finish;
	
	public Cart(WebDriver driver) {
		super(driver);
		initialize(this);
	}
	
	public void navigateToCartPage() {
		lnk_ShoppingCart.click();
	}
	
	public void checkOutProduct() {
		btn_CheckOutproducts.click();
	}
	
	public void enterShippmentAddress(List<Map<String,String>> data) {
		String firstName = data.get(0).get("FirstName");
		txt_FirstName.sendKeys(firstName);
		reportLog("info", "First Name is entered as " + firstName);
		String lastName = data.get(0).get("LastName");
		txt_LastName.sendKeys(data.get(0).get("LastName"));
		reportLog("info", "First Name is entered as " + lastName);
		String postalCode = data.get(0).get("PostalCode");
		txt_PostalCode.sendKeys(data.get(0).get("PostalCode"));
		reportLog("info", "First Name is entered as " + postalCode);
		btn_Continue.click();
	}
	
	public void verifyProductDetails(List<Map<String,String>> data) {
		String actualProductName = lbl_ProductName.getText();
		String expectedProductName = data.get(0).get("ProductName");
		conditionalReportLog("Pass","Product " + actualProductName + " is displayed as expected",expectedProductName, actualProductName);
		
		String actualProDesc = lbl_ProductDesc.getText();
		String expectedProDesc = data.get(0).get("ProductDesc");
		conditionalReportLog("Pass","Product Description " + actualProDesc + " is displayed as expected",expectedProDesc,actualProDesc);
		
		String actualProductPrice = lbl_ProductPrice.getText();
		String expectedProductPrice = data.get(0).get("ProductPrice");
		conditionalReportLog("Pass","Product Price " + actualProductPrice +" is displayed as expected",expectedProductPrice, actualProductPrice);
		
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(lbl_TaxPrice.getText().isEmpty(), false);
		
	}
}
