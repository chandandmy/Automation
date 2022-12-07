package sauceDemo.pageObjects;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sauceDemo.Base.BaseClass;
import utilities.CommonUtils;

public class CheckOutPage extends BaseClass  {

WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);	
	}
	
	
	/**
	 *  Checkout button
	 */
@FindBy(xpath="//button[@id='checkout']")
private WebElement checkOut_button;
	   
/**
 *  Customer first name text box
 */	   
@FindBy(xpath="//input[@id='first-name']")
private WebElement firstName_txtBox;		   
		   
/**
 *  Customer last name text box
 */		   
@FindBy(xpath="//input[@id='last-name']")
private WebElement lastName_txtBox;			   

/**
 *  Customer postal code text box
 */
@FindBy(xpath="//input[@name='postalCode']")
private WebElement postalCode_txtBox;			   
				   
/**
 *  Continue button
 */				   
@FindBy(xpath="//input[@id='continue']")
private WebElement continue_Btn;				   

/**
 *  Inventory item name
 */
@FindBy(xpath="//div[@class='inventory_item_name']")
private WebElement inventItem_Name;					   
					   
/**
 *  Inventory item price
 */						  
@FindBy(xpath="//div[@class='inventory_item_price']")
private WebElement inventItem_Price;						   
						   
/**
 *  Finish button
 */							   
@FindBy(xpath="//button[text()='Finish']")
private WebElement finish_Btn;							   

/**
 *  order confirmation message
 */
@FindBy(xpath="//h2[text()='THANK YOU FOR YOUR ORDER']")
private WebElement orderConfirmation_msg;								   
								   
								   	
	public List<String> checkoutSteps(String firstName, String LastName, String zipCode) {
		CommonUtils.clickElement(checkOut_button);
		CommonUtils.sendKeys(firstName_txtBox, firstName);
		CommonUtils.sendKeys(lastName_txtBox, LastName);
		CommonUtils.sendKeys(postalCode_txtBox, zipCode);
		CommonUtils.clickElement(continue_Btn);
		List<String> InventoryProdDetails=new ArrayList<String>();
		InventoryProdDetails.add(CommonUtils.getTextWeb(inventItem_Name));
		InventoryProdDetails.add(CommonUtils.getTextWeb(inventItem_Price));
		CommonUtils.clickElement(finish_Btn);
        return InventoryProdDetails;
	}
	
	public boolean successMsg() {
		return CommonUtils.isPresent(orderConfirmation_msg);
	}
	
}
