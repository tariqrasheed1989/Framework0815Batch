package Tests;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import PageObjects.CartPage;
import PageObjects.LandingPage;
import PageObjects.OrderConfirmationPage;
import PageObjects.PaymentConfirmationPage;
import PageObjects.ProductCateloguePage;

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
		
		ProductCateloguePage productCateloguePage=new ProductCateloguePage(driver);
		productCateloguePage.AddToCart(product);
		String ActualToastMsg=productCateloguePage.getToastMessage();
		Assert.assertEquals(ActualToastMsg, "Product Added To Cart");
		productCateloguePage.goToCart();
		CartPage cartPage=new CartPage(driver);
		String ActualCartProductName=cartPage.getCartProductName();
		String OrderNumber = cartPage.getOrderNum();
		Assert.assertEquals(ActualCartProductName, product.toUpperCase());
		cartPage.checkout();
		PaymentConfirmationPage paymentConfirmationPage=new PaymentConfirmationPage(driver);
		paymentConfirmationPage.paymentDetails("0997", "Sachin");
		paymentConfirmationPage.selectCountry(country);
		paymentConfirmationPage.placeOrder();
		OrderConfirmationPage orderConfirmationPage=new OrderConfirmationPage(driver);
		String actualOrderNum=orderConfirmationPage.getOrderNum();

	//	String actualOrderNum=driver.findElement(By.xpath("//label[@class='ng-star-inserted']")).getText();	
		Assert.assertEquals(actualOrderNum, OrderNumber);
		driver.close();
	}

}
