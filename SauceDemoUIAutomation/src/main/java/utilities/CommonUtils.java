package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sauceDemo.Base.DriverInit;

public class CommonUtils {


	public static void clickElement(WebElement element) {
			webDriverWait(element);
			element.click();
	}
	
	public static void sendKeys(WebElement element, String value) {
			webDriverWait(element);
			element.sendKeys(value);
	}	
		
	public  static boolean isPresent(WebElement element) {
			webDriverWait(element);
			return element.isDisplayed();
	}
	
	
	public static void webDriverWait(WebElement element) {
		WebDriverWait wait=new WebDriverWait(DriverInit.driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static String getTextWeb(WebElement element) {
			webDriverWait(element);
			return element.getText();
	}
	
	public static String getProperty(String key) throws Throwable {
		Properties prop=new Properties();
		String localDir = System.getProperty("user.dir");
		File file = new File(localDir + "\\src\\test\\java\\resources\\globalData.properties");
		FileInputStream fis=new FileInputStream(file);
		prop.load(fis);
		String value=prop.getProperty(key);
		return value;
	}
	
	public static String getCurrentPageUrl() {
	return DriverInit.getDriver().getCurrentUrl();
	}
	
	
	
	
}
