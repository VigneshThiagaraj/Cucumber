package com.cucumber.pages;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.framework.config.PageConfig;

public class Products extends PageConfig{
	
	@FindBy(className  ="inventory_item_name")
	public List<WebElement> lbl_ProductName;
	
	@FindBy(className  ="inventory_item_price")
	public List<WebElement> lbl_ProductPrice;
	
	@FindBy(className  ="inventory_details_price")
	public WebElement lbl_ProductDetailsPrice;
	
	@FindBy(id  ="back-to-products")
	public WebElement btn_backToProducts;
	
	public Products(WebDriver driver) {
		super(driver);
		initialize(this);
	}
	
	public void addProductToCart(List<Map<String,String>> data) {
		String productName = data.get(0).get("ProductName");
		String btn_AddProduct = "//div[text()='" + productName + "']/following::button[1]";
		driver.findElement(By.xpath(btn_AddProduct)).click();
		reportLog("info", productName + " is added to the cart");
	}
	
	public void isProductAvailable(List<Map<String,String>> data) {
		boolean flag = false;
		String proName = data.get(0).get("ProductName");
		for(int i = 0; i<lbl_ProductName.size(); i++) {
			if(lbl_ProductName.get(i).getText().equals(proName)) {
				flag = true;
				break;
			}
		}
		if(flag) {
			reportLog("Pass", proName + " is displayed as expected");
		}else {
			reportLog("Fail", proName + " is not displayed as expected");
		}
	}
	
	public void validateAllProductPrice() {
		for(int i = 0; i<lbl_ProductName.size(); i++) {
			String proName = lbl_ProductName.get(i).getText();
			String price = lbl_ProductPrice.get(i).getText();
			lbl_ProductName.get(i).click();
			String detailsPrice = lbl_ProductDetailsPrice.getText();
			if(price.equals(lbl_ProductDetailsPrice.getText())) {
				reportLog("Pass", proName + " price is " + price + " displayed as expected in details page");
			}else {
				reportLog("Fail", "Mismatch in " + proName + " price actual " + price + "  expected " + detailsPrice);
			}
			btn_backToProducts.click();
		}
	}
}
