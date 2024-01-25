package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.CartPage;

public class AbstractComponents {
	
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement Cart;
	
	public void waitUntillElementIsVisible(WebElement element,int time)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitUntillElementGetsDisappear(By findBy,int time)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	public void waitUntillElementGetsDisappear(WebElement element,int time)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	public void waitUnitlElementIsClickable(WebElement element, int time) {
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
	}
	
	
	public void jsClick(WebElement element)
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	public CartPage goToCart()
	{
		Cart.click();
		
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}
	
	public void clickElement(WebElement element,String ElementName) {

		try {
			element.click();
		}catch(Exception e) {
			jsClick(element);
			System.out.println("Unable to click "+ElementName+" through UI. So clicked using Javascript");
		}
	}
	
	public String filterText(String input, String delimeter) {//#6581ca399fd99c85e8ee7f45
		
		return input.split(delimeter)[1].trim();
	}

}
