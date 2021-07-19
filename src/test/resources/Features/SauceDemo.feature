Feature: SauceDemo Website Automation
  #I want to use this template for my feature file

  Scenario: Checkout and validate the product details
    Given Login SauceDemo with valid username and password
    |UserName 			|Password 		|
    |standard_user  |secret_sauce |
    When Add Product to the cart
    |ProductName 	 				|
    |Sauce Labs Backpack  |
    And CheckOut Product with given shipment details
    |FirstName 	 				|	LastName		| PostalCode |
    |first name				  | last name		| 12345			 |
    Then Verify Product Details
    |ProductName 	 				|	ProductDesc		| ProductPrice |
    |Sauce Labs Backpack  | carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection. | $29.99 | 

  Scenario: Check the particular product Sauce Labs Bolt T-Shirt
    Given Login SauceDemo with valid username and password
    |UserName 			|Password 		|
    |standard_user  |secret_sauce |
    Then check Product Availability
    |ProductName 	 				|	
    |Sauce Labs Bolt T-Shirt |
 
 Scenario: Validate All Products Price
    Given Login SauceDemo with valid username and password
    |UserName 			|Password 		|
    |standard_user  |secret_sauce				|
    Then All Products Price Validation
    
 Scenario: Validate invalid login error message
    Given Login SauceDemo with valid username and password
    |UserName 			|Password 		|
    |standard_user  |secret				|
    Then Invalid Login Error Message
    |InvalidLoginErrorMsg 	 				|	
    |Epic sadface: Username and password do not match any user in this service 				|