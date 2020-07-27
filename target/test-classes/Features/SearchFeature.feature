
@SmokeTestCases @regression
Feature: Verify user is able to search the valid product  
	
  Scenario Outline: Search a product and validate the details
    Given User opens the website
    When User enters the product name as <product> in search field
    And User clicks search icon
    Then User can see the product displayed
    And User can see valid product name as <product>
    
  Examples: 
      | product              |
      | Printed Chiffon Dress| 
      
      
  Scenario Outline: Add product to cart
    Given User opens the website
    When User scroll down in home page
    And User clicks Add to Cart button for <product>
    Then User can see the pop-up displayed
    And User can see valid product name as <product> displayed
    And User can see the successful message as <message> 
    
  Examples: 
      | product   					  | message|
      | Printed Chiffon Dress |Product successfully added to your shopping cart| 
      
  Scenario Outline: Validate product details and Add product to cart
    Given User opens the website
    When User scroll down in home page
    And User mousehover on <product_name>
    Then User can see More link 
    When User click on More link
    Then User should be redirected to product details page
    And User can see the product name as <product_name>
    And User can see the product price as <price>
    And User can see the product quantity as <quantity>
    When User clicks the + button 
    Then User can see the product quantity as <quantity_inr>
    When User clicks the - button 
    Then User can see the product quantity as <quantity_dcr>
    And User can see the size as <default_size>
    And User can select the size as <selected_size>
    And User clicks Add to cart button
    Then User can see the pop-up displayed
    And User can see valid product name as <product_name> displayed
    And User can see the successful message as <message> 
    
  Examples: 
      | product_name         | message                                        | price | quantity |quantity_inr|quantity_dcr|default_size|selected_size|
      | Printed Chiffon Dress|Product successfully added to your shopping cart| $16.51|1         |2           | 1          |S           |  M          |
      
  
 
 
