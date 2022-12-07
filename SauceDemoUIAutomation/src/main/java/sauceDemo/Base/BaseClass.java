package sauceDemo.Base;

import org.openqa.selenium.WebDriver;

public class BaseClass {

	WebDriver driver;
	
	public BaseClass() {
		this.driver=DriverInit.getDriver();
	}
	
}
