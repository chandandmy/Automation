package sauceDemo.Base;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.CommonUtils;

public class DriverInit {

	public static WebDriver driver;

	public WebDriver initializeDriver() throws Throwable
{
		String browserName=CommonUtils.getProperty("browser");
		
		 if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			 driver=new ChromeDriver();
		} else if(browserName.contains("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}else {
			System.out.println("Please provide a browser name in global data file to execute");
			System.exit(0);
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(CommonUtils.getProperty("App_url"));
		return driver;
	}
	
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	
	
	
	
	
	
	
}
