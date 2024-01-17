package Tests;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import PageObjects.LandingPage;

public class Test
{
	public static void main(String[] args) 
	{
		String product="Zara coat 3";
		String country="India";
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		LandingPage landingPage=new LandingPage(driver);
		landingPage.goTo();
		landingPage.login("pathan@gmail.com", "Abcd@1234");
		
//		WebElement txt_UserName=driver.findElement(By.xpath("//input[@id='userEmail']"));
//		WebElement txt_Password=driver.findElement(By.xpath("//input[@type='password']"));
//		WebElement btn_Login=driver.findElement(By.id("login"));
//		
//		txt_UserName.sendKeys("pathan@gmail.com");
//		txt_Password.sendKeys("Abcd@1234");
//		btn_Login.click();
		
//		driver.findElement(By.xpath("//b[normalize-space()='"+product.toUpperCase()+"']/parent::h5/parent::div //button[text()=' Add To Cart']")).click();
		List<WebElement> ProductsName=driver.findElements(By.xpath("//div[@class='card-body']/h5/b"));
		List<WebElement>AddToCart=driver.findElements(By.xpath("//button[normalize-space()='Add To Cart']"));
		
		for(int i=0;i<ProductsName.size();i++)
		{
			String productName=ProductsName.get(i).getText();
			if(productName.equalsIgnoreCase(product)){
				
				AddToCart.get(i).click();
			}			
		}
		
		//WebElement loadingIcon=driver.findElement(By.xpath("//div[contains(@class,'la-ball-scale-multiple ng-star-inserted')]"));			
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(@class,'toast-message')]"))));
		WebElement toastMessage=driver.findElement(By.xpath("//div[contains(@class,'toast-message')]"));
		String actualToastMsg=toastMessage.getText();
		Assert.assertEquals(actualToastMsg, "Product Added To Cart");
		wait.until(ExpectedConditions.invisibilityOf(toastMessage));
		
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		
		String CartProductName=driver.findElement(By.xpath("//div[@class='cartSection'] //h3")).getText();
		String OrderNumber=driver.findElement(By.xpath("//div[@class='cartSection'] //p[@class='itemNumber']")).getText();
		
		Assert.assertEquals(CartProductName, product.toUpperCase());
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Checkout']")));
		try {
			driver.findElement(By.xpath("//button[normalize-space()='Checkout']")).click();
		}catch(ElementClickInterceptedException e) {
			JavascriptExecutor js=(JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[normalize-space()='Checkout']")));
			System.out.println("Unable to click on UI so attempted through JS");
		}
		
		driver.findElement(By.xpath("//div[contains(.,'CVV Code') and @class='title']/following-sibling::input")).sendKeys("0997");
		driver.findElement(By.xpath("//div[contains(.,'Name on Card') and @class='title']/following-sibling::input")).sendKeys("ABC");

		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys(country);
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//section[@class='ta-results list-group ng-star-inserted'] //button"))));
		List<WebElement> CountryOptions=driver.findElements(By.xpath("//section[@class='ta-results list-group ng-star-inserted'] //button"));
		
		for(WebElement opt:CountryOptions)
		{
			String countryName=opt.getText();
			if(countryName.equals(country))
			{
				opt.click();
			}
		}
		
		WebElement submitBtn=driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']"));
		try {
			submitBtn.click();
		}catch(ElementClickInterceptedException e)
		{
			JavascriptExecutor js=(JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();",submitBtn);
			System.out.println("Unable to click on UI so attempted through JS");
		}
		String actualOrderNum=driver.findElement(By.xpath("//label[@class='ng-star-inserted']")).getText();	
		Assert.assertEquals(actualOrderNum, OrderNumber);
		driver.close();
	}

}
