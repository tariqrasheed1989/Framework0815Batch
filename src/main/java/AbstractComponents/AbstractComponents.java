package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {
	
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
	}

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
	
	public void jsClick(WebElement element)
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

}
