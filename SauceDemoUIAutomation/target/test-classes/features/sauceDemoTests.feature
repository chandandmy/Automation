Feature: Test the sauce demo website 

Background: 
Given User navigated to sauceDemo website

@sauceDemo
Scenario: Test the ordered product if its correct or not
Given User login to application with correct username and password
When User added one product to cart
And User is doing checkout with below customer info
|     chandan            |     singh                 |          560045                    |
Then Validate the product details which were ordered
Then Validate log out feature of the application

@sauceDemo
Scenario Outline: Checking availability of Sauce labs Bolt T-shirt
Given User login to application with correct username and password
When getting all the available products
Then Validate tshirt named as <Tshirt_Name> is available
Then Validate log out feature of the application

Examples: 
|              Tshirt_Name                                 |                                            
|               Sauce Labs Bolt T-Shirt           |        

@sauceDemo
Scenario: Test the product price in product listing and product individual page
Given User login to application with correct username and password
When getting the price of all products without dollar symbol
Then Validate price is same on product listing page and product individual page
Then Validate log out feature of the application

@sauceDemo
Scenario: Test the wrong credentials on login page
Given User login to application with Incorrect username and password
Then App should display an error message
