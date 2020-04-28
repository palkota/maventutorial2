package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseTest;

public class BasePage {

	static WebDriver driver = BaseTest.driver;
	
	void click(By locator)
	{
		WebElement element = driver.findElement(locator);
		highlightElement(element);
		element.click();
	}
	
	void type(By locator,String text)
	{
		WebElement element = driver.findElement(locator);
		highlightElement(element);
		element.sendKeys(text);
	}
	
	void type(By locator, Keys enter) {
		WebElement element = driver.findElement(locator);
		highlightElement(element);
		element.sendKeys(enter);
	}
	
	List<WebElement> getElements(By locator)
	{
		return driver.findElements(locator);
	}
	
	void highlightElement(WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", element);
	}
	
	WebElement waitForElement(By locator,String condition)
	{
		WebDriverWait wait = new WebDriverWait(driver,10,2);
	
		if(condition.equals("visibility"))
		{
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			highlightElement(element);
			return element;
		}
		else if (condition.equals("clickable"))
		{
			return wait.until(ExpectedConditions.elementToBeClickable(locator));
		}
		return null;
	}
}
