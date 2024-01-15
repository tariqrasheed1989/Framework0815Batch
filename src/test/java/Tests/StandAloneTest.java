package Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest 
{
	public static void main(String[] args) 
	{
		String product="Zara coat 3";
		WebDriver driver= new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebElement txt_UserName=driver.findElement(By.xpath("//input[@id='userEmail']"));
		WebElement txt_Password=driver.findElement(By.xpath("//input[@type='password']"));
		WebElement btn_Login=driver.findElement(By.id("login"));
		
		txt_UserName.sendKeys("pathan@gmail.com");
		txt_Password.sendKeys("Abcd@1234");
		btn_Login.click();
		
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
		
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		String CartProductName=driver.findElement(By.xpath("//div[@class='cartSection'] //h3")).getText();
		String OrderNumber=driver.findElement(By.xpath("//div[@class='cartSection'] //p[@class='itemNumber']")).getText();
		
		Assert.assertEquals(CartProductName, product);
		driver.findElement(By.xpath("//button[normalize-space()='Checkout']")).click();
	}

}
