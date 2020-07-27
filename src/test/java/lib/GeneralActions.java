package lib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GeneralActions {
	public boolean isElementPresent(WebDriver driver, String xpath) {
		System.out.println(xpath);
		driver.findElement(By.xpath(xpath)); return true;
		
//	    try {
//	        driver.findElement(By.xpath(xpath));
//	        return true;
//	    } catch (org.openqa.selenium.NoSuchElementException e) {
//	        return false;
//	    }
	}


}
