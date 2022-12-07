package stepDefinations;

import java.util.List;
import org.junit.Assert;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sauceDemo.Base.DriverInit;
import sauceDemo.pageObjects.CheckOutPage;
import sauceDemo.pageObjects.LoginPage;
import sauceDemo.pageObjects.ProductsPage;
import utilities.CommonUtils;

public class SauceDemoSteps  extends DriverInit{

	
	LoginPage loginPage;
	ProductsPage productPage;
	CheckOutPage checkOutPage;
	List<String> productDetails;
	List<String> InventProductDetails;
	List<String> listedInventoryProducts;
	List<Double> productsPriceList;


	@Given("^User login to application with correct username and password$")
    public void user_login_to_application_with_correct_username_and_password() throws Throwable {
		loginPage=new LoginPage(driver);
		loginPage.loginToAppWithCorrectCreds();
    }
	
	@Given("^User login to application with Incorrect username and password$")
    public void user_login_to_application_with_incorrect_username_and_password() throws Throwable {
		loginPage=new LoginPage(driver);
		loginPage.loginToAppWithInCorrectCreds();
    }
	
	@Given("^User navigated to sauceDemo website$")
    public void user_navigated_to_saucedemo_website() throws Throwable {
		initializeDriver();
    }
	
	@When("^User added one product to cart$")
    public void user_added_one_product_to_cart() throws Throwable {
		productPage=new ProductsPage(driver);
		productDetails=productPage.getProductDetails();
		productPage.addToCart();
		
    }
	
    @And("^User is doing checkout with below customer info$")
    public void user_is_doing_checkout(DataTable table) throws Throwable {
    	checkOutPage=new CheckOutPage(driver);
    	List<List<String>> customerInfo=table.asLists();
    	InventProductDetails=checkOutPage.checkoutSteps(customerInfo.get(0).get(0), customerInfo.get(0).get(1), customerInfo.get(0).get(2));	
    }
	
    @Then("^Validate the product details which were ordered$")
    public void validate_the_product_details_which_were_ordered() throws Throwable {
    	   if(checkOutPage.successMsg()) {
    		   Assert.assertEquals(productDetails.get(0), InventProductDetails.get(0));
    		   Assert.assertEquals(productDetails.get(1), InventProductDetails.get(1));
    	   }else {
    		   Assert.fail();
    	   }
    }
    
    @When("^getting all the available products$")
    public void getting_all_the_available_products() throws Throwable {
		productPage=new ProductsPage(driver);
    	listedInventoryProducts=productPage.getAllListedProducts();	
    	Thread.sleep(4000);
    }
    
    @Then("^Validate tshirt named as (.+) is available$")
    public void validate_tshirt_named_as_is_available(String tshirtname) throws Throwable {
    	
    	for(int i=0;i<listedInventoryProducts.size();i++) {
    		if(listedInventoryProducts.get(i).equalsIgnoreCase(tshirtname)) {
    			Assert.assertTrue(tshirtname+" is available on product listed page", true);
    			return;
    		}		
    		}
    	Assert.fail(tshirtname+" is not present on product listed page");
    	}
    
	
    @When("^getting the price of all products without dollar symbol$")
    public void getting_the_price_of_all_products_without_dollar_symbol() throws Throwable {
    	productPage=new ProductsPage(driver);
    	productsPriceList=productPage.getAllProductPrice();
    }
	
    @Then("^Validate price is same on product listing page and product individual page$")
    public void validate_price_is_same_on_product_listing_page_and_product_individual_page() throws Throwable {
    	List<Double> individualProductPrice=productPage.getIndividualPrice();
    	for(int i=0;i<productsPriceList.size();i++) {
    		if(productsPriceList.get(i).equals(individualProductPrice.get(i))) {
    			Assert.assertTrue("Both price matched", true);
    		}else {
    			Assert.fail("product price not matched on product page and indvidual product page");
    		}
    	}
    	
    }
		  
    @Then("^App should display an error message$") 
		  public void app_should_display_an_error_message() throws Throwable {
		         if(loginPage.getErrorMsgStatus()&&CommonUtils.getProperty("error_msg").contains(loginPage.getErrorMsgText())) {
		        	 Assert.assertTrue("Error message displayed as "+loginPage.getErrorMsgText(), true);
		         }else {
		        	 Assert.fail("Error message for invalid credentials not displayed");
		         }
		         driver.quit();
		  
		  }
		 
    @Then("^Validate log out feature of the application$")
    public void validate_log_out_feature_of_the_application() throws Throwable {
    	productPage.logoutSteps();
    	if(CommonUtils.getProperty("App_url").equalsIgnoreCase(productPage.geturl())) {
    		Assert.assertTrue("Successfully validated logout feature", true);
    	}else {
    		Assert.fail("Logout feature not working");
    	}
    	driver.quit();
    }
    	
    	
    	
    
	
	
	
	
	
	
	
}
