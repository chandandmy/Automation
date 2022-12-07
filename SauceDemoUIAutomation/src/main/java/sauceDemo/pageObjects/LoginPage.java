package sauceDemo.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sauceDemo.Base.BaseClass;
import utilities.CommonUtils;

public class LoginPage extends BaseClass  {

	 WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	/**
	 *  username textbox
	 */
	   @FindBy(xpath="//input[@id='user-name']")
	   private WebElement userName_textBox;
	   
	   /**
		 *  password textbox
		 */
	@FindBy(xpath="//input[@id='password']")
    private WebElement pwd_textBox;
		   
		   /**
			 *  login button
			 */
	 @FindBy(xpath="//input[@id='login-button']")
	 private WebElement login_btn;
	 
	 /**
		 *  login error message
		 */
@FindBy(xpath="//h3[@data-test='error']")
private WebElement error_msg;
	 
	 public void loginToAppWithCorrectCreds() throws Throwable {
		 CommonUtils.sendKeys(userName_textBox,CommonUtils.getProperty("username"));
		 CommonUtils.sendKeys(pwd_textBox,CommonUtils.getProperty("password"));
		 CommonUtils.clickElement(login_btn);
	 }
	
	 public void loginToAppWithInCorrectCreds() throws Throwable {
		 CommonUtils.sendKeys(userName_textBox,CommonUtils.getProperty("username"));
		 CommonUtils.sendKeys(pwd_textBox,CommonUtils.getProperty("wrong_pwd"));
		 CommonUtils.clickElement(login_btn);
	 }
	 
	 public boolean getErrorMsgStatus() {
		return CommonUtils.isPresent(error_msg);
	 }
	
	public String getErrorMsgText() {
		return CommonUtils.getTextWeb(error_msg);
	}
	
	
	
	
	
	
	
}
