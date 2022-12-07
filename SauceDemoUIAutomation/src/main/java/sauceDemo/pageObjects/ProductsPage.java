package sauceDemo.pageObjects;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import sauceDemo.Base.BaseClass;
import utilities.CommonUtils;

public class ProductsPage extends BaseClass {

WebDriver driver;
List<String> inventoryProducts;
List<Double> inventoryProductsPrice;
List<Double> individualProductsPrice;

	
	public ProductsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
/**
*  Add to cart button
 */
@FindBy(xpath="(//button[text()='Add to cart'])[1]")
private WebElement addToCart_button;
	   
/**
*  Shopping cart button
 */
 @FindBy(xpath="//a[@class='shopping_cart_link']")
 private WebElement cart_Btn;
		   
 /**
*  product name
 */
@FindBy(xpath="(//div[@class='inventory_item_name'])[1]")
private WebElement prodName;
			   
 /**
 *  product price
 */
@FindBy(xpath="(//div[@class='inventory_item_price'])[1]")
private WebElement prodPrice;
				   
/**
 *  back to product button 
 */			   
@FindBy(xpath="//button[@id='back-to-products']")
private WebElement back_btn;			   
				 
/**
 *  all displayed product names
 */					   
@FindBys({
    @FindBy(xpath="//div[@class='inventory_item_name']")
})
private List<WebElement> productsListed;				   
				   
/**
 *  all displayed product price
 */					
@FindBys({
    @FindBy(xpath="//div[@class='inventory_item_price']")
})
private List<WebElement> productsListed_price;					   
					  
/**
 *  Product individual price
 */						  
@FindBy(xpath="//div[@class='inventory_details_price']")
private WebElement productIndividual_Price;							   
							  
/**
 *  home page burger button
 */								  
@FindBy(xpath="//div[@class='bm-burger-button']")
private WebElement burger_btn;								   
								   
/**
 *  application log out button
 */									  
@FindBy(xpath="//a[@id='logout_sidebar_link']")
private WebElement logout_btn;									   
									  
										  
	   public void addToCart() {
			 CommonUtils.clickElement(addToCart_button);
			 CommonUtils.clickElement(cart_Btn);
	   }
	   
	   public List<String> getProductDetails() {
		   List<String> prodDetails=new ArrayList<String>();
		  prodDetails.add(CommonUtils.getTextWeb(prodName));
		  prodDetails.add(CommonUtils.getTextWeb(prodPrice));
		  return prodDetails;
	   }
	
	public List<String> getAllListedProducts(){
		 inventoryProducts=new ArrayList<String>();
		for(int i=0;i<productsListed.size();i++) {
			inventoryProducts.add(CommonUtils.getTextWeb(productsListed.get(i)));
		}
		return inventoryProducts;
	}
	
	public List<Double> getAllProductPrice(){
		inventoryProductsPrice=new ArrayList<Double>();
		for(int i=0;i<productsListed_price.size();i++) {
			double productPrice=Double.parseDouble(CommonUtils.getTextWeb(productsListed_price.get(i)).replace("$", " ").trim());
			inventoryProductsPrice.add(productPrice);
		}
		return inventoryProductsPrice;
	}
	
	
	public List<Double> getIndividualPrice() {
		individualProductsPrice=new ArrayList<Double>();
		for(int i=0;i<productsListed.size();i++) {
			CommonUtils.clickElement(productsListed.get(i));
			double productPrice= Double.parseDouble(CommonUtils.getTextWeb(productIndividual_Price).replace("$", " ").trim());
			individualProductsPrice.add(productPrice);
			CommonUtils.clickElement(back_btn);
		}
		
		return individualProductsPrice;
		
	}
	
	public void logoutSteps() {
		CommonUtils.clickElement(burger_btn);
		CommonUtils.clickElement(logout_btn);
	}
	
	public String geturl() {
		String url=CommonUtils.getCurrentPageUrl();
		return url;
	}
	
	
	
	
	
	
	
	
	
	
	
}
